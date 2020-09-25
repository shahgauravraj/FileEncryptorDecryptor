package application;

import java.sql.Date;

public class ModelTable {
	String name;
	String gender;
	Date DOB;
	public ModelTable(String name, String gender, Date dOB) {
		this.name = name;
		this.gender = gender;
		DOB = dOB;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	
	
	

}
