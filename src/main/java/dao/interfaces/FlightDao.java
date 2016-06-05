package dao.interfaces;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.klisho.airlines.Employee;
import com.klisho.airlines.Flight;
import com.klisho.airlines.Profession;
import org.joda.time.*;
import sun.security.util.Length;

import static com.klisho.airlines.Flight.EVERY_DAY;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

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

    public Collection<Flight> getAllFlights() {
        try {

            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(
                    "SELECT id, flightNumber, apfrom, apto, departureTime FROM Flight ORDER BY id"
            );

            Collection<Flight> flights = new LinkedList<>();
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

}
