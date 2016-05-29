package com.klisho.airlines;

import lombok.Data;

/**
 * Created by Ola-Mola on 29/05/16.
 */
@Data
public class Employee {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final Profession profession;

}
