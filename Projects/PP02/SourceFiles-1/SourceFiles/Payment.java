package PP2;

public class Payment {

	public static Validation validating;
	public static HashCode hashing;
	public static Customer[] customers;

	// this will check whether a card is valid
	public static Boolean isValidCard(String number){

		return true;

	}// end of the isValidCard method

	// creates a hash code for the credit card number to be stored in file
    public static String createHashCode(String number){

		return null;

	}// end of the createHashCode method


     // it adds a new customer to the array of customers once the payment was successful
 	 public static void addCustomer(Customer customer){

 	 } // end of the addCustomer method


	// it displays the payments AVG, MAX payment, and MIN payment,
	// only for accepted payments with valid cards
	public static void displayStat(){

	}// end of the displayStat method


	// write data to file, the credit card number should be encrypted
	// using one-way hash method in the Hashing class
    public static void writeToFile(){

    } // end of the writeToFile method


	// the main entry method of the program that will get data from user and
	// perform the business logic
	public static void main(String[] args) {

		hashing = new HashCode();
		validating = new  Validation();
		customers = new Customer[10];

	}

}
