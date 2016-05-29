package com.klisho.airlines;

import lombok.Data;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ola-Mola on 29/05/16.
 */

@Data
public class FlightAssignment {

    private final Flight flight;
    private final DateTime date;
    private final List<Employee> crew =  new ArrayList<>();

}
