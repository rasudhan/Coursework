//********************************************************************
//* 																 *
//* 				CIS611 Spring 2017 Anand RameshKannan			 *
//* 																 *
//* 					Program Assignment PA03 					 *
//* 																 *
//* 				Program to Find Min and Max Average 			 *
//* 																 *
//* 					Date Created: 02.14.2017 					 *
//*					Saved in: AnandRameshKannanPA0301.java		 	 *
//* 																 *
//********************************************************************
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
public class AnandRameshKannanPA0301 {

	public static void main(String[] args) {
		
		// Declare variables
		String orderID;
		
		int i=0,j=0;
		int userChoice=0,userAttempts=0;
		double[] avg = new double[50];
		double sum=0;
		double average,minAvg,maxAvg;
		String[] splitStr=new String[100];
		DecimalFormat df=new DecimalFormat("#.###");
		
			
		//Obtain input from the user as long as he wishes to provide one
		while(userChoice==0) {
			try {
				orderID=JOptionPane.showInputDialog(null,"Enter Order ID -> ");
				String input = JOptionPane.showInputDialog("Enter Order " + orderID + " (4)Items’ Prices -> ");
				splitStr = input.split(",");
				double[] prices = new double[splitStr.length];
				for (i = 0; i <splitStr.length; i++) {
				    prices[i] = Double.parseDouble(splitStr[i]);
				    sum += prices[i];
			    }
				if(prices.length!=4) { //Check if user inputs exactly 4 items else ask them to do so
					throw new Exception();
				}
				else {
					userAttempts++; 
					average=sum/4;
					avg[j++]=average;
					sum=0;
				}
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Please Enter Valid Order ID and 4 Item Prices(separated by a comma)");
			}
			
			userChoice=JOptionPane.showConfirmDialog(null, "Would you like to continue?");
		}
		
		minAvg=avg[0];
		maxAvg=avg[0];
		
		//Find minimum and maximum average
		for(j=1;j<userAttempts;j++) {
			if(avg[j]>maxAvg)
				maxAvg=avg[j];
			if(avg[j]<minAvg)
				minAvg=avg[j];
		}
		
		//Print the output to the user
		JOptionPane.showMessageDialog(null, "The min_average = "+ df.format(minAvg)+ " and the max_average = "+df.format(maxAvg));
	
		
	}

}
