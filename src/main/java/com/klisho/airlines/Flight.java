package com.klisho.airlines;

/**
 * Created by Ola-Mola on 29/05/16.
 */
import lombok.*;

import org.joda.time.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



@Data
public class Flight {

    private final String flightNumber;
    private final String from; //IATA airport code: LED, DME..
    private final String to;

    private final LocalTime departureTime;
    private final List<Integer> daysOfWeek;





    public static final List<Integer> EVERY_DAY = Collections.unmodifiableList(new ArrayList(7) {
        {
            add(DateTimeConstants.MONDAY);
            add(DateTimeConstants.TUESDAY);
            add(DateTimeConstants.WEDNESDAY);
            add(DateTimeConstants.THURSDAY);
            add(DateTimeConstants.FRIDAY);
            add(DateTimeConstants.SATURDAY);
            add(DateTimeConstants.SUNDAY);
        }
    });

}
