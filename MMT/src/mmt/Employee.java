//package com.mmt.ees.model;
package mmt;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.UUID;


public class Employee {
	private String employeeID;
	private String firstName;
	private String lastName;
	private String emailAdd;
	private String phoneNumber;
	private String cellNumber;
	private String streetAdd;
	private String city;
	private String state;

	private String zipcode;

	//private Employer employer;

	//private ArrayList<Evaluation> evaluations;

	public Employee(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Employee(String[] strings) {

		//"id", "firstName", "lastName", "email", "phone", "cellNumber", "address", "city", "state", "zipCode"
		employeeID  = strings[0];
		firstName   = strings[1];
		lastName    = strings[2];
		emailAdd    = strings[3];
		phoneNumber = strings[4];
		cellNumber  = strings[5];
		streetAdd  = strings[6];
		city = strings[7];
		state = strings[8];
		zipcode = strings[9];
	}

	public Employee(Employee employee, String employeeID) {

		this.employeeID = employeeID;
		this.firstName = employee.getFirstName();
		this.lastName = employee.getLastName();
		this.emailAdd = employee.getEmailAdd();
		this.phoneNumber = employee.getPhoneNumber();
		this.cellNumber = employee.getCellNumber();
		this.streetAdd = employee.getStreetAdd();
		this.city = employee.getCity();
		this.state = employee.getState();
		this.zipcode = employee.getZipcode();
//		this.employer = employee.getEmployer();
//		this.evaluations = employee.getEvaluations();
	
	}

	public Employee(String employeeID) {
		this.employeeID = employeeID;
	}

/*	
	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public ArrayList<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(ArrayList<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}
*/	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAdd() {
		return emailAdd;
	}

	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}

	public String getStreetAdd() {
		return streetAdd;
	}

	public void setStreetAdd(String streetAdd) {
		this.streetAdd = streetAdd;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getEmployeeID() {
		return this.employeeID;
	}
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            
            sb.append(employeeID);
            sb.append(",");
            sb.append(firstName);
            sb.append(",");
            sb.append(lastName);
            sb.append(",");
            sb.append(emailAdd);
            sb.append(",");
            sb.append(phoneNumber);
            sb.append(",");
            sb.append(cellNumber);
            sb.append(",");
            sb.append(streetAdd);
            sb.append(",");
            sb.append(city);
            sb.append(",");
            sb.append(state);
            sb.append(",");
            sb.append(zipcode);

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
