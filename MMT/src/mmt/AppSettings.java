/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmt;

/**
 *
 * @author Aditya Mishra
 */
public class AppSettings {

    /**
    * This class/enum encapsulates the default path for files.
    * @author Aditya Mishra
    */
   public static String DataRootPath  = "data/";
   public static String DataEvalPath  = "data/evaluation/";
   public static String ConolidatedReportFileName = "evaluation.txt";
   public static String fieldPlacementDataFileName = "fieldPlacement.txt";
   /*
   public static String employeeRestoreDataFilePathName = AppSettings.DataRootPath + "employee.txt";
   public static String employerRestoreDataFilePathName = AppSettings.DataRootPath + "employer.txt";
   public static String evalResultRestoreDataFilePathName     = AppSettings.DataRootPath + "evaluation.txt";
   public static String fieldPlacementRestoreDataFilePathName = AppSettings.DataRootPath + "fieldPlacement.txt";
   public static String nextIdListDataFilePathName = AppSettings.DataRootPath + "nextIdList.txt";
   public static String helpAboutString = "";
   */
   
   public AppSettings() {
      
    }
   
   public static String getEmployeeDataFilePathName(String rootPath) {
	String folderPath = rootPath;
	if (rootPath == null) {
            folderPath = AppSettings.DataRootPath;
	}
	return folderPath + "employee.txt";
    }
    public static String getEmployerDataFilePathName(String rootPath) {
        String folderPath = rootPath;
        if (rootPath == null) {
            folderPath = AppSettings.DataRootPath;
        }
        return folderPath + "employer.txt";
    }
    /*
     * Method to return filename for evaluation result of the employee.
     */
    public static String getEvalResultDataFilePathName(String employeeId, String rootPath) {
            String folderPath = rootPath;
            if (rootPath == null) {
                    folderPath = AppSettings.DataRootPath;
            }
            return folderPath + employeeId + ".txt";
    }
    public static String getEvalResultDataFilePathName(String rootPath) {
            String folderPath = rootPath;
            if (rootPath == null) {
                    folderPath = AppSettings.DataRootPath;
            }
            return folderPath + AppSettings.ConolidatedReportFileName;
    }    
    public static String getFieldPlacementDataFilePathName(String rootPath) {
            String folderPath = rootPath;
            if (rootPath == null) {
                    folderPath = AppSettings.DataRootPath;
            }
            return folderPath + "fieldPlacement.txt";
    }

    public static String getNextIdListDataFilePathName(String rootPath) {
            String folderPath = rootPath;
            if (rootPath == null) {
                    folderPath = AppSettings.DataRootPath;
            }
            return folderPath + "nextIdList.txt";
    }
   
   public String getEmployeeRestoreData() {
       StringBuilder sb = new StringBuilder();
       sb.append(AppSettings.DataRootPath);
       sb.append("employeeData.csv");
       
       return sb.toString();
   }

   public String getEmployerRestoreData() {
       StringBuilder sb = new StringBuilder();
       sb.append(AppSettings.DataRootPath);
       sb.append("employerData.csv");
       
       return sb.toString();
   }
   
   public String getEvalResultRestoreData() {
       StringBuilder sb = new StringBuilder();
       sb.append(AppSettings.DataRootPath);
       sb.append("evaluationResultData.csv");
       
       return sb.toString();
   }
   
   public String getFieldPlacementRestoreData() {
       StringBuilder sb = new StringBuilder();
       sb.append(AppSettings.DataRootPath);
       sb.append("fieldPlacementData.csv");
       
       return sb.toString();
   }
}
