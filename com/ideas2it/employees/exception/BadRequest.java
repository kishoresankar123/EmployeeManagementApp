package com.ideas2it.employees.exception;
import java.util.List;
import java.util.ArrayList;
/**
* It can be implemented for accessing custom exception.
**/
public class BadRequest extends RuntimeException {
    public List<Integer> validationErrorAsList = new ArrayList<>();

    public BadRequest(String message, List<Integer> validationErrorAsList) {
        super(message);
        this.validationErrorAsList = validationErrorAsList; 
    }

    public List<Integer> getValidationErrorAsList() {
        return validationErrorAsList;
    }
}