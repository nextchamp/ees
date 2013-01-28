//package com.mmt.ees.model;
package mmt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class Evaluation {
	
	private String evaluationNumber ;
	private String employeeNumber ;
	private String employerNumber ;
	private String evaluationDate ;
	private String nxtEvalDate ;
	private int workQualityScore ;
	private String workQualityComments ;
	private int workHabitsScore ;
	private String workHabitsComments ;
	private int jobKnowledgeScore ;
	private String jobKnowledgeComments ;
	private int behaviorScore ;
	private String behaviorComments ;
	private double averageScore ;
	private int overallProgressScore ;
	private String overallProgressComments ;
	private boolean employmentRecommendation ;
	
        public Evaluation(String employeeId) {
            employeeNumber = employeeId;
            this.importData();
        }

        public Evaluation(String[] strings) {
            this.InitFromStrings(strings);
        }

        private void InitFromStrings(String[] strings) {
            //{"#id","employeeId", "employerId", "evaluationDate", "nextEvaluationDate", "qualityScore", "qualityComments", "habitScore", "habitComment", "knowledgeScore", "knowledgeComments", "behaviorScore", "behaviorComment", "overallScore", "overallComment", "recommendation"};
            evaluationNumber = strings[0];
            employeeNumber = strings[1];
            employerNumber = strings[2];
            evaluationDate = strings[3];
            nxtEvalDate = strings[4];
            workQualityScore = Integer.parseInt(strings[5]);
            workQualityComments = strings[6];
            workHabitsScore = Integer.parseInt(strings[7]);
            workHabitsComments = strings[8];
            jobKnowledgeScore = Integer.parseInt(strings[9]);
            jobKnowledgeComments = strings[10];
            behaviorScore = Integer.parseInt(strings[11]);
            behaviorComments = strings[12];
            averageScore = Double.parseDouble(strings[13]);
            overallProgressScore = Integer.parseInt(strings[14]);
            overallProgressComments = strings[15];
            employmentRecommendation = Integer.valueOf(strings[16]) == 1;
            //averageScore.setText(obj[0]);
            this.calcAverageScore();
        }
        
        @Override
        public String toString() {
            //{"#id","employeeId", "employerId", "evaluationDate", "nextEvaluationDate", "qualityScore", "qualityComments", "habitScore", "habitComment", "knowledgeScore", "knowledgeComments", "behaviorScore", "behaviorComment", "overallScore", "overallComment", "recommendation"};
            String formattedAvgScore = String.format("%.2f", averageScore);
            StringBuilder sb = new StringBuilder();
            sb.append(evaluationNumber);
            sb.append(",");
            sb.append(employeeNumber);
            sb.append(",");
            sb.append(employerNumber);
            sb.append(",");
            sb.append(evaluationDate);
            sb.append(",");
            sb.append(nxtEvalDate);
            sb.append(",");
            sb.append(workQualityScore);
            sb.append(",");
            sb.append(workQualityComments);
            sb.append(",");
            sb.append(workHabitsScore);
            sb.append(",");
            sb.append(workHabitsComments);
            sb.append(",");
            sb.append(jobKnowledgeScore);
            sb.append(",");
            sb.append(jobKnowledgeComments);
            sb.append(",");
            sb.append(behaviorScore);
            sb.append(",");
            sb.append(behaviorComments);
            sb.append(",");
            sb.append(formattedAvgScore);
            sb.append(",");
            sb.append(overallProgressScore);
            sb.append(",");
            sb.append(overallProgressComments);
            sb.append(",");
            sb.append(employmentRecommendation);
            
            return sb.toString();        
        }
        
	public String getEvaluationDate() {
		return evaluationDate;
	}
	public void setEvaluationDate(String evaluationDate) {
		this.evaluationDate = evaluationDate;
	}
	public String getNxtEvalDate() {
		return nxtEvalDate;
	}
	public void setNxtEvalDate(String nxtEvalDate) {
		this.nxtEvalDate = nxtEvalDate;
	}
	public int getWorkQualityScore() {
		return workQualityScore;
	}
	public void setWorkQualityScore(int workQualityScore) {
		this.workQualityScore = workQualityScore;
	}
	public String getWorkQualityComments() {
		return workQualityComments;
	}
	public void setWorkQualityComments(String workQualityComments) {
		this.workQualityComments = workQualityComments;
	}
	public int getWorkHabitsScore() {
		return workHabitsScore;
	}
	public void setWorkHabitsScore(int workHabitsScore) {
		this.workHabitsScore = workHabitsScore;
	}
	public String getWorkHabitsComments() {
		return workHabitsComments;
	}
	public void setWorkHabitsComments(String workHabitsComments) {
		this.workHabitsComments = workHabitsComments;
	}
	public int getJobKnowledgeScore() {
		return jobKnowledgeScore;
	}
	public void setJobKnowledgeScore(int jobKnowledgeScore) {
		this.jobKnowledgeScore = jobKnowledgeScore;
	}
	public String getJobKnowledgeComments() {
		return jobKnowledgeComments;
	}
	public void setJobKnowledgeComments(String jobKnowledgeComments) {
		this.jobKnowledgeComments = jobKnowledgeComments;
	}
	public int getBehaviorScore() {
		return behaviorScore;
	}
	public void setBehaviorScore(int behaviorScore) {
		this.behaviorScore = behaviorScore;
	}
	public String getBehaviorComments() {
		return behaviorComments;
	}
	public void setBehaviorComments(String behaviorComments) {
		this.behaviorComments = behaviorComments;
	}
	public int getOverallProgressScore() {
		return overallProgressScore;
	}
	public void setOverallProgressScore(int overallProgressScore) {
		this.overallProgressScore = overallProgressScore;
	}
	public String getOverallProgressComments() {
		return overallProgressComments;
	}
	public void setOverallProgressComments(String overallProgressComments) {
		this.overallProgressComments = overallProgressComments;
	}
	public boolean isEmploymentRecommendation() {
		return employmentRecommendation;
	}
	public void setEmploymentRecommendation(boolean employmentRecommendation) {
		this.employmentRecommendation = employmentRecommendation;
	}
	public String getEvaluationNumber() {
		return evaluationNumber;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public String getEmployerNumber() {
		return employerNumber;
	}
	public double getAverageScore() {
		return averageScore;
	}

	public double calcAverageScore() {
		return (workQualityScore+workHabitsScore+behaviorScore+jobKnowledgeScore)/5.0 ;
	}

        public boolean exportData() {
            // open file to write
            try {
                String filePath = this.getClass().getResource(AppSettings.DataEvalPath).getFile();
                File dir = new File(filePath);
                dir.mkdirs();
                if (!filePath.endsWith(System.getProperty("file.separator"))) {
                    filePath = filePath + System.getProperty("file.separator");
                }
                
                File file = new File(filePath + employeeNumber + ".txt");
                
                if(!file.exists()) {
                    file.createNewFile();
                }
                
                OutputStream os = new FileOutputStream(file);
                exportData(os);
                return true;
            } catch(Exception ex) {
                ex.printStackTrace();
            }

            return false;
        }
        
        public boolean exportData(OutputStream os) throws IOException {
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(os));            
            wr.write(toString());
			wr.flush();
            return true;
	}

        public boolean importData() {
            // Check the employee number
            if (employeeNumber == null || employeeNumber.isEmpty()) {
                return false;
            }

            try {
                String filePath = this.getClass().getResource(AppSettings.DataEvalPath).getFile();
                File dir = new File(filePath);
                dir.mkdirs();
                if (!filePath.endsWith(System.getProperty("file.separator"))) {
                    filePath = filePath + System.getProperty("file.separator");
                }

                File file = new File(filePath + employeeNumber + ".txt");

                if(!file.exists()) {
                    file.createNewFile();
                }

                InputStream is = new FileInputStream(file);
                importData(is);
                return true;
            } catch(Exception ex) {
                ex.printStackTrace();
            }

            return false;
        }

        public boolean importData(InputStream is) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));            
            String line = br.readLine();
            if ( line != null ) {
                String[] parts = line.split(",");
                this.InitFromStrings(parts);
                
                return true;
            }

            return false;
	}
        
        public boolean exportDataFileHasHeader(OutputStream os, String headerStr) throws IOException {
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(os));
            
            //System.out.println(headerStr);
            //System.out.println(headerStr);
            
            // header
            wr.write(headerStr);
            wr.newLine();
            
            //data
            wr.write(toString());
            wr.newLine();
            
            wr.flush();
            
            return true;
	}

        public boolean importDataFileHasHeader(InputStream is) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));            
            String line = br.readLine();
            if ( line != null ) {
                // This is header, drop.ignore this line
            }
            line = br.readLine();
            if ( line != null ) {
                String[] parts = line.split(",");
                this.InitFromStrings(parts);
                
                return true;
            }

            return false;
	}
        
        public static boolean exportDataFileHasHeader(ArrayList<Evaluation> evaluationList, OutputStream os, String headerStr) throws IOException {
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(os));
            
            // header
            wr.write(headerStr);
            wr.newLine();
            
            for(int i = 0; i < evaluationList.size(); i++) {
                //data
                wr.write(evaluationList.get(i).toString());
                wr.newLine();
            
                wr.flush();
            } // for each evaluation list
            
            return true;
	}
}
