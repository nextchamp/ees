/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmt.utilities;

import mmt.CalendarUtil;

/**
 *
 * @author Praveen.Kumar
 */
public class DataValidator {
    
    /*
     * Returns true if name is not empty/null else false
     * 
    */
    public static boolean isNameValid(String s) {
        if (s == null || s.isEmpty()) {
          return false;
        }
        
        return true;
    }
    /*
     * Returns true if street is not empty/null else false
     * 
    */
    public static boolean isStreetValid(String s) {
        if (s == null || s.isEmpty()) {
          return false;
        }
        
        return true;
    }
    /*
     * Returns true if city is not empty/null else false
     * 
    */
    public static boolean isCityValid(String s) {
        if (s == null || s.isEmpty()) {
          return false;
        }
        
        return true;
    }
    /*
     * Returns true if state is not empty/null else false
     * 
    */
    public static boolean isStateValid(String s) {
        if (s == null || s.isEmpty()) {
          return false;
        }
        
        return true;
    }
    /*
     * Method to validate the zip code
     * 
    */
    public static boolean isZipCodeValid(String s) {
        
        if (s == null || s.isEmpty()) {
          return false;
        } else {
            // try to parse the postal code into an int
            try {
                s = s.trim();
                Integer.parseInt(s);
                if (s.length() == 5) {
                    return true;
                }
            } catch (NumberFormatException e) {
            }
        }
        return false;
    }
    /*
     * Method to validate the zip code
     * 
    */
    public static boolean isEmailValid(String s) {
        if (s == null || s.isEmpty()) {
          return false;
        } else if (s.indexOf("@") == -1) {
            return false;
        }else if (s.indexOf(".") == -1) {
            return false;
        }
        
        return true;
    }
    /*
     * Method to validate the phone number
     * 
    */
    public static boolean isPhoneNumberValid(String s) {
        if (s == null || s.isEmpty()) {
          return false;
        }
            
        return true;
    }
    /*
     * Method to validate the date
     * 
    */
    public static boolean isDateValid(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        } else {
            if (!CalendarUtil.validString(s)) {
                return false; // "No valid birthday. Use the format yyyy-mm-dd!\n";
            }
        }
        return true;
    }
    public DataValidator() {
        
    }
    
}
