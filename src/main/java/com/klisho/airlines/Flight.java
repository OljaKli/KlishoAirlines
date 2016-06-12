package com.klisho.airlines;

/**
 * Created by Ola-Mola on 29/05/16.
 */
import lombok.*;

import org.joda.time.*;

import java.util.*;


@Data
public class Flight {

    private final int id; //added
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

    private static final Map<Integer, String> dayNames = new HashMap<Integer, String>() {
        {
            put(DateTimeConstants.MONDAY,   "пн");
            put(DateTimeConstants.TUESDAY,  "вт");
            put(DateTimeConstants.WEDNESDAY, "ср");
            put(DateTimeConstants.THURSDAY, "чт");
            put(DateTimeConstants.FRIDAY,   "пт");
            put(DateTimeConstants.SATURDAY, "сб");
            put(DateTimeConstants.SUNDAY,   "вс");
        }
    };

    private static String dayToStr(Integer day) {
        return dayNames.get(day);
    }

    public String getDepartureTimeStr() {
        return getDepartureTime().toString("HH:mm");
    }

    public String getDaysOfWeekStr() {
        StringBuilder builder = new StringBuilder();

        for (Integer day : getDaysOfWeek()) {
            if (builder.length() > 0) {
                builder.append(", ");
            }

            builder.append(dayToStr(day));
        }
        return builder.toString();
    }
}
