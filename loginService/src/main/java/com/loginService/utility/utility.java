package com.loginService.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class utility {

    public static boolean validateUserType(String userType){
        return !userType.equalsIgnoreCase("admin") && !userType.equalsIgnoreCase("farmer") && !userType.equalsIgnoreCase("dealer");
    }

    public static boolean validatePassword(String password){

        // Check if the string starts with an alphabet
        if (!Character.isLetter(password.charAt(0))) {
            return true;
        }

        // Check if the string contains at least one uppercase alphabet
        boolean containsUppercase = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                containsUppercase = true;
                break;
            }
        }
        if (!containsUppercase) {
            return true;
        }

        // Check if the string matches the allowed characters and length
        String regex = "^[a-zA-Z0-9!@#$%&]{8,16}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return !matcher.matches();
    }

    public static boolean validatePhoneNumber(Long phoneNo){
        String number = String.valueOf(phoneNo);
        return number.length() != 10;
    }

}
