//*************************************************************************
//* 																
//* 				CIS611 Spring 2017 Anand RameshKannan, Jeffrey Cheng			
//* 															
//* 					Program Project PP2 					 
//* 																 
//* 				Class to store Credit Card details 
//*						
//* 																 
//* 					Date Created: 03.03.2017 					 
//*					Saved in: CreditCard.java		 	 
//* 																 
//**************************************************************************
package PP2;

public class CreditCard {
	
	private long number;
	private String expDate;
	
	public CreditCard(long number, String expDate){
		this.number = number;
		this.expDate = expDate;
	}

	public CreditCard() {
		this(0,null);
		// TODO Auto-generated constructor stub
	}

	
	// Getters and Setters
	
	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	@Override
	public String toString() {
		return "CreditCard \nNumber: " + number + "\nExp Date: " + expDate;
	}
	
	
	
}
