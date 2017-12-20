//*************************************************************************
//* 																
//* 				CIS611 Spring 2017 Anand RameshKannan, Jeffrey Cheng			
//* 															
//* 					Program Project PP2 					 
//* 																 
//* 				Program to Hash the Credit Card numbers 
//*						
//* 																 
//* 					Date Created: 03.03.2017 					 
//*					Saved in: HashCode.java		 	 
//* 																 
//**************************************************************************
package PP2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCode {
	
	
   // creates hash good to a given long number in a String format	
   public String getHashCode(String number){
    	
        MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        md.update(number.getBytes());
        
        byte[] byteData = md.digest();
        
       //call convert the byte to hex format method, and return the String Hex 
    	return convertToHexString(byteData);
    	
    }
   
 //converts the byte to hex format method code
   private String convertToHexString(byte[] byteData ){
	 
    StringBuffer hexString = new StringBuffer();
   	for (int i=0;i<byteData.length;i++) {
   		String hex=Integer.toHexString(0xff & byteData[i]);
  	     	if(hex.length()==1) hexString.append('0');
  	     	hexString.append(hex);
   	}
   
   	return hexString.toString();
   
   }
   
}