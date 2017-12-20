
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scraper{
	
	private String url; 
	private String fileName;
	DataSet[] datasets = new DataSet[360];
	
	Scraper(String url, String fileName){
		this.url = url;
	}
	
	// This method will parse a dataset and scrape all the relevant information pertaining to one dataset.
	// Once all the information is fetched, it will create a DataSet object and it will be added to the datasets array
	private void parseData() {
		
	}
	
	// will read the data from datasets array (one by one) and return a String of the all DataSet object strings in multiple lines, each line has a DataSet object String
	@Override
	public String toString() {
	
		return null;
		
	}
	
	// store the program out in a text file, output.txt 
	public void writeToFile() {
		
	}
	
    }//end Class
	

