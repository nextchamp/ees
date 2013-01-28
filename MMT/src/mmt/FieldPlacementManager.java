/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aditya Mishra
 */
public class FieldPlacementManager {
    
    /*
    * EmployeeId(String) is the key 
     */
    private Map<String, String> employeeToEmployerMap ;
    public FieldPlacementManager() {
        employeeToEmployerMap = new HashMap<String, String>();        
    }
    
    public boolean placeEmployee(String employerId, String[] employeeIds) {
        boolean status = true;
        if (employerId == null || employerId.isEmpty() ||
                employeeIds == null || employeeIds.length == 0) {
            return false;
        }
        
        // add all employees to this employer
        for (int i = 0; i < employeeIds.length; i++) {
            String prevEmployerId = employeeToEmployerMap.put(employeeIds[i], employerId);
	}
        return status;
    }
    /*
     * Method returns true if employee with employerId is employed.
     */
    public boolean isEmployed(String employeeId) {
        if ( findEmployerOf(employeeId) != null) {
            return true;
        } 
        return false;
    }
    public String removeEmployee(String employeeId) {
        return employeeToEmployerMap.remove(employeeId);
    }
	
    public String findEmployerOf(String employeeId){
        return employeeToEmployerMap.get(employeeId);
    }
    
    public List findEmployeesOf(String employerId){
        List employeeIds = new ArrayList<String>();
        
        // Iterate thru map to get eachentry and check if this employer is present
        Iterator entries = employeeToEmployerMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String employeeId = (String)entry.getKey();
            String value = (String)entry.getValue();
            if ( employerId.equalsIgnoreCase(value)) {
                employeeIds.add(employeeId);
            }
        }
        
        return employeeIds;
    }

    public int getEmployeeCount() {
        return this.employeeToEmployerMap.size();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        Iterator entries = employeeToEmployerMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String employeeId = (String)entry.getKey();
            String value = (String)entry.getValue();

            String line = employeeId + "," + value;
            sb.append(line);
            sb.append("\n");
        }// while there is more line to write to file

        return sb.toString();
    }
    
    public boolean exportData(String parentPath, String fileName) {
        
        boolean satus = false;
        BufferedWriter wr = null;
        try {
            File fs = AppUtils.getDataFile(parentPath, fileName);           
            OutputStream os = new FileOutputStream(fs);
            wr = new BufferedWriter(new OutputStreamWriter(os));
            
            // Write the header
            wr.write("employeeId,employerId");
            wr.newLine();
            wr.write(this.toString());
            wr.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            wr.close();
            satus = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return satus;
    }
    
    public int importData(String parentPath, String fileName) {
        int count = 0;
        // Empty out map
        employeeToEmployerMap.clear();
        BufferedReader br = null;
        try {
            //BufferedReader br = new BufferedReader(new FileReader(filePathName));
            File fs = AppUtils.getDataFile(parentPath, fileName);           
            InputStream is = new FileInputStream(fs);
            br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while((line=br.readLine())!=null) {
                if(count++ == 0) {
                    // its header, so drop/ignore this line
                    continue;
		}
		String[] parts = line.split(",");
                if ( !parts[0].isEmpty() ) {
                    String employeeId = parts[0];
                    String employerId = parts[1];
                    if ((employeeId == null)||(employerId ==null)){
                        continue;
                    }
                    // Add it to this placement objet
                    placeEmployee(employerId, new String[]{employeeId});
                }
            } // while there is more line in the file
            count = employeeToEmployerMap.size();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return count;
    }
}
