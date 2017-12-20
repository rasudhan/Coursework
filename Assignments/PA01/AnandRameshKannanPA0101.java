//********************************************************************
//* 																 *
//* 				CIS611 Spring 2017 Anand RameshKannan			 *
//* 																 *
//* 					Program Assignment PA01 					 *
//* 																 *
//* 				Program to Calculate Merchandise Bill 			 *
//* 																 *
//* 					Date Created: 01.27.2017 					 *
//*					Saved in: AnandRameshKannanPA0101.java		 	 *
//* 																 *
//********************************************************************
import java.text.DecimalFormat;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class AnandRameshKannanPA0101 {

	public static void main(String[] args) {
		// Declare Variables
		float billamount;
		float storediscount;
		float salestax;
		float subtotal,total;
		DecimalFormat df=new DecimalFormat("#.##");
		
		//Obtain User Input - Bill Amount
		Scanner myInput = new Scanner(System.in);
		System.out.print("Enter the bill amount : ");
		billamount=myInput.nextFloat();
		
		//Calculate the bill details
		storediscount=(float) (billamount*0.15);
		subtotal=billamount-storediscount;
		salestax=(float) (subtotal*0.074);
		total=subtotal+salestax;
		
		//Display the bill details(in Console Window)
		System.out.format("Bill amount:     $%.2f\n",billamount);
		System.out.format("Store discount:  $%.2f\n",storediscount);
		System.out.format("Sales Tax:       $%.2f\n",salestax);
		System.out.format("Total:	        $%.2f\n",total);
		
		//Display the bill details(in JOptionPane)	
		JOptionPane.showMessageDialog(null, "Bill amount:     $"+ df.format(billamount)+
				"\nStore discount:   $"+df.format(storediscount)+
				"\nSales Tax:          $"+df.format(salestax)+
				"\nTotal:     	              $"+df.format(total));
	}

}
