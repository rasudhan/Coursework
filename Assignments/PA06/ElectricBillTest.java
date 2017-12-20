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
//*					Saved in: ElectricBillTest.java		 	 
//* 																 
//**************************************************************************

package ElectricBill;

import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class ElectricBillTest {
	
	@SuppressWarnings("unused")
	private ElectricBill bill;
	private String fileName;
	
ElectricBillTest(String fileName){
	bill = new ElectricBill();
	this.fileName = fileName;
	
		
}

public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
	
	//ElectricBill bill = new ElectricBill();
	ElectricBillTest myBill = new ElectricBillTest("customers.txt"); 
	
	int type;
	int custID;
	String fName,lName;
	int noOfKWH;
	int month;
	DecimalFormat df=new DecimalFormat("#.##");
  
	//Obtain user input with proper validation
	do {
		while(true) {
			try {
				custID=Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Customer ID: "));
				if(!String.valueOf(custID).matches("^[0-9]{8}$"))
					throw new Exception();
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Please enter the correct Customer ID(8 digits)! Ex:12345678");
				continue;
			}
			break;
		}
		
		while(true) {
			try {
				type=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the type of Customer(0-Residential,1-Commercial): "));
				if(!String.valueOf(type).matches("^[0-1]{1}$"))
					throw new Exception();
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Please enter the correct type of Customer(0-Residential,1-Commercial)");
				continue;
			}
			break;
		}
		while(true) {
			try {
				fName=JOptionPane.showInputDialog(null, "Enter the first name:");
				if(fName.isEmpty())
					throw new Exception();
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Please enter the first name! Ex:Anand");
				continue;
			}
			break;
		}
		while(true) {
			try {
				lName=JOptionPane.showInputDialog(null, "Enter the last name:");
				if(lName.isEmpty())
					throw new Exception();
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Please enter the last name! Ex:Sudhan");
				continue;
			}
			break;
		}
		while(true) {
			try {
				noOfKWH=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the value of energy used(in KWh): "));
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Please enter the correct value of energy used(in KWh): Ex:500");
				continue;
			}
			break;
		}
		while(true) {
			try {
				month=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the month for which billing is to be generated: "));
				if(!(month>=1 && month<=12))
					throw new Exception();
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Please enter the correct month! Ex:3 for March");
				continue;
			}
			break;
		}
		
		//Add Customer details			
		myBill.bill.addCustomer(custID, fName, lName, type, noOfKWH, month,0);
		
				
	}while(Customer.getNumberOfCustomers()!=4);
	
	//Print Customer details in Console for reference
			System.out.println("CustomerID FirstName    LastName     Customer Status NoOfKWH Month Bill Amount");
			System.out.println(myBill.bill.toString());
	
	//Print Customer Summary Details for reference in the Console
	System.out.println("Sum of Bill Amount : $"+df.format(myBill.bill.getSum())+
			"  Average Bill Amount : $"+df.format(myBill.bill.getAVG()));
	
	//Write to File
	if(myBill.bill.writeToFile(myBill.fileName)) {
		JOptionPane.showMessageDialog(null, "File Written Successfully!");
	}
			
	
}//Main method

}//Class 
