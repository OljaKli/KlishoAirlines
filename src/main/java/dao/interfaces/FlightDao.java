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


                        final Statement statement2 = connection.createStatement();
                        final ResultSet resultSet2 = statement2.executeQuery(
                                "SELECT day FROM FlightDays WHERE flightId = " +id
                        );


                    List<Integer> daysList = new ArrayList<>();
                    while (resultSet2.next()) {
                        daysList.add(resultSet2.getInt("day"));
                    }

                        return resultSet.next()
                                ? Optional.of(toFlight(resultSet, daysList))
                                : Optional.empty();
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }

        private Flight toFlight(ResultSet resultSet, List<Integer>daysList) throws SQLException {
                return new Flight(resultSet.getInt("id"),
                        resultSet.getString("flightNumber"),
                        resultSet.getString("apfrom"),
                        resultSet.getString("apto"),
                        LocalTime.parse(resultSet.getString("departureTime")),
                        daysList);

            }

}
