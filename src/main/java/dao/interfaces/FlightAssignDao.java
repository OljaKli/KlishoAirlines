package dao.interfaces;

/**
 * Created by Ola-Mola on 30/05/16.
 */

import com.klisho.airlines.Employee;
import com.klisho.airlines.Flight;
import com.klisho.airlines.FlightAssignment;
import org.joda.time.LocalTime;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class FlightAssignDao implements Dao {
    private final Connection connection;

    public FlightAssignDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connection;
    }

    public Optional<FlightAssignment> createFlightAssign (int flightId, int pilot, int radiop, int navigator,
                                                int hostess1, int hostess2, int hostess3) {
        try {

            final PreparedStatement prStatement = connection.prepareStatement(
                    "INSERT INTO FlightAssign (flightId, Pilot, Radiop, Navigator, Hostess1, Hostess2, Hostess3) " +
                            "values (?, ?, ?, ?, ?, ?, ?)");

            prStatement.setInt(1, flightId);
            prStatement.setInt(2, pilot);
            prStatement.setInt(3, radiop);
            prStatement.setInt(4, navigator);
            prStatement.setInt(5, hostess1);
            prStatement.setInt(6, hostess2);
            prStatement.setInt(7, hostess3);


            int rowsAffected = prStatement.executeUpdate();//rowsAffected = the number of changed rows

//            ResultSet rs = prStatement.getGeneratedKeys();
//            if (rs.next()) {
//                int id = rs.getInt(1);
             return getFlightAssignById(flightId);
//            } else {
//                throw new RuntimeException("Failed to insert new flight");
//            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }


    public Optional<FlightAssignment> getFlightAssignById(int id) {
        try {
            final Statement statement1 = connection.createStatement();
            final ResultSet resultSet = statement1.executeQuery(
                    "SELECT flightId, Pilot, Radiop, Navigator, Hostess1, Hostess2, Hostess3 " +
                            "FROM FlightAssign WHERE flightId = " + id);
//???

            return resultSet.next()
                    ? Optional.of(toFlightAssign(resultSet))
                    : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private FlightAssignment toFlightAssign (ResultSet resultSet) throws SQLException {
        EmployeeDao daoEmp = new EmployeeDao(connection);
        FlightDao daoFlight = new FlightDao(connection);

        return new FlightAssignment(
                daoFlight.getFlightById(resultSet.getInt("flightId")).get(),
                daoEmp.getEmployeeById(resultSet.getInt("Pilot")).get(),
                daoEmp.getEmployeeById(resultSet.getInt("Radiop")).get(),
                daoEmp.getEmployeeById(resultSet.getInt("Navigator")).get(),
                daoEmp.getEmployeeById(resultSet.getInt("Hostess1")).get(),
                daoEmp.getEmployeeById(resultSet.getInt("Hostess2")).get(),
                daoEmp.getEmployeeById(resultSet.getInt("Hostess3")).get());

    }


    public List<FlightAssignment> getAllFlightAssign() {
        try {

            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(
                    "SELECT flightId, Pilot, Radiop, Navigator, Hostess1, Hostess2, Hostess3 " +
                            "FROM FlightAssign ORDER BY flightId"
            );

            List<FlightAssignment> flightAssign = new LinkedList<>();
            while (resultSet.next()) {
                flightAssign.add(toFlightAssign(resultSet));
            }
            return flightAssign;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
