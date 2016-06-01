package dao.interfaces;

import com.klisho.airlines.Employee;
import com.klisho.airlines.Profession;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

/**
 * Created by Ola-Mola on 01/06/16.
 */
public class EmployeeDao implements Dao {
    private final Connection connection;

    public EmployeeDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connection;
    }

    public Optional<Employee> getEmployeeById(int id) {
        try {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(
                    "SELECT id, firstName, lastName, profession FROM Employee WHERE id = " + id);

            return resultSet.next()
                    ? Optional.of(toEmployee(resultSet))
                    : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Employee toEmployee(ResultSet resultSet) throws SQLException {
        return new Employee(resultSet.getInt("id"),
            resultSet.getString("firstName"),
            resultSet.getString("lastName"),
            Profession.values()[resultSet.getInt("profession")]);
    }

    public Collection<Employee> getAll() {
        try {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(
                    "SELECT id, firstName, lastName, profession FROM Employee ORDER BY id"
            );

            Collection<Employee> emps = new LinkedList<>();
            while (resultSet.next()) {
                emps.add(toEmployee(resultSet));
            }
            return emps;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
