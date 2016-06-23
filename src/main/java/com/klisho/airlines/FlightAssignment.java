package com.klisho.airlines;

import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.util.*;

/**
 * Created by Ola-Mola on 29/05/16.
 */

@Data
public class FlightAssignment {

//    private final Flight flight;
//    private final DateTime date;
//    private final List<Employee> crew =  new ArrayList<>();
//    private final int flightId;
    private final Flight flight;
    private final Employee pilot;
    private final Employee radiop;
    private final Employee navigator;
    private final Employee hostess1;
    private final Employee hostess2;
    private final Employee hostess3;



}
