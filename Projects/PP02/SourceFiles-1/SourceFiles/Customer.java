package PP2;


public class Customer {
	
	private int id;
	private String fName, lName;
	private double amount;
	private CreditCard card;
	
	public Customer(String fName, String lName, int id, double amount, CreditCard card) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.id = id;
		this.amount = amount;
		this.card = card;
		
	}
	
	// add public setter/getter methods, and also the toString method

}
