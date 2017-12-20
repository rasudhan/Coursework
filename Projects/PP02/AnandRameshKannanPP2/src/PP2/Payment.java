//*************************************************************************
//* 																
//* 				CIS611 Spring 2017 Anand RameshKannan, Jeffrey Cheng			
//* 															
//* 					Program Project PP2 					 
//* 																 
//* 				Program to Validate Credit Card 
//*						and Process Payment for Customers
//* 																 
//* 					Date Created: 03.03.2017 					 
//*					Saved in: Payment.java		 	 
//* 																 
//**************************************************************************
package PP2;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class Payment {

	public static Validation validating;
	public static HashCode hashing;
	public static Customer[] customers;

	// this will check whether a card is valid
	public static Boolean isValidCard(String number){
		
		return validating.aValidNumber(number);	

	}// end of the isValidCard method

	// creates a hash code for the credit card number to be stored in file
    public static String createHashCode(String number){

		return hashing.getHashCode("4388576018410707");

	}// end of the createHashCode method


     // it adds a new customer to the array of customers once the payment was successful
 	 public static void addCustomer(Customer customer){
 		 
 		 customers[Customer.numCustomers]=new Customer();
 		 customers[Customer.numCustomers-1]=customer;
 		 
 	 } // end of the addCustomer method


	// it displays the payments AVG, MAX payment, and MIN payment,
	// only for accepted payments with valid cards
	public static void displayStat(){

		double minAmount,maxAmount,avgAmount,sum;
		String message;
		minAmount=customers[0].getAmount();
		maxAmount=minAmount;
		avgAmount=minAmount;
		sum=minAmount;
		DecimalFormat df = new DecimalFormat("#.##");
		
		
		//Calculating Min, Max and Average
		for(int i=1;i<Customer.numCustomers;i++) {
			if(customers[i].getAmount()<minAmount) {
				minAmount=customers[i].getAmount();
			}
			else if(customers[i].getAmount()>maxAmount) {
				maxAmount=customers[i].getAmount();
			}
			sum+=customers[i].getAmount();
		}
		avgAmount=sum/(Customer.numCustomers);
		message="MINIMUM AMOUNT = $"+minAmount+"\n"+"MAXIMUM AMOUNT = $"+maxAmount+"\n"+"AVERAGE AMOUNT = $"+df.format(avgAmount)+"\n";
		JOptionPane.showMessageDialog(null, message,"CUSTOMER SPEND STATISTICS", JOptionPane.INFORMATION_MESSAGE);
		
		
	}// end of the displayStat method


	// write data to file, the credit card number should be encrypted
	// using one-way hash method in the Hashing class
    public static void writeToFile() throws FileNotFoundException { 

    	String fileName = "Customer"  + ".txt";
      	java.io.File file = new java.io.File(fileName);
       
        // Create a file
        java.io.PrintWriter output = new java.io.PrintWriter(file);
        
        //Retrieve Customer data from array
    	String message="";
    	Long cardNumber;
    	for(int i=0;i<Customer.numCustomers;i++) {
    		cardNumber=customers[i].getCard().getNumber();
    		message=customers[i].fileString()+" "+hashing.getHashCode(String.valueOf((cardNumber)));
    		output.println(message);
    	}
    	output.close();
    	
    } // end of the writeToFile method


	// the main entry method of the program that will get data from user and
	// perform the business logic
	public static void main(String[] args) throws Exception{

		hashing = new HashCode();
		validating = new  Validation();
		customers = new Customer[10];
	
		int id;
		String fName = null, lName = null;
		double amount=0;
		String cardNumber,expDate;
		Customer theCustomer;
		
		//Obtain Customer Data with validation
		do {
			while(true) {
				try {
					id=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Customer ID: "));
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Please enter a valid number! Ex:5");
					continue;
				}
				break;
			}
			if(id==0) {
				JOptionPane.showMessageDialog(null, "Thanks for using the system! ...");
				break;
			}
			while(true) {
				try {
					fName=JOptionPane.showInputDialog(null,"Enter Customer First Name: ");
					lName=JOptionPane.showInputDialog(null,"Enter Customer Last Name: ");
					if(fName.isEmpty() || lName.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please enter first and last name!");
						continue;
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Please enter first and last name!");
					continue;
				}
				
				
				break;
			}
			
			while(true) {
				try {
				amount=Double.parseDouble(JOptionPane.showInputDialog(null,"Enter the Amount (in dollars): "));
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Please enter the correct amount! Ex:50.0");
					continue;
				}
				break;
			}
			
			while(true) {
				try{
					cardNumber=JOptionPane.showInputDialog(null, "Enter Credit Card number: ");
					Long cardCheck=Long.parseLong(cardNumber); //Checking if it is a card number
					if(cardNumber.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please enter Card Number!");
						continue;
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Please enter Card Number!");
					continue;
				}
				break;
			}
			
			while(true) {
				try {
					expDate=JOptionPane.showInputDialog(null, "Enter Credit Card Expiry date: (Ex:022018) ");
					if(!expDate.matches("^(0[1-9]|1[0-2])([0-9]{4})$")) {
						JOptionPane.showMessageDialog(null, "Enter a valid expiry date! Ex:022018 ");
						continue;
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Enter a valid expiry date! Ex:022018");
					continue;
				}
				break;
			}
			
			if(isValidCard(cardNumber)) {
				JOptionPane.showMessageDialog(null, "Credit Card Validation Successful!");
			}
			else {
				JOptionPane.showMessageDialog(null, "Invalid Card number! ");
				continue;
			}
			theCustomer= new Customer(fName,lName,id,amount,new CreditCard(Long.parseLong(cardNumber),expDate));
			
			addCustomer(theCustomer);
			
			//generate scratch card*************************
			//(int)(Math.random() * ((upperbound - lowerbound) + 1) + lowerbound);
			int scratchCard = (int) (Math.random() * 16 + 5);
			
			JOptionPane.showMessageDialog(null, "Payment Successful!\n"+theCustomer.toString() + String.format("%s %d %s", "\nScratch Card Discount:", scratchCard, "%"));
			
			//JOptionPane.showMessageDialog(null, "Payment Successful!\n"+theCustomer.toString());
			
		  } while(Customer.numCustomers!=10);
		
		if(Customer.numCustomers==0) {
			JOptionPane.showMessageDialog(null, "Sorry No Customer Entries Found!");
		}
		else {
			displayStat();
			writeToFile();
		}
		
		
		
	}

}
