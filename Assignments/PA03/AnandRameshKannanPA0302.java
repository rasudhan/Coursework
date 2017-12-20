//********************************************************************
//* 																 *
//* 				CIS611 Spring 2017 Anand RameshKannan			 *
//* 																 *
//* 					Program Assignment PA03 					 *
//* 																 *
//* 				Program to Eliminate Duplicate Characters 	     *
//* 																 *
//* 					Date Created: 02.14.2017 					 *
//*					Saved in: AnandRameshKannanPA0302.java		 	 *
//* 																 *
//********************************************************************
import javax.swing.JOptionPane;

public class AnandRameshKannanPA0302 {

	public static void main(String[] args) {

		// Declare variables
		String distinctStr = "";
		String input = null,output = null;
		char[] arr = null;
    int i=0,j=0;
    
    //Loop until user provides correct input
	while(true) {
    try {          
		    input=JOptionPane.showInputDialog("Enter the integer array elements: ");
		    output=input.replace(" ","");
		    arr = output.toCharArray();
		    if(!output.matches("^[a-zA-Z]+$")) {  //Checking if user types only Alphabets
			    	throw new Exception();
		    }
		    
		
		    for(i=0;i<arr.length;i++) {
		      for(j=i+1;j<arr.length;j++) {
		        if(arr[i]==arr[j]) {
		          arr[j]='\b';
		        }
		      }
		    }
		    break;
    }
    catch(Exception ex) {
    	JOptionPane.showMessageDialog(null, "Please type only alphabets!");
    }
	}
	//Remove duplicates from the string
   for (char value:arr) {
	   if(value!='\b')
	   distinctStr+=value;
   }
   
   	//Print the output to the user
	 JOptionPane.showMessageDialog(null, "The distinct integer array elements: "+ distinctStr.replaceAll(".(?=.)", "$0 "));


	}

}
