//*************************************************************************
//* 																
//* 				CIS611 Spring 2017 Anand RameshKannan, Jeffrey Cheng			
//* 															
//* 					Program Project PP2 					 
//* 																 
//* 				Class to store Customer details 
//*						
//* 																 
//* 					Date Created: 03.03.2017 					 
//*					Saved in: Customer.java		 	 
//* 																 
//**************************************************************************
package PP2;


public class Customer {
	
	private int id;
	private String fName, lName;
	private double amount;
	private CreditCard card;
	static int numCustomers=0;
	
	public Customer(String fName, String lName, int id, double amount, CreditCard card) {
		
		this.fName = fName;
		this.lName = lName;
		this.id = id;
		this.amount = amount;
		this.card = card;
		
	}

	public Customer() {
		this(null,null,0,0,new CreditCard());
		numCustomers++;
		// TODO Auto-generated constructor stub
	}

	// Getters/Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
	}

	@Override
	public String toString() {
		return "Customer id: " + id + "\nFirst Name: " + fName + "\nLast Name: " + lName + 
				"\nAmount: $" + amount + "\nCard Details below\n" + card.toString();
				
	}
	
	public String fileString() {
		return "Customer id: " + id + ", fName: " + fName + ", lName: " + lName + 
				", Amount: $" + amount + ", Card Number: ";
	}
	
	

}
