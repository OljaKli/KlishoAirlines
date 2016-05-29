package com.klisho.airlines;


import org.joda.time.LocalTime;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Created by Ola-Mola on 29/05/16.
 */
public class FlightListTest {
    @Test
    public void testFlightList() {
        Flight f1 = new Flight("SU1142", "SVO", "AAQ", LocalTime.parse("14:55"), Flight.EVERY_DAY);
        assertEquals("SU1142", f1.getFlightNumber());
        assertEquals(14, f1.getDepartureTime().getHourOfDay());
        assertEquals(55, f1.getDepartureTime().getMinuteOfHour());
    }
}