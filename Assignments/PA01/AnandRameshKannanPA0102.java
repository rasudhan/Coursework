//********************************************************************
//* 																 *
//* 				CIS611 Spring 2017 Anand RameshKannan			 *
//* 																 *
//* 					Program Assignment PA01 					 *
//* 																 *
//* 				Program to Display Name and Mobile Number   	 *
//* 																 *
//* 					Date Created: 01.27.2017 					 *
//*					Saved in: AnandRameshKannanPA0102.java		 	 *
//* 																 *
//********************************************************************
import javax.swing.JOptionPane;

public class AnandRameshKannanPA0102 {

	public static void main(String[] args) {
		// Declare variables
		String name,mobilenumber;
		
		//Obtain user input
		name=JOptionPane.showInputDialog("What is your name?");
		mobilenumber=JOptionPane.showInputDialog("What is your mobile number?");
		
		//Display output
		JOptionPane.showMessageDialog(null,"Your Name is: "+name+"\nYour Mobile Number is: ("+
				mobilenumber.substring(0,3)+") "+mobilenumber.substring(3,6)+
				"-"+mobilenumber.substring(6,10));
	}

}
