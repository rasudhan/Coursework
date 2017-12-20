//*************************************************************************
//* 																
//* 				CIS611 Spring 2017 Anand RameshKannan
//* 															
//* 					Program Assignment PA06				 
//* 																 
//* 				Program to Calculate Electric Bill 
//*						and Store it in File
//* 																 
//* 					Date Created: 03.19.2017 					 
//*					Saved in: Bill.java		 	 
//* 																 
//**************************************************************************
//
package ElectricBill;

//add class template

public abstract class Bill {

	private Customer[] customers;


	public Bill() {
		this.customers = new Customer[6];
	}

	// add get/set methods for restricted access data fields
	public Customer[] getCustomers() {
		return customers;
	}

	public void setCustomers(Customer[] customers) {
		this.customers = customers;
	}

	 /** Abstract method getSum */
	  protected abstract double getSum(); // finds the sum of the customers bills

	/** Abstract method getAVG */
	  protected abstract double getAVG(); // finds the average of the customers bills

	  /** Abstract method computeBill */
	  protected abstract void computeBill(Customer customer); // computes the customer bill

	  /** Abstract method get addCustomer */
	  protected abstract boolean addCustomer(int custID, String fName, String lName, int status,int noOfKWH, int month, double billAmount); // creates and adds a customer object to the customers array only if the array is not full.

	  /** Abstract method writeToFile */
	  protected abstract boolean writeToFile(String fileName); // stores customers data to the file
}
