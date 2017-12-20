//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 			customer class has necessary attribute and implements 			*	
//*						Serializable											*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: Customer.java		 	 						*
//* 																 			*
//*******************************************************************************
package finalproject;

import java.io.Serializable;

public class Customer implements Serializable{
	
	private int custID;
	private String firstName;
	private String lastName;
	private String address;
	private String telephone;
	private String email;
	private String username;
	private String password;
	private int extraPoints;
	private Status type;
	
	
	public Customer(int custID, String firstName, String lastName, String address, String telephone, String email, String username, String password, int extraPoints, Status type) {
		super();
		this.custID = custID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
		this.username = username;
		this.password = password;
		this.extraPoints = extraPoints;
		this.type = type;
	}
	
	public Customer () {
		this(0, null, null, null, null, null, null, null, 0, null);
		
	}

	public int getCustID() {
		return custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public int getExtraPoints() {
		return extraPoints;
	}

	public void setExtraPoints(int extraPoints) {
		this.extraPoints = extraPoints;
	}


	public Status getType() {
		return type;
	}

	public void setType(Status type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Customer ID: " + custID  + "\t" + "," + "First Name: " + firstName + "\t" + "," + "Last Name: " + lastName + "\t" + ","  + 
				 "Address: " + address + "\t" +"," + "Telephone: " + telephone + "\t" + "," + "Email: " + email + "\t" + "," + "Username: " + username + "\t" + "," + "Password: "
				+ password + "\t" + "," + "ExtraPoints :" + extraPoints + "\n";
	}

	
	
}
