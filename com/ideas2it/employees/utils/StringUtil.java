package com.ideas2it.employees.utils;

/**
* It can be implemented for string validations of user input.
*
**/
public class StringUtil {

    /**
    * It can be implemented for name validation of user input.
    *
    * @Param receives name in string data type
    * @return returns boolean as validation result
    * @Example1 param = raja, return = true
    * @Example2 param = raja99, return = false
    **/
    public static boolean nameValidator(String name) {
        boolean isNameValid = false;
        for (int i = 0; i < name.length(); i++) {
            if (Character.isDigit(name.charAt(i))) {
                isNameValid = true;
            }
        }
        return isNameValid;
    }

    /**
    * It can be implemented for email validation of user input.
    *
    * @Param receives email in string data type
    * @return returns boolean as validation result
    * @Example1 param = raja@, return = true
    * @Example2 param = raja99@.com, return = false
    **/
    public static boolean isEmailValid(String email) {
         return (email.endsWith(".com") && email.contains("@") && email.length()>7);
    }
}