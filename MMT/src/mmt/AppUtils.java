/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import org.javafxdata.datasources.Format;
import org.javafxdata.datasources.provider.CSVDataSource;
import org.javafxdata.datasources.reader.DataSourceReader;
import org.javafxdata.datasources.reader.FileSource;
import se.mbaeumer.fxmessagebox.MessageBox;
import se.mbaeumer.fxmessagebox.MessageBoxType;

/**
 *
 * @author Aditya Mishra
 */
public class AppUtils {

    public static final String[] dataHeaderEmployee = new String[]{"id", "firstName", "lastName", "email", "phone", "cellNumber", "address", "city", "state", "zipCode"};
    public static final String[] dataHeaderEmployer = new String[]{"id", "name", "address", "city", "state", "zipCode", "phone", "email", "contactPerson"};
    public static final String[] dataHeaderEvalReport = new String[]{"id","employeeId", "employerId", "evaluationDate", "nextEvaluationDate", "qualityScore", "habitScore", "knowledgeScore", "behaviorScore", "averageScore", "overallScore", "recommendation"};
    public static final String[] dataHeaderFullEvalReport = new String[]{"id", "employeeId", "employerId", "evaluationDate", "nextEvaluationDate", "qualityScore", "qualityComments", "habitScore", "habitComment", "knowledgeScore", "knowledgeComments", "behaviorScore", "behaviorComment", "averageScore", "overallScore", "overallComment", "recommendation"};

    /*
     * Number of columns in employee 
     */
    public static final int columnsInEmployeeData = dataHeaderEmployee.length;//10;
    public static final int columnsInEmployerData = dataHeaderEmployer.length;//9;
    public static final int columnsInReportData = dataHeaderEvalReport.length;//16;
    public static final int columnsInFullReportData = dataHeaderFullEvalReport.length;//16;

    public static String getNextIdListFileName() {
        return AppSettings.getNextIdListDataFilePathName(null);
    }

    /*
     * Method to return headerString using header-column-names.
     * It does not add newline at the end.
     */
    public static String getHeaderString(String[] dataHeader) {
        StringBuilder sb = new StringBuilder();
        
        // Build the heaader
        for (int i = 0; i < dataHeader.length; i++) {
            sb.append(dataHeader[i]);
            // if not last column add "," otherwise new-line
            if (i != dataHeader.length-1) {
                sb.append(",");
            }
        }
        return sb.toString();    
    }
    /*
     * Method to get evaluation file object for employee object
     * Evaluation: evaluation object
     */
    public static File getEvalDataFile(Evaluation empEval, String parentPath) throws Exception {
        String fileName = empEval.getEmployeeNumber() + ".txt";
        String filePath = parentPath;
        if (parentPath == null) {
            filePath = AppUtils.class.getClassLoader().getResource(AppSettings.DataRootPath).getPath();
        }
        File fs = new File(filePath, fileName);
        
        return fs;
    }
    /*
     * Method to get data file object for given filename 
     * @param String : parentPath
     * @param String fileName
     */
    public static File getDataFile(String parentPath, String fileName) throws Exception {
        String filePath = parentPath;
        if (parentPath == null) {
            filePath = AppUtils.class.getClassLoader().getResource(AppSettings.DataRootPath).getPath();
        }
        File fs = new File(filePath, fileName);
        
        return fs;
    }
    public static String getDataFileName(ViewMode dataType, String rootPath) {
        
        // Get the filename based on type
        String fileName = null;
        
        // Check which table is active
        switch(dataType){
            case EMPLOYEE:
                fileName = AppSettings.getEmployeeDataFilePathName(rootPath);
                break;
            case EMPLOYER:
                fileName = AppSettings.getEmployerDataFilePathName(rootPath);
                break;
            case REPORT_CONCISE:
            case REPORT_FULL:
                fileName = AppSettings.getEvalResultDataFilePathName(rootPath);
                break;
        }
        
        return fileName;
    }
    
    
    
    /*
     * Method to write multiple evaluation reports into single consolidated file. "evaluations.txt"
     */
    public static boolean writeMultipleReportToSingleFile(ArrayList<Evaluation> evaluationList, String parentPath) {
        String headerStr = AppUtils.getHeaderString(AppUtils.dataHeaderFullEvalReport);
        //String folderPath = parentPath;
        //if (parentPath == null) {
        //    folderPath = AppSettings.DataRootPath;
        // }
        boolean status = false;
        try { 
            File fs = new File(parentPath, AppSettings.ConolidatedReportFileName);
            OutputStream os = new FileOutputStream(fs);
            status = Evaluation.exportDataFileHasHeader(evaluationList, os, headerStr);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return status;
    }
    
    /*
     * Method to backup data to default location
     */
    public static boolean  backupSingleEvalData(Evaluation empEval, String parentPath) {
        
        // Return false, if not valid state
        if (empEval == null) {
            return false;
        }
        
        String[] dataHeader = AppUtils.dataHeaderFullEvalReport;
        
        // Open file in overwrite mode
        // Get the output stream for file to keep writing the data
        boolean success = true;
        OutputStream os = null;
        try {
            File fs = AppUtils.getEvalDataFile(empEval, parentPath);
            os = new FileOutputStream(fs);
            
            String headerWithCommas = getHeaderString(dataHeader);
            // Write the header and data
            empEval.exportDataFileHasHeader(os, headerWithCommas);            
        } catch (Exception ex) {
            ex.printStackTrace();
            success = false;
        }

        // Done: Close the stream
        try {
            os.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            success = false;
        }

        return success;
    }
    
    /*
     * Method to backup data to default location
     */
    public static boolean  RestoreSingleEvalData(Evaluation empEval, String parentPath) {
        
        // Return false, if not valid state
        if (empEval == null) {
            return false;
        }
        
        // Open file in overwrite mode
        // Get the output stream for file to keep writing the data
        boolean success = true;
        InputStream is = null;
        try {
            File fs = AppUtils.getEvalDataFile(empEval, parentPath);           
            is = new FileInputStream(fs);
            success = empEval.importDataFileHasHeader(is);            
        } catch (Exception ex) {
            ex.printStackTrace();
            success = false;
        }

        // Done: Close the stream
        try {
            is.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            success = false;
        }

        return success;
    }
    
    /*
     * Method to backup data to default location
     */
    public static boolean  BackupData(ViewMode dataType, String[] dataHeader, 
                DataManager dataManager, String dataPath) {
        
        // Return false, if not valid state
        if (dataManager == null || 
            dataManager.masterData == null ||
            dataManager.columnsData == null) {
            return false;
        }
        
        // Return false, if there is not data or columns
        if (dataManager.masterData.isEmpty() || 
                dataManager.columnsData.isEmpty()){
            return false;
        }
        
        // Open file in overwrite mode
        // Get the output stream for file to keep writing the data
        boolean success = true;
        String fileName = getDataFileName(dataType, dataPath);
        //System.out.println("Filename: " + fileName);
        BufferedWriter bufferedWriter = null;
        try {
            String temps = fileName;
            if (dataPath == null) {
                temps = AppUtils.class.getClassLoader().getResource(fileName).getPath();
            }
            //System.out.println("temps returned by classLoader: " + temps);
            File fs = new File(temps);
           
            OutputStream os = new FileOutputStream(fs);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(os));

            StringBuilder sb = new StringBuilder();
            // Build the heaader
            sb.setLength(0);
            for (int i = 0; i < dataHeader.length; i++) {
                sb.append(dataHeader[i]);
                // if not last column add "," otherwise new-line
                if (i != dataHeader.length-1) {
                    sb.append(",");
                } else {
                    sb.append("\n");
                }
                //String tempStr = sb.toString();
                //System.out.println(tempStr);
            }
            // Write the header
            bufferedWriter.write(sb.toString());
            bufferedWriter.flush();
            
            // Get the data/rows
            if (dataManager.filteredData != null) {
                Iterator<String[]> iter1 = dataManager.filteredData.iterator();
                //String[] rowStrings = dataManager.filteredData.get(i);
                sb.setLength(0);
                while (iter1.hasNext()) {
                    String[] colValues = iter1.next();
                    for (int i = 0; i < colValues.length; i++) {
                        //String tempStr = sb.toString();
                        //System.out.println(tempStr);

                        sb.append(colValues[i]);
                        // if not last column add "," otherwise new-line
                        if (i != colValues.length-1) {
                            sb.append(",");
                        }
                        //tempStr = sb.toString();
                        //System.out.println(tempStr);
                    }
                    sb.append("\n"); // should I replace it with streamWriter's newLine() method call while writing
                    //String tempStr = sb.toString();
                    //System.out.println(tempStr);
                    // Write the rows to the output stream
                    bufferedWriter.write(sb.toString());
                    sb.setLength(0); // clear it for next
                } // all rows
            }
            // Write the rows to the output stream
            //bufferedWriter.write(sb.toString());
            bufferedWriter.flush();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            success = false;
        }

        // Done: Close the stream
        try {
            bufferedWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            success = false;
        }

        return success;
    }
    
    /*
     * Method to resore data from default location
     */
    public static boolean  RestoreData(ViewMode dataType, String[] dataHeader, 
                DataManager dataManager, String dataPath) {

        // reset it to null
        dataManager.masterData = null;
        dataManager.columnsData = null;

        // Get the filename based on type
        String fileName = getDataFileName(dataType, dataPath);
        String fileType = "CSV";

        // Try to read data
        try {
            //String temps = fileName;
            URL url = null;
            if (dataPath == null) {
                // classpath based filename
                url = AppUtils.class.getClassLoader().getResource(fileName);
            } else {
                // absolute path filename
                File myFile = new File(fileName);
                url = myFile.toURI().toURL();
            }
            //System.out.println("temps returned by classLoader: " + temps);

            DataSourceReader dsr1 = null;
            //dsr1 = new FileSource(url.openStream(), new Format(fileType));
            //InputStream is = dsr1.getInputStream();
            //byte[] content = new byte[is.available()];
            //is.read(content);
            //is.close();
            //System.out.println("**********************************"+ new String(content));
 
            dsr1 = new FileSource(url.openStream(), new Format(fileType));
            CSVDataSource ds1 = new CSVDataSource(dsr1, dataHeader);
            System.out.println("ds1.data.size is " + ds1.getData().size());
            dataManager.masterData = ds1.getData();
            System.out.println("master.data.size " + dataManager.masterData.size());
            //dataManager.filteredData = ds1.getData();
            //System.out.println("filtered.data.size " + dataManager.filteredData.size());
            dataManager.columnsData = ds1.getColumns();
            return true;
        } catch(Exception ex) {
            MessageBox mb = new MessageBox("Error in restoring data..", MessageBoxType.OK_ONLY);
            mb.showAndWait();
        
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean GetEmployeeData(BackupRestoreMode backupRestoreMode, DataManager dataManager, String dataPath) {
       if (backupRestoreMode == BackupRestoreMode.BACKUP) {
           return BackupData(ViewMode.EMPLOYEE, AppUtils.dataHeaderEmployee, dataManager, dataPath);
       } else {
           return RestoreData(ViewMode.EMPLOYEE, AppUtils.dataHeaderEmployee, dataManager, dataPath);
       }
   }

  public static boolean GetEmployerData(BackupRestoreMode backupRestoreMode, DataManager dataManager, String dataPath) {
       if (backupRestoreMode == BackupRestoreMode.BACKUP) {
           return BackupData(ViewMode.EMPLOYER, AppUtils.dataHeaderEmployer, dataManager, dataPath);
       } else {
           return RestoreData(ViewMode.EMPLOYER, AppUtils.dataHeaderEmployer, dataManager, dataPath);
       }
   }

   public static boolean GetEvalResultData(BackupRestoreMode backupRestoreMode, DataManager dataManager, String dataPath) {
       if (backupRestoreMode == BackupRestoreMode.BACKUP) {
           return BackupData(ViewMode.REPORT_CONCISE, AppUtils.dataHeaderEvalReport, dataManager, dataPath);
       } else {
           return RestoreData(ViewMode.REPORT_CONCISE, AppUtils.dataHeaderEvalReport, dataManager, dataPath);
       }
   }
   
   public static boolean  GetFieldPlacementRestoreData(BackupRestoreMode backupRestoreMode, DataManager dataManager, String dataPath) {
       
       if (backupRestoreMode == BackupRestoreMode.BACKUP) {
           //return BackupData(ViewMode.FIELD_PLACEMENT, dataHeader, dataManager, dataPath);
       } else {
           //return RestoreData(ViewMode.FIELD_PLACEMENT, dataHeader, dataManager, dataPath);
       }
       
       return false;
   }

   
}
