package dao.interfaces;

/**
 * Created by Ola-Mola on 30/05/16.
 */

import com.klisho.airlines.FlightAssignment;


import java.sql.Connection;
import java.sql.SQLException;
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



}


