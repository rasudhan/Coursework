//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 			for credit card Validation on BookingGUI 						*	
//*																				*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: CardValidation.java		 	 				*
//* 																 			*
//*******************************************************************************
package finalproject;

public class CardValidation {
	
	 // Return true if the card number is valid, otherwise returns false, this method is already implemented
	  public boolean aValidNumber(String n) {

		long number = Long.parseLong(n);
		return  (numLength(number) >= 13) && (numLength(number) <= 16) &&
	        (prefixCheck(number, 4) || prefixCheck(number, 5) ||
	        prefixCheck(number, 6) || prefixCheck(number, 37)) &&
	        (totalEevenNumbers(number) + totalOddNumbers(number)) % 10 == 0;
	  }// end of aValidNumber method

	  //get the sum of even places numbers, Starting from the second digit from right
	  private int totalEevenNumbers(long number) {
		    int digit = 0;
		    int i = 0;
		    int j = 0;
		    int sum = 0;
		    
		    while (number != 0) {
		    	digit= singleDigit(number);
		    	if (i % 2 !=0) {
		    		j = digit * 2;
		    		if (j > 9) {
		    			j = (j % 10) + 1;
		    		}
		    		sum += j;
		    	}
		    	i++;
		    	number /= 10;
		    }
		  return sum;
	  }// end of totalEevenNumbers method

	  // Return the same number if it is a single digit, otherwise, return the sum of
	  // the two digits in this number
	  private int singleDigit(long number) {
		  long digit = 0;
		  int singleDigit;
		  if (number > 0) {
			  digit = number % 10;
			  
		  }
		  singleDigit = (int) digit;
	    return singleDigit;
	  } // end of singleDigit method



	  // Return the sum of odd place digits in number
	  private int totalOddNumbers(long number) {
		    int digit = 0;
		    int i = 0;
		    int sum = 0;
		    
		    while (number != 0) {
		    	digit= singleDigit(number);
		    	if (i % 2 ==0) {
		    		sum += digit;
		    	}
		    	i++;
		    	number /= 10;
		    }
		  return sum;

	  }// end of totalOddNumbers method

	  // Return true if the digit d is a prefix for number
	  private boolean prefixCheck(long number, int d) {
		  boolean status = false;
		  number = numPrefix(number, d);
		  if (number == d) {
			  status = true;
		  }
		  else {
			  number /= 10;
			  if (number == d) {
				  status = true;
			  }
		  }
		 return status;
	  }// end of prefixCheck method


	  // Return the number of digits in this number parameter
	  private int numLength(long number) {
		  
		 return (int) (Math.log10(number)+1);
	    
	  }// end of numLength method

	  // Return the first k number of digits from number, which is either a first digit or first two digits
	  // Depending on the card type
	  private long numPrefix(long number, int k) {
		  while (number > 99) {
			  number /= 10;
			
		  }
		  
	      return number;
	  }// end of numPrefix method

}// end of the class
