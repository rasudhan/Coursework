//*************************************************************************
//* 																
//* 				CIS611 Spring 2017 Anand RameshKannan
//* 															
//* 					Program Assignment PA09				 
//* 																 
//* 				Program to Read a file and Extract Product name and price 
//*					          Store it in File (GUI Version)
//* 																 
//* 					Date Created: 03.30.2017 					 
//*					Saved in: AnandRameshKannanPA0901.java		 	 
//* 																 
//**************************************************************************
package PA09;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class AnandRameshKannanPA0901 {

	
	  public static void main(String[] args) {
	    
	
		  BufferedReader br = null;
		  File myFile = null;
		  String fileName="RetailStore.txt"; //Uses the default file if user does not select a file!
		  String items = "PRODUCT DETAILS";
		  
		  //Asks the user to select a file
		  JFileChooser fileChooser = new JFileChooser();
		  
	      if (fileChooser.showOpenDialog(null)
	          == JFileChooser.APPROVE_OPTION) {
	        // Get the selected file
	       myFile = fileChooser.getSelectedFile();
	      }
	     
	      //Check if file is selected by the user
	      try {
		      if(myFile.exists())
			      fileName=myFile.toString();
	      }
	      catch(Exception ex) {
	    	  JOptionPane.showMessageDialog(null, "You did not select a file! \nThe default file is being used!");
	      }
	     
		  //Regex Patterns for product title and price
		  String productString = "\\b(?:(?<=title=\").*?(?=\" href=))";
		  String priceString = "(?<=Suggested Retail Price:).*?(?=\")";
 
		  
		  Pattern productPattern = Pattern.compile(productString);
		  Pattern pricePattern = Pattern.compile(priceString);
	      Matcher matcher;
	      Matcher productMatcher,priceMatcher;
		  
	      //Read Data from the File
			try {
	 
				String line;
	 			br = new BufferedReader(new FileReader(fileName));
	 
				while ((line = br.readLine()) != null) {
							
					productMatcher= productPattern.matcher(line);
					priceMatcher= pricePattern.matcher(line);
					
					//Obtain the product details only if it has a price value
					while(productMatcher.find() && priceMatcher.find()) {
						System.out.println(productMatcher.group());
						items+="\nTitle: "+productMatcher.group();
										
						System.out.println(priceMatcher.group());
						items+=", Suggested Retail Price: "+priceMatcher.group();
					}
				}
	 
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			items.replaceAll("\n", "");
			
			//Display PRODUCT DETAILS 
			JOptionPane.showMessageDialog(null, items);
			
			
			//Write PRODUCT DETAILS to a text file
			try {
	            File newTextFile = new File("matcherout.txt");

	            FileWriter fw = new FileWriter(newTextFile);
	            BufferedWriter bw = new BufferedWriter(fw);
	            
	            bw.write(items);
	            bw.close();

	        } catch (IOException iox) {
	            //do stuff with exception
	            iox.printStackTrace();
	        }
			
		
	}

	
}