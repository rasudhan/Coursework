//*************************************************************************
//* 																
//* 				CIS611 Spring 2017 Anand RameshKannan
//* 															
//* 					Program Assignment PA07				 
//* 																 
//* 				Program to Calculate Electric Bill 
//* 					Read and Store it in File (GUI Version)
//* 																 
//* 					Date Created: 03.30.2017 					 
//*					Saved in: UserGUI.java		 	 
//* 																 
//**************************************************************************
package PA07;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class UserGUI extends JFrame implements ActionListener {
	
	 private JLabel labelFN;
	  private JLabel labelLN;
	  private JLabel labelCustID;
	  private JLabel labelEnergy;
	  private JTextField fieldFN;
	  private JTextField fieldLN;
	  private JTextField fieldCustID;	
	  private JTextField fieldEnergy;
	  private JButton SubmitButton;
	  private JButton CloseButton;
	  private JTextArea textArea;
	  private JComboBox custStatus;
	  private JComboBox theMonth;
	  private ElectricBill bill;
	  private JScrollPane jp;
	  
	  public UserGUI() {
		  
		
	    initGUI();
	    doTheLayout();
	    
	    //Register Action Listeners
	    SubmitButton.addActionListener(this);
	    CloseButton.addActionListener(this);
	    
	   

	  } // end of constructor

	  private void initGUI() {
	      
		  labelFN = new JLabel("First Name:");
	      labelLN = new JLabel("Last Name:");
	      labelCustID = new JLabel("Customer ID:");
	      labelEnergy = new JLabel("Energy (kWH):");
	      fieldFN = new JTextField("", 30);
	      fieldLN = new JTextField("", 30);
	      fieldCustID = new JTextField("", 20);
	      fieldEnergy = new JTextField("", 20);
	      textArea = new JTextArea(5, 15);
	      textArea.setEditable(false);
	      textArea.setLineWrap(true);
	      textArea.setWrapStyleWord(true);
	      
	      //New Scroll pane for the textArea	      
	      jp = new JScrollPane(textArea);
	      
	      
	      //Add required options to the comboboxes
	      String[] listStrings = { "","Residential", "Commercial"};
	      custStatus = new JComboBox(listStrings);
	      
	      String[] months= {"","1: Jan","2: Feb","3: Mar","4: Apr","5: May","6: Jun","7: Jul","8: Aug","9: Sep","10: Oct",
	    		                          "11: Nov","12: Dec"};
	      theMonth=new JComboBox(months);
	      
	      
	      //Add required buttons
	      SubmitButton = new JButton("Submit");
	      CloseButton = new JButton("Close");
	      
	      
	      bill = new ElectricBill();

	      //Read data from file
	     try {
			readFromFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	      
	  }// end of creating objects method

	  private void doTheLayout(){

		  //Create panels
	      JPanel top = new JPanel();
	      JPanel center = new JPanel();
	      JPanel bottom = new JPanel();

	      
	      //Add fields to the desired panels
	      top.setLayout( new FlowLayout());
	      top.add(labelFN);
	      top.add(fieldFN);
	      top.add(labelLN);
	      top.add(fieldLN);
	      
	     
	      center.setPreferredSize( new Dimension( 10, 155 ) );
	      center.setLayout( new FlowLayout());
	      center.add(labelCustID);
	      center.add(fieldCustID);
	      center.add(custStatus);
	      center.add(labelEnergy);
	      center.add(fieldEnergy);
	      center.add(theMonth);
	      center.add(new JLabel(""));
	      center.add(SubmitButton);
	      center.add(CloseButton);
	     
	      
	      bottom.setLayout( new BorderLayout());
	      bottom.add(new JLabel("Summary:"),"North");
	      bottom.add(jp,"Center");
	      
	      
	      

	      //Add the panels to the frame
	      this.add(top, "North");
	      this.add(center, "Center");
	      this.add(bottom, "South");

	  }// end of Layout method

	  
	  
	  @Override /** Implement actionPerformed */
	  public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == SubmitButton) {
	    	
	    	int number=PA07.Customer.getNumberOfCustomers();
	    	
	       if(number==6) { //Customers storage validation for a maximum of 6 customers
	    	   
	    	   DecimalFormat df=new DecimalFormat("#.##");
				SubmitButton.setEnabled(false);
				String summary="";
				summary+="CustomerID FirstName    LastName     Status       NoOfKWH Month Bill Amount\n";
		           summary+=bill.toString();
		           summary+="\nSum of Bill Amount : $"+df.format(bill.getSum())+
							"  Average Bill Amount : $"+df.format(bill.getAVG());
		           textArea.setText(summary);
				JOptionPane.showMessageDialog(null, "Customer Array is FULL!");
		   }
	       else {
	    	   	submitButtonClicked();
	       }
	    }
	    else if (e.getSource() == CloseButton)
	      closeButtonClicked();
	  } 
	  
	 private void submitButtonClicked() {
		 	  	 
			
			int type;
			int custID;
			String fName,lName;
			int noOfKWH;
			int month;
			DecimalFormat df=new DecimalFormat("#.##");
		 		
				
				
					try {
						fName=fieldFN.getText();
						if(fName.isEmpty())
							throw new Exception();
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Please enter the first name! Ex:Anand");
						return;
					}
				
				
				
					try {
						lName=fieldLN.getText();
						if(lName.isEmpty())
							throw new Exception();
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Please enter the last name! Ex:Sudhan");
						return;
					}
					
					try {			
						custID=Integer.parseInt(fieldCustID.getText());
						if(!String.valueOf(custID).matches("^[0-9]{8}$"))
							throw new Exception();
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Please enter the correct Customer ID(8 digits)! Ex:12345678");
						return;
					}
					
					try {
						String custType=(String)custStatus.getSelectedItem();
						if(custType=="Residential")
							type=0;
						else if(custType=="Commercial")
							type=1;
						else
							throw new Exception();
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Please select the correct type of Customer(0-Residential,1-Commercial)");
						return;
					}
				
					try {
						noOfKWH=Integer.parseInt(fieldEnergy.getText());
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Please enter the correct value of energy used(in KWh): Ex:500");
						return;
					}
				
				
				
					try {
						String myMonth=(String)theMonth.getSelectedItem();
						if(myMonth=="")
							throw new Exception();
						switch (myMonth) {
						case "1: Jan":				month=1;		break;
						case "2: Feb":				month=2;		break;
						case "3: Mar":				month=3;		break;
						case "4: Apr":				month=4;		break;
						case "5: May":				month=5;		break;
						case "6: Jun":				month=6;		break;
						case "7: Jul":				month=7;		break;
						case "8: Aug":				month=8;		break;
						case "9: Sep":				month=9;        break;
						case "10: Oct":				month=10;		break;
						case "11: Nov":				month=11;		break;
						case "12: Dec":				month=12;		break;
						default: throw new Exception();
						}
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Please select the correct month! Ex:3-March");
						return;
					}
				
				
				
				//Add Customer details			
				bill.addCustomer(custID, fName, lName, type, noOfKWH, month,0);
				
				
							
				//Print Customer Details
				String summary="";
				summary+="CustomerID FirstName    LastName     Status       NoOfKWH Month Bill Amount";
				summary+="\n"+bill.custString();
				
				//Print Customer details in Console for reference
					System.out.println("CustomerID FirstName    LastName     Status       NoOfKWH Month Bill Amount");
					System.out.println(bill.custString());
			
			//Print Customer Summary Details for reference in the Console
					summary+="\nSum of Bill Amount : $"+df.format(bill.getSum())+
							"  Average Bill Amount : $"+df.format(bill.getAVG());
					textArea.setText(summary);
			System.out.println("Sum of Bill Amount : $"+df.format(bill.getSum())+
					"  Average Bill Amount : $"+df.format(bill.getAVG()));
			
			//Write to File
			if(bill.writeToFile("Customers.txt")) {
				JOptionPane.showMessageDialog(null, "File Written Successfully!");
				
				//Reset the input fields
				fieldFN.setText("");
				fieldLN.setText("");
				fieldCustID.setText("");
				fieldEnergy.setText("");
				custStatus.setSelectedItem("");
				theMonth.setSelectedItem("");
				fieldFN.requestFocus();
				
			}
		 
	 }
	 
	 public void readFromFile() throws IOException{ //Read data from the file
		   InputStream fis=new FileInputStream("customers.txt");
	       BufferedReader br=new BufferedReader(new InputStreamReader(fis));
	       String line;
	       DecimalFormat df= new DecimalFormat("#.##");
	       while ((line = br.readLine()) != null) {
	           String words[]=line.split(", ");
	           int status=0,month=0;
	           if(words[3].equals("Residential"))
	        	   status=0;
	           else
	        	   status=1;
	           switch (words[5]) {
				case "Jan":				month=1;		break;
				case "Feb":				month=2;		break;
				case "Mar":				month=3;		break;
				case "Apr":				month=4;		break;
				case "May":				month=5;		break;
				case "Jun":				month=6;		break;
				case "Jul":				month=7;		break;
				case "Aug":				month=8;		break;
				case "Sep":				month=9;        break;
				case "Oct":				month=10;		break;
				case "Nov":				month=11;		break;
				case "Dec":				month=12;		break;
	           }
	           
	           //Add the customer to the array
	           bill.addCustomer(Integer.parseInt(words[0]), words[1], words[2], status, Integer.parseInt( words[4]),month ,Double.parseDouble( words[6]));
	           String summary="Data Read from file 'customers.txt'\n";
	           summary+="CustomerID FirstName    LastName     Status       NoOfKWH Month Bill Amount\n";
	           summary+=bill.toString();
	           summary+="\nSum of Bill Amount : $"+df.format(bill.getSum())+
						"  Average Bill Amount : $"+df.format(bill.getAVG());

	           //Print details on the textArea
	           textArea.setText(summary);
	           }
	 }
	 
	 private void closeButtonClicked() {
		 JOptionPane.showMessageDialog(null,"Thanks for using the program!\nGoodbye! :)");
		 System.exit(0);
	 }


	public static void main(String[] args) {
		UserGUI frame = new UserGUI();
	    frame.setTitle("Electric Bill");
	    frame.pack();
	    frame.setLocationRelativeTo(null); // Center the frame
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    

	}

}
