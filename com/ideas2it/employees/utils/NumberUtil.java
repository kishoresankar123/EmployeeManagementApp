package com.ideas2it.employees.utils;

import java.util.Date;
import java.time.Period;
import java.time.LocalDate; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * It can be implemented for validation of numerical inputs from the user.
 **/
public class NumberUtil {

    /**
    * It can be implemented for tax calculation of user salary input.
    *
    * @Param receives salary in int data type
    * @return returns processed value as calculation result
    * @Example param = 1000, return = 820
    **/
    public static int taxCalculator(int salary) {
        int tempSalary = 0;
        tempSalary = salary - (salary * 18 / 100);
        return tempSalary;
    }

    /**
    * It can be implemented for salary validation of user input.
    *
    * @Param receives salary in int data type
    * @return returns boolean as validation result
    * @Example1 param = 850, return = false
    * @Example2 param = 1500, return = true
    **/
    public static boolean salaryValidator(final int salary) {
        return String.valueOf(salary).length() < 4 ;   
    }

    /**
    * It can be implemented for phone number validation of user input.
    *
    * @Param receives number in string data type
    * @return returns boolean as validation result
    * @Example1 param = 1234567890, return = false
    * @Example2 param = 9790764943, return = true
    **/
    public static boolean isPhoneValid(final String number) {
        return number.startsWith("9");
    }

    /**
    * It can be implemented for age calculation of user input.
    *
    * @Param receives date in LocalDate data type
    * @return returns age as validation result
    * @Example1 param = 29-10-1999, return = 23
    **/
    public static int ageCalculator(final LocalDate temporaryDate) throws ParseException {
        LocalDate presentDate = LocalDate.now();
        Period age = Period.between(temporaryDate,presentDate);
        return age.getYears();
    }

}