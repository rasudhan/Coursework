//*************************************************************************
//* 																
//* 				CIS611 Spring 2017 Anand RameshKannan
//* 															
//* 					Program Assignment PA07				 
//* 																 
//* 				Program to Calculate Electric Bill 
//*					Read and Store it in File (GUI Version)
//* 																 
//* 					Date Created: 03.30.2017 					 
//*					Saved in: Customer.java		 	 
//* 																 
//**************************************************************************


package PA07;

// add class template
public class Customer {

	private int customerID;
	private String fName;
	private String lName;
	private int status, NoOfKWH;
	private int billMonth;
	private double billAmount;

	private static int numberOfCustomers = 0;

	// the default class constructor
	public Customer() {

		this(0,null,null,0,0,0,0);
		
	}

	// add the class overloaded constructor
	
	public Customer(int customerID, String fName, String lName, int status, int noOfKWH, int billMonth,
			double billAmount) {
		
		this.customerID = customerID;
		this.fName = fName;
		this.lName = lName;
		this.status = status;
		this.NoOfKWH = noOfKWH;
		this.billMonth = billMonth;
		this.billAmount = billAmount;
		numberOfCustomers++;
	}

	
	//Getter and Setter methods
	public int getCustomerID() {
		return customerID;
	}

	
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getNoOfKWH() {
		return NoOfKWH;
	}

	public void setNoOfKWH(int noOfKWH) {
		NoOfKWH = noOfKWH;
	}

	public int getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(int billMonth) {
		this.billMonth = billMonth;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public static int getNumberOfCustomers() {
		return numberOfCustomers;
	}

	public static void setNumberOfCustomers(int numberOfCustomers) {
		Customer.numberOfCustomers = numberOfCustomers;
	}

	
	// override the toString() method

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", fName=" + fName + ", lName=" + lName + ", status=" + status
				+ ", NoOfKWH=" + NoOfKWH + ", billMonth=" + billMonth + ", billAmount=" + billAmount + "]";
	}



	



	


}
