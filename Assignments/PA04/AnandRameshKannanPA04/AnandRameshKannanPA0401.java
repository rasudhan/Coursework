//********************************************************************
//* 																 *
//* 				CIS611 Spring 2017 Anand RameshKannan			 *
//* 																 *
//* 					Program Assignment PA04 					 *
//* 																 *
//* 				Program to Check Valid Password   	 			 *
//* 																 *
//* 					Date Created: 02.18.2017 					 *
//*					Saved in: AnandRameshKannanPA0401.java		 	 *
//* 																 *
//********************************************************************
import javax.swing.JOptionPane;
public class AnandRameshKannanPA0401 {

	public static void main(String[] args) {
		
		int userChoice=0;
		String password;
		
		while(userChoice==0) {
			
			//Accept input from user
			password=JOptionPane.showInputDialog(null,"Enter a string for password: ");
			
			//Call the method to test password validity and display the result
			JOptionPane.showMessageDialog(null, isValidPassword(password)?"valid password"+" - "+ password:"invalid password"+" - "+ password);
			
			//Ask for user confirmation to continue or terminate the program
			userChoice=JOptionPane.showConfirmDialog(null, "Would you like to continue?");
		}
	}
	
	public static boolean isValidPassword(String s) {
		
		//Regular expression to match the given password criteria
      if(s.matches("^(?=(.*[0-9]){2})(?=.*[a-zA-Z])(?=.*[@#$%!^&+=])[^ \\/]{8}$"))
		return true;
      else
    	 return false;
		
	}

}
