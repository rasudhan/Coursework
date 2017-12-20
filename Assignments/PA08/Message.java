//*************************************************************************
//* 																
//* 				CIS611 Spring 2017 Anand RameshKannan
//* 															
//* 					Program Assignment PA08				 
//* 																 
//* 				Program to View/Insert/Update/Delete records in DataBase
//* 																 
//* 					Date Created: 04.07.2017 					 
//*					Saved in: Message.java		 	 
//* 																 
//**************************************************************************
package PA08;


import java.io.Serializable;

public class Message implements Serializable {

	
	int id;
	String lastName;
	String firstName;
	char mi;
	String address,city,state,telephone;
	String opType;
	
	
	
	public Message(int id, String lastName, String firstName, char mi, String address, String city, String state,
			String telephone,String opType) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.mi = mi;
		this.address = address;
		this.city = city;
		this.state = state;
		this.telephone = telephone;
		this.opType=opType;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", mi=" + mi + ", address="
				+ address + ", city=" + city + ", state=" + state + ", telephone=" + telephone + ", opType=" + opType
				+ "]";
	}

}
