package com.ideas2it.employees.exception;

/**
* It can be implemented for accessing custom exception.
**/
public class NoEmployeeFound extends Exception {

    public NoEmployeeFound(String message) {
        super(message);
    } 
}