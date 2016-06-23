package com.klisho.airlines;

import lombok.Data;
import org.joda.time.DateTimeConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ola-Mola on 29/05/16.
 */
@Data
public class Employee {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final Profession profession;


    private static final Map<Integer, String> profNames = new HashMap<Integer, String>() {
        {
            put(0,   "пилот");
            put(1,  "радиооператор");
            put(2, "штурман");
            put(3, "стюардесса");

        }
    };

    private static String profToStr(Integer profession) {
        return profNames.get(profession);
    }

    public String getShortString() {
        return getFirstName() + " " + getLastName();
}

    public String getFirstName (Employee emp){
    return emp.firstName;
    }

    public String getLastName (Employee emp){
        return emp.lastName;
    }

    public static Integer profToInt (Profession prof) {
        Integer profNum;
        switch (prof) {
            case PILOT:  profNum = 0;
                break;
            case RADIOP:  profNum = 1;
                break;
            case NAVIGATOR:  profNum = 2;
                break;
            case HOSTESS:  profNum = 3;
                break;
            default: profNum = null;
                break;
        }
    return profNum;

    }

}
