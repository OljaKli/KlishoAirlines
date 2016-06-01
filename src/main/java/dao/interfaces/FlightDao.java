package dao.interfaces;

import java.util.Collection;
import java.util.Optional;
import com.klisho.airlines.Flight;
/**
 * Created by Ola-Mola on 30/05/16.
 */
public interface FlightDao extends Dao {

        Optional<Flight> getFlightById(int id);
        Collection<Flight> getAll();

}
