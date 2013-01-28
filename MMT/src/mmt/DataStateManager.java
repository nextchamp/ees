/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmt;

/**
 * This class encapsulates the state of application
 * based on user's actions (add/remove/update) will change the
 * state to dirty. And save will change it back to clean.
 * 
 * @author Aditya Mishra
 */
public class DataStateManager {
    
    private boolean employeeDataDirty = false;
    private boolean employerDataDirty = false;
    private boolean reportDataDirty = false;
    /*
     * Defines the state of the data
     */
    public enum DataState {
        CLEAN, DIRTY
    }
    
    public DataStateManager() {
        
    }
    
    /*
     * Method to return the state of the data
     */
    public boolean isDirty() {
        if (isEmployeeDataDirty() || isEmployerDataDirty() ||
                isReporDatatDirty()) {
            return true;
        }
        
        return false;
    }
    
    /*
     * Method to return the state of the employee data
     */
    public boolean isEmployeeDataDirty() {
        return employeeDataDirty;
    }
    
    /*
     * Method to return the state of the employer data
     */
    public boolean isEmployerDataDirty() {
        return employerDataDirty;
    }
    
    /*
     * Method to return the state of the report data
     */
    public boolean isReporDatatDirty() {
        return reportDataDirty;
    }
    
    /*
     * Method to set the state of employee data dirty
     * @params: app-mode (EMPLOYEE, EMPLOYER, REPORT etc..)
     */
    public void setDirty(ViewMode mode) {
        
        // Check which table is active
        switch(mode){
            case EMPLOYEE:
                employeeDataDirty = true;
                break;
            case EMPLOYER:
                employerDataDirty = true;
                break;
            case REPORT_CONCISE:
                break;
            case REPORT_FULL: // mode when "Evaluate" employee mode
                reportDataDirty = true;
                break;
        } // switch
    }
    
    /*
     * Method to set the state of employee data dirty
     * @params: app-mode (EMPLOYEE, EMPLOYER, REPORT etc..)
     */
    public void setClean(ViewMode mode) {
        
        // Check which table is active
        switch(mode){
            case EMPLOYEE:
                employeeDataDirty = false;
                break;
            case EMPLOYER:
                employerDataDirty = false;
                break;
            case REPORT_CONCISE:
                reportDataDirty = false;
                break;
             case REPORT_FULL:
                
                break;
        } // switch
    }
}
