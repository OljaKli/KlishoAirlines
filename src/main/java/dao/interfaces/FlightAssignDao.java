package dao.interfaces;

/**
 * Created by Ola-Mola on 30/05/16.
 */

import com.klisho.airlines.FlightAssignment;


import java.util.Optional;

public interface FlightAssignDao extends Dao {
    Optional<FlightAssignment> getInstanceById(int id);
}


