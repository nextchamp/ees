//package com.mmt.ees.model;
package mmt;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;


public class Employer {
	private String employerID ;
	private String companyName ;
	private String streetAddress ;
	private String companyCity ;
	private String companyState ;
	private String zipCode ;
	private String phoneNumber ;
	private String emailAddress ;
	private String contactPerson ;
	
	/*
	 * EmployeeID(String) is the key 
	 */
	private Map<String,Employee> employeeMap ;
	
	public Employer() {
		employeeMap = new HashMap<String,Employee>() ;
	}
	public Employer(Employer employer, String employerID){
		this();
		this.employerID = employerID;
		this.companyName = employer.getCompanyName();
		this.streetAddress = employer.getStreetAddress();
	    this.companyCity = employer.getCompanyCity();
		this.companyState = employer.getCompanyState();
		this.zipCode = employer.getZipCode();
		this.phoneNumber = employer.getPhoneNumber();
		this.emailAddress = employer.getEmailAddress();
	    this.contactPerson = employer.getContactPerson();
	}
	
	public Employer(String employerID) {
		this();
		this.employerID = employerID;
	}
	/**
	 * 
	 */
	public Employer(String companyName, String contactPerson) {
		this() ;
		this.companyName = companyName ;
		this.contactPerson = contactPerson ;
	}
	
	public String getEmployerID() {
		return employerID;
	}
	
	public String getCompanyState() {
		return companyState;
	}
	public void setCompanyState(String companyState) {
		this.companyState = companyState;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getemployerID() {
		return employerID ;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCompanyCity() {
		return companyCity;
	}
	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}
	
	public void hireEmployee(Employee e) {
		if(employeeMap.get(e.getEmployeeID())==null) {
			employeeMap.put(e.getEmployeeID(),e) ;
		}
		else {
			// TODO: throw exception 
		}
		
	}
	
	public void removeEmployee(Employee e) {
		employeeMap.remove(e.getEmployeeID());
	}
	
	public boolean hasEmployee(Employee e){
		return (employeeMap.get(e.getEmployeeID())!=null) ;
	}
	public int getEmployeeCount() {
		return this.employeeMap.size();
	}
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            
            sb.append(employerID);
            sb.append(",");
            sb.append(companyName);
            sb.append(",");
            sb.append(streetAddress);
            sb.append(",");
            sb.append(companyCity);
            sb.append(",");
            sb.append(companyState);
            sb.append(",");
            sb.append(zipCode);
            sb.append(",");
            sb.append(phoneNumber);
            sb.append(",");
            sb.append(emailAddress);
            sb.append(",");
            sb.append(contactPerson);

            return sb.toString();
        }
        
        public boolean exportData(OutputStream os) throws IOException {
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(os));            
            wr.write(toString());
            wr.write("\n");
			wr.flush();
            return true;
	}
}
