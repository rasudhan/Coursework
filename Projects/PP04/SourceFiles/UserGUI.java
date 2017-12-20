
import java.io.*;

import javax.swing.*;

public class UserGUI extends JFrame{
	
	static Scraper scraper;
	
	public static void main (String[] args)  {
		
	       scraper = new Scraper("URL", "filename");
	       
	       
	    } // end main 
	
}// end class