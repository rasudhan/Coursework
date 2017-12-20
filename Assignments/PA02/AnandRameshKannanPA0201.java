//********************************************************************
//* 																 *
//* 				CIS611 Spring 2017 Anand RameshKannan			 *
//* 																 *
//* 					Program Assignment PA02 					 *
//* 																 *
//* 				Program to Calculate Merchandise Bill 			 *
//* 																 *
//* 					Date Created: 02.04.2017 					 *
//*					Saved in: AnandRameshKannanPA0201.java		 	 *
//* 																 *
//********************************************************************
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
public class AnandRameshKannanPA0201 {

	public static void main(String[] args) {
		
		// Declare variables
		double merchandiseCost;
		Integer isMember;
		double memberDiscount=0,scratchcardDiscount,scratchcardDiscountValue;
		double subTotal,salesTax,grandTotal;
		DecimalFormat df=new DecimalFormat("#.##");
		
		//Obtain user-input
		isMember=Integer.parseInt(JOptionPane.showInputDialog(null,"Does customer have store membership?(0-No,1-Yes) ","Membership Details",JOptionPane.QUESTION_MESSAGE));
		merchandiseCost=Double.parseDouble(JOptionPane.showInputDialog(null,"Total Merchandise Cost: ","Merchandise Cost",JOptionPane.QUESTION_MESSAGE));
		
		//Calculate member discount and scratchcard discount
		if(isMember==1) {
			memberDiscount=(merchandiseCost*.05);
		}
		scratchcardDiscount=(int)(Math.random() * 16);
		scratchcardDiscountValue=scratchcardDiscount/100*merchandiseCost;
		
		//Calculate subtotal after all discounts have been applied
		subTotal=merchandiseCost-memberDiscount-scratchcardDiscountValue;
		
		//Calculate Sales-Tax of 7.4% on SubTotal Value
		salesTax=(subTotal*0.074);
		
		//Calculate Grand Total Value
		grandTotal=subTotal+salesTax;
		
		//Display the output bill
		JOptionPane.showMessageDialog(null,
		    "Merchandise Cost:                    $"+df.format(merchandiseCost)+
		  "\nScratch card discount @ "+scratchcardDiscount+"%: $"+df.format(scratchcardDiscountValue)+
			"\nMembership discount (5%) :       $"+df.format(memberDiscount)+
			"\nTotal bill after discount:             $"+df.format(subTotal)+
			"\nSales Tax:                                   $"+df.format(salesTax)+
			"\nTotal Bill Amount:                   $"+df.format(grandTotal));

	}

}
