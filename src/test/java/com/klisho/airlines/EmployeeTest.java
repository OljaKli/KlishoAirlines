package com.klisho.airlines;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ola-Mola on 29/05/16.
 */



public class EmployeeTest {
    @Test
    public void testEmployee(){
        Employee employee = new Employee(0, "Ivan", "Petrov", Profession.PILOT);
        assertEquals("Ivan", employee.getFirstName());
        assertEquals(0, employee.getId());
        assertEquals("Petrov", employee.getLastName());
        assertEquals(Profession.PILOT, employee.getProfession());

    }
}