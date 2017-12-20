package PA08;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.mysql.jdbc.Statement;

public class StaffServer extends JFrame {

	private Statement myStmt;
	int port;
	
	private JTextArea jTA;
	 DataInputStream inputFromClient;
	 DataOutputStream outputToClient ;
	
	public StaffServer() throws IOException {
		
		new JFrame();
		jTA=new JTextArea();
		add(jTA);
		
		setSize(400,300);
		setVisible(true);
		
		ServerSocket serverSocket = new ServerSocket(8600);
		 jTA.append("Server Started  " +"\n");
		
	   // Listen for a connection request
      Socket socket = serverSocket.accept();
      
      // Create data input and output streams
       inputFromClient = new DataInputStream(
        socket.getInputStream());
      outputToClient = new DataOutputStream(
        socket.getOutputStream());

	
	
	 while (true) {
	        // Receive radius from the client
	      int name= inputFromClient.readInt();

	        // Send area back to the client
	        outputToClient.writeChars("Success");

	        jTA.append("Success!"+name + '\n');
	        
	  }
	}
	
	public StaffServer(int port) {
		this.port=port;
	}
	
	public void initializeDB() {
		
	}
	public void view() {
		
	}
	public void insert() {
			
	}
	public void update() {
		
	}
	public void delete() {
		
	}
	
	public static void main(String[] args) throws IOException {
		
		new StaffServer();
		

	}

}
