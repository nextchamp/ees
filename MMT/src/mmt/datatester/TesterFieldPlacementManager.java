package mmt.datatester;

import mmt.FieldPlacementManager;
import java.util.List;

/**
 * @(#)TesterFieldPlacementManager.java
 *
 *
 * @author Aditya  Mishra
 * @version 1.00 2013/1/17
 */


public class TesterFieldPlacementManager {
	
    public static void main(String[] args){
        String parentPath = "data";
        String inFileName  = "inputFP.txt";
        String outFileName = "outputFP.txt";
        String employerId = "101";
        String employeeId = "9001";
        boolean status = false;
        int count = 0;
        
    	FieldPlacementManager fpm = new FieldPlacementManager();

    	System.out.println("Testing placing {\"8123\", \"8113\", \"8111\"} with employer 101 --------------------");
        status = fpm.placeEmployee(employerId, new String[]{"8123", "8113", "8111"});
        System.out.println(fpm.toString());
        System.out.println("Testing placing {\"9201\", \"9213\", \"9001\"} with employer 202 --------------------");
        status = fpm.placeEmployee("202", new String[]{"9201", "9213", "9001"});
        System.out.println(fpm.toString());
        
        System.out.println("Testing find Employees --------------------");
        List employeesList = fpm.findEmployeesOf(employerId);
        System.out.println("For employeR: " + employerId + ", employee(s) found is : ");
        if (employeesList.isEmpty()) {
            System.out.println("none");
        } else {
            for(int i = 0; i < employeesList.size(); i++) {
                System.out.println(employeesList.get(i).toString());
            }
        }
        System.out.println(fpm.toString());

        System.out.println("Testing find Employer --------------------");
        String foundEmployerId = fpm.findEmployerOf(employeeId);
        System.out.println("For employee" + employeeId + ", employer found is : ");
        if (foundEmployerId != null) {
            System.out.println(foundEmployerId);
        } else {
            System.out.println("none");
        }
        System.out.println(fpm.toString());
        
        System.out.println("Testing remove [9213] --------------------");
        String removedEmployee = fpm.removeEmployee("9213");
        System.out.print("After removing : ");
        if (removedEmployee != null) {
            System.out.println(removedEmployee);
        } else {
            System.out.println("none found");
        }
        System.out.println(fpm.toString());
        
        /*
        count = fpm.importData(inFileName);        
        System.out.println("Testing Import --------------------");
        System.out.println("Data imported, size: " + count);
        System.out.println(fpm.toString());
        */
        
        status = fpm.exportData(parentPath, outFileName);
        System.out.println("Testing Export --------------------");
        System.out.println("Data exported, status: " + status);
        count = fpm.importData(parentPath, outFileName);
        System.out.println("Data imported using new file, size: " + count);
        System.out.println(fpm.toString());
    }

}