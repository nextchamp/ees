/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author Aditya Mishra
 */
public class IdGenerator {
    private int nextEmployeeId = 1001;
    private int nextEmployerId = 201;
    private int nextReportId   = 301;
    
    private String filePathName = "nextIdList.txt";
    
    public IdGenerator() {
        
    }
    
    protected void finalize () {
        this.save();
    }
    
    public void save () {
        BufferedWriter wr = null;
        try {
            File fs = new File(getClass().getClassLoader().getResource(this.filePathName).getPath());           
            OutputStream os = new FileOutputStream(fs);
            wr = new BufferedWriter(new OutputStreamWriter(os));
			
			//OutputStream os = getClass().getClassLoader().getResource(this.filePathName).openConnection().getOutputStream();
            //wr = new BufferedWriter(new OutputStreamWriter(os));

            // Write the header
            //wr.write("employeeId,employerId");
            //wr.newLine();
            
            StringBuilder sb = new StringBuilder();
            sb.append(nextEmployeeId);
            sb.append(",");
            sb.append(nextEmployerId);
            sb.append(",");
            sb.append(nextReportId);
            sb.append("\n");
            
            wr.write(sb.toString());
            wr.flush();    
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            wr.close();
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }
    
    public static IdGenerator GetIdGenerator(String filePathName) {
        IdGenerator idg = new IdGenerator();
        BufferedReader br = null;

        try {
            InputStream is = IdGenerator.class.getClassLoader().getResourceAsStream(filePathName);
            br = new BufferedReader(new InputStreamReader(is));
            idg.filePathName = filePathName;
            String line = null;
            if ( (line = br.readLine()) != null) {
                String[] parts = line.split(",");
		idg.nextEmployeeId = Integer.parseInt(parts[0]);
		idg.nextEmployerId = Integer.parseInt(parts[1]);
                idg.nextReportId = Integer.parseInt(parts[2]);
            } // if found
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            br.close();
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        return idg;
    }
    
    public void reset(int nextEmployeeId, int nextEmployerId, int nextReportId) {
        this.nextEmployeeId = ++nextEmployeeId;
        this.nextEmployerId = ++nextEmployerId;
        this.nextReportId = ++nextReportId;
    }

    public String getNextId(ViewMode mode) {
        String str = "";
        switch(mode) {
            default:
            case EMPLOYEE:
                // Employee id
                str = "" + this.nextEmployeeId++;
                break;
            case EMPLOYER:
                // Employer id
                str = "" + this.nextEmployerId++;
                break;
            case REPORT_CONCISE:
            case REPORT_FULL:
                // Report id
                str = "" + this.nextReportId++;
                break;
        }
        return str;
    }
}
