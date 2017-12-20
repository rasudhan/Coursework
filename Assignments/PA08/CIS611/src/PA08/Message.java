package PA08;

import java.io.Serializable;

public class Message implements Serializable {

	
	private int id;
	private String lastName,firstName;
	private char mi;
	private String address,city,state,telephone;
	
	
	
	public Message(int id, String lastName, String firstName, char mi, String address, String city, String state,
			String telephone) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.mi = mi;
		this.address = address;
		this.city = city;
		this.state = state;
		this.telephone = telephone;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
