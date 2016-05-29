package com.klisho.airlines;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ola-Mola on 29/05/16.
 */

@Data
public class FlightList {
private final List<Flight> flightList = new ArrayList<>();

}
