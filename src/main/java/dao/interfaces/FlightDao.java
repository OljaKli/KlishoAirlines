package dao.interfaces;

import java.sql.*;
import java.util.*;

import com.klisho.airlines.Flight;
import org.joda.time.*;

/**
 * Created by Ola-Mola on 30/05/16.
 */
public class FlightDao implements Dao {
        private final Connection connection;


        public FlightDao(Connection connection) {
                this.connection = connection;
        }

        @Override
        public Connection getConnection() throws SQLException {
                return connection;
        }

        public Optional<Flight> getFlightById(int id) {
                try {
                        final Statement statement1 = connection.createStatement();
                        final ResultSet resultSet = statement1.executeQuery(
                                "SELECT id, flightNumber, apfrom, apto, departureTime FROM Flight WHERE id = " + id);


                        List<Integer> daysList = getDaysOfFlight(id);

                        return resultSet.next()
                                ? Optional.of(toFlight(resultSet, daysList))
                                : Optional.empty();
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }

    private List<Integer> getDaysOfFlight(int flightId) throws SQLException {
        final Statement statement2 = connection.createStatement();
        final ResultSet resultSet2 = statement2.executeQuery(
                "SELECT day FROM FlightDays WHERE flightId = " + flightId);

        List<Integer> daysList = new ArrayList<>(7);
        while (resultSet2.next()) {
            daysList.add(resultSet2.getInt("day"));
        }
        return daysList;
    }

    private Flight toFlight(ResultSet resultSet, List<Integer>daysList) throws SQLException {
        return new Flight(resultSet.getInt("id"),
                resultSet.getString("flightNumber"),
                resultSet.getString("apfrom"),
                resultSet.getString("apto"),
                LocalTime.parse(resultSet.getString("departureTime")),
                daysList);

    }

    public List<Flight> getAllFlights() {
        return getAllFlights(false);
    }

    /**
     *
     * @param canceled - include only canceled flight to result, otherwise only actual flight
     * @return
     */
    public List<Flight> getAllFlights(boolean canceled) {
        try {

            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(
                    "SELECT id, flightNumber, apfrom, apto, departureTime FROM Flight"
                    + (canceled ? " WHERE canceled = TRUE" : " WHERE canceled = FALSE")
                    + " ORDER BY id"
            );

            List<Flight> flights = new LinkedList<>();
            while (resultSet.next()) {
                int flightId = resultSet.getInt("id");

                List<Integer> daysList = getDaysOfFlight(flightId);

                flights.add(toFlight(resultSet, daysList));
            }
            return flights;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean cancelFlight(int flightId) {
        try {
            final PreparedStatement prStatement = connection.prepareStatement(
                    "UPDATE Flight SET canceled = TRUE WHERE ID = ?");

            prStatement.setInt(1, flightId);

            int rowsAffected = prStatement.executeUpdate();//rowsAffected = the number of changed rows
            return rowsAffected >= 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Flight> createFlight(String flightNumber, String apFrom, String apTo, LocalTime departureTime, List<Integer> days) {
        try {

            final PreparedStatement prStatement = connection.prepareStatement(
                    "INSERT INTO Flight (flightNumber, apfrom, apto, departureTime) values (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            prStatement.setString(1, flightNumber);
            prStatement.setString(2, apFrom);
            prStatement.setString(3, apTo);
            prStatement.setString(4, departureTime.toString(("HH:mm")));



            int rowsAffected = prStatement.executeUpdate();//rowsAffected = the number of changed rows

            ResultSet rs = prStatement.getGeneratedKeys();
            if (rs.next()) {
               // prStatement.executeUpdate();
                int id = rs.getInt(1);

                // TODO insert days of week
               // for (int n: days) {
                final PreparedStatement prStatement2 = connection.prepareStatement(
                        "INSERT INTO FlightDays (flightId, day) values (?,?)");
                prStatement2.setInt(1, id);

                for (int n = 0; n < days.size(); n++) {
                    prStatement2.setInt(2, days.get(n));
                    int rowsAffected2 = prStatement2.executeUpdate();
                }
                //int rowsAffected2 = prStatement2.executeUpdate();


                return getFlightById(id);
            }
            else  {
                throw new RuntimeException("Failed to insert new flight");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
