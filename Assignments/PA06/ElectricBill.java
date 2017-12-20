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
//*					Saved in: ElectricBill.java		 	 
//* 																 
//**************************************************************************
package ElectricBill;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

//add class template

public class ElectricBill extends Bill{


	final static double BASE_RESIDENTIAL_CUST = 6.75;
	final static double BASE_COMMERCIAL_CUST = 10.75;
	private Customer[] customers=new Customer[6];
	DecimalFormat df= new DecimalFormat("#.##");
	
	public ElectricBill() {
		super();
	}

	@Override
	protected double getSum() {
		
		double sum=0;
		for(int i=0;i<Customer.getNumberOfCustomers();i++) {
			sum+=customers[i].getBillAmount();
		}
		return sum;

	}

	@Override
	protected double getAVG() {
		
		return getSum()/Customer.getNumberOfCustomers();
		
	}

	@Override
	protected void computeBill(Customer customer) {
		// TODO Auto-generated method stub
		double totalAmount;
		
		if(customer.getStatus()==0) {
			totalAmount=residentialElecBill(customer.getNoOfKWH(),customer.getBillMonth());
		}
		else {
			totalAmount=commercialElecBill(customer.getNoOfKWH(),customer.getBillMonth());
		}
		customer.setBillAmount(totalAmount);

	}

	protected  boolean addCustomer(int custID, String fName, String lName, int status,int noOfKWH, int month, double billAmount) {
		// TODO Auto-generated method stub
		
		int i=Customer.getNumberOfCustomers();
		customers[Customer.getNumberOfCustomers()]=new Customer(custID,fName,lName,status,noOfKWH,month,billAmount);
		computeBill(customers[i]);
		this.setCustomers(customers);
		return true;
		
	}

	
	@Override
	protected boolean writeToFile(String fileName) {
		// TODO Auto-generated method stub
	
		String message="";
		//Header (Optional)
		//message="CustomerID FirstName    LastName     Customer Status NoOfKWH Month Bill Amount"+System.lineSeparator();
		message+=toString();
		
		//Summary of sum and average
		//message+=System.lineSeparator()+"Sum of Bill Amount : $"+df.format(getSum())+
    		//	"  Average Bill Amount : $"+df.format(getAVG());
      	java.io.File file = new java.io.File(fileName);
      	       
      	
      	// Create a file and write to it
        java.io.PrintWriter output = null;
		try {
			output = new java.io.PrintWriter(file);
			output.println(message);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "File Issue");
		}
        
        //Close the file    	
    	output.close();
		return true;
		
	}

	// override toString method
	@Override
	public String toString() {
		
		String custDetail = "";
		Customer[] customers=this.getCustomers();
		for(int i=0;i<Customer.getNumberOfCustomers();i++) {
			custDetail+=customers[i].getCustomerID()+",  "+ String.format("%-10s",customers[i].getfName())+",  "+
					String.format("%-10s",customers[i].getlName())+",  "
		         +String.format("%-11s",findStatus(customers[i].getStatus()))+",    "+
		         String.format("%-6s",customers[i].getNoOfKWH())+", "+findMonth(customers[i].getBillMonth())+",  "+df.format(customers[i].getBillAmount())+System.lineSeparator();
		}
		return custDetail;

	}
	
	protected String findStatus(int status) {
		String value=null;
		if(status==0)
			value="Residential";
		else
			value="Commercial";
		return value;
	}
	protected String findMonth(int month) {
		String monVal = null;
		switch(month) {
		case 1: monVal="Jan";break;
		case 2: monVal="Feb";break;
		case 3: monVal="Mar";break;
		case 4: monVal="Apr";break;
		case 5: monVal="May";break;
		case 6: monVal="Jun";break;
		case 7: monVal="Jul";break;
		case 8: monVal="Aug";break;
		case 9: monVal="Sep";break;
		case 10: monVal="Oct";break;
		case 11: monVal="Nov";break;
		case 12: monVal="Dec";break;
		}
		return monVal;
		
	}


private double residentialElecBill(int kwh,int month){
	
	double charges;
	if((month==6) || (month==7) || (month==8) ||(month==9)) { //Summer months
		if((kwh>=500)) {
			charges= 0.09*kwh;
		}
		else {
			charges= 0.04604*kwh;
		}
	}
	else { //Winter Months
		charges= 0.04604*kwh;
	}
	
	return charges+BASE_RESIDENTIAL_CUST;
}

private double commercialElecBill(int kwh,int month){
	
	double charges;
	if((month==6) || (month==7) || (month==8) ||(month==9)) { //Summer months
		charges= 0.06450*kwh;
	}
	else { //Winter Months
		charges= 0.03920*kwh;
	}
	
	return charges+BASE_COMMERCIAL_CUST;
	
}
}


