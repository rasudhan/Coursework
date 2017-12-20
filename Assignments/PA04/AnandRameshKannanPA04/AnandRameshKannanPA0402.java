//********************************************************************
//* 																                              
//* 				CIS611 Spring 2017 Anand RameshKannan			            
//* 																                              
//* 					Program Assignment PA04 					                  
//* 																                              
//* 				Program to Read file, sort using selectionsort, Write to file  	         
//* 																                             
//* 					Date Created: 02.18.2017 					                 
//*					Saved in: AnandRameshKannanPA0402.java		 	         
//* 																                             
//********************************************************************

import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;
import java.util.Arrays;

public class AnandRameshKannanPA0402 {
  public static void main(String[] args) throws Exception {

      String[] pName=new String[50];
      Double[] pPrice=new Double[50];
       String filename=JOptionPane.showInputDialog("Enter filename: ");

      readFromFile(filename,pName,pPrice);
      JOptionPane.showMessageDialog(null,"Products before Sorting\n"+Arrays.toString(pName)+"\n"+Arrays.toString(pPrice));
      selectionSort(pName,pPrice);
      JOptionPane.showMessageDialog(null,"Products after Sorting\n"+Arrays.toString(pName)+"\n"+Arrays.toString(pPrice));
      writeToFile(pName,pPrice);

  }
  public static void readFromFile(String filename,String[] pName,Double[] pPrice) throws Exception {


    java.io.FileReader file=null;
    try {     
    // Create a File instance
    file = new java.io.FileReader(filename);
    }
    catch (Exception ex) {
      JOptionPane.showMessageDialog(null,"Please enter a valid file name or file path! \n Ex:products.txt");
      System.exit(0);
    }
    String line;
    BufferedReader bufferedReader = 
                new BufferedReader(file);
      //          String[] pName=new String[50];
    //Double[] pPrice= new Double[50];
    int k=0;
    // Read data from a file
    while ((line=bufferedReader.readLine())!=null) {
      String[] entry=line.split(",");
      
      pName[k]=entry[0];
      pPrice[k]=Double.parseDouble(entry[1]);

      //String firstName = input.next();
      //String lastName = input.next();
     //Double price = input.nextDouble();
      //JOptionPane.showMessageDialog(null,pName[k]+" "+pPrice[k]);
        k++;
    }

  }

  public static void writeToFile(String[] pName,Double[] pPrice) throws Exception{
    String fileName = "sortProducts"  + ".txt";
  	java.io.File file = new java.io.File(fileName);
    if (file.exists()) {
    	JOptionPane.showMessageDialog(null,"\"sortProducts\" File already exists");
        System.exit(0);
    }

    // Create a file
    java.io.PrintWriter output = new java.io.PrintWriter(file);

    for(int i=0;i<pName.length;i++) {
          // Write formatted output to the file
      output.print(pName[i]);
      output.println(","+pPrice[i]);

    }
    JOptionPane.showMessageDialog(null,"\"sortProducts\" File CREATED successfully with sorted values.");
    // Close the file
    output.close();
  }//writeToFile

  public static void selectionSort(String[] pName,Double[] pPrice) {
    
    int i;
    for (i = 0; i < pPrice.length - 1; i++) {
      // Find the minimum in the list[i..list.length-1]
      double currentMin = pPrice[i];
      String currentName = pName[i];
      int currentMinIndex = i;

      for (int j = i + 1; j < pPrice.length; j++) {
        if ( pPrice[j] < currentMin) {
          currentMin = pPrice[j];
          currentName= pName[j];
          currentMinIndex = j;
        }
      }

      // Swap pPrice[i] with pPrice[currentMinIndex] if necessary;
      if (currentMinIndex != i) {
        pPrice[currentMinIndex] = pPrice[i];
        pPrice[i] = currentMin;

        pName[currentMinIndex]=pName[i];
        pName[i]=currentName;
      }
    }

  }

}//end of class

