/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmt;

/**
 * Class to hold various prompt strings user will be getting to run application
 * smoothly.
 * @author Aditya Mishra
 */
public class Prompts {
    
    public static String getRestoreDirPrompt() {
        return "Select a folder to restore data from...";
    }

    public static String getBackupDirPrompt() {
        return "Select a folder to backup data to...";
    }

    public static String getFilterPrompt() {
        return "Enter employer name here";
    }

    public static String getSearchPrompt() {
        return "Enter search text here";
    }
    
    public static String getFirstName() {
        return "Enter first name";
    }
    
    public static String getLastName() {
        return "Enter last name";
    }
    
    public static String getEmployerName() {
        return "Enter employer name";
    }
    
    public Prompts() {
        
    }
}
