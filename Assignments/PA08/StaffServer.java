//*************************************************************************
//* 																
//* 				CIS611 Spring 2017 Anand RameshKannan
//* 															
//* 					Program Assignment PA08				 
//* 																 
//* 				Program to View/Insert/Update/Delete records in DataBase
//* 																 
//* 					Date Created: 04.07.2017 					 
//*					Saved in: StaffServer.java		 	 
//* 																 
//**************************************************************************
package PA08;

import java.awt.Container;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.*;


import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;


public class StaffServer extends JFrame {

	
	int port;
	
	 private Connection connect = null;
		private Statement statement = null;
		private PreparedStatement preparedStatement = null;
		private ResultSet resultSet = null;
		Message message;
		
		
	private JTextArea jTA;
	 ObjectInputStream inputFromClient;
	 ObjectOutputStream outputToClient ;
	 ServerSocket serverSocket;
	 
	 
	 
	
	public StaffServer(int port) throws IOException, ClassNotFoundException, SQLException {
		
		new JFrame();
		jTA=new JTextArea();
		add(jTA);
		
		setSize(400,300);
		setVisible(true);
		try {
				
			// this will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      System.out.println("Driver loaded");
		      jTA.append("Driver loaded.   " +"\n");
		      
		      // setup the connection with the DB.
		      connect = DriverManager.
		    		  getConnection("jdbc:mysql://BUSCISMYSQL01/rasudhanDB", 
		    				  "rasudhan", "831117967");
		      System.out.println("Database loaded");
		      jTA.append("Database loaded.   " +"\n");
		      

			
			 
			 while (true) {
				 
				 	serverSocket=new ServerSocket(port);
				 	jTA.append("Server Started at port : "+port+"\n");
				 	
			        // Listen for a new connection request
			        Socket socket = serverSocket.accept();
	
			        // Create a new thread for the connection
			        HandleAClient task = new HandleAClient(socket);
	
			        // Start the new thread
			        new Thread(task).start();
			        
			        
 			}
		}
		catch(Exception ex) {
			System.out.println("Port in use! Please try a different port!");
		}
		
	      
	}
		 
	

	public void view(Message message) throws SQLException{
		
		String sql = "SELECT * FROM rasudhandb.staff";
		
		statement = connect.createStatement();
		resultSet = statement.executeQuery(sql);
		ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();
		int cnumber = rsmd.getColumnCount();
	
		while (resultSet.next()) {
			for (int i = 1; i <= cnumber; i++) {
				int id = resultSet.getInt("id");
				String lName = resultSet.getString("lastName");
				String fName = resultSet.getString("firesultSettName");
				String mis = resultSet.getString("mi");
				char mi = mis.charAt(0);
				String address = resultSet.getString("address");
				String city = resultSet.getString("city");
				String state = resultSet.getString("state");
				String telephone = resultSet.getString("telephone");
				message = new Message (id, lName, fName, mi, address, city, state, telephone, "VIEW");
				try {
					outputToClient.writeObject(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
		
	}
	public void insert(Message message) throws SQLException {
		
		int id = message.id;
		String lName = message.lastName;
		String fName = message.firstName;
		char mi = message.mi;
		String address = message.address;
		String city = message.city;
		String state = message.state;
		String telephone = message.telephone;
		statement = connect.createStatement();
		
		String sql = "INSERT INTO rasudhandb.staff VALUES(?,?,?,?,?,?,?,?)";
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		preparedStatement.setString(2, lName);
		preparedStatement.setString(3, fName);
		preparedStatement.setString(4, String.valueOf(mi));
		preparedStatement.setString(5, address);
		preparedStatement.setString(6, city);
		preparedStatement.setString(7, state);
		preparedStatement.setString(8, telephone);
		
		preparedStatement.execute();
		
		System.out.print("Record Inserted!");
			
	}
	public void update(Message message) throws SQLException {
		
		int id = message.id;
		String lName = message.lastName;
		String fName = message.firstName;
		char mi = message.mi;
		String address = message.address;
		String city = message.city;
		String state = message.state;
		String telephone = message.telephone;
		statement = connect.createStatement();
		
		String sql = "UPDATE rasudhandb.staff SET firstName=?,lastName= ?,mi=?,address=?,"
				+ "city=?,state=?,telephone=? WHERE id=?)";
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setInt(8, id);
		preparedStatement.setString(2, lName);
		preparedStatement.setString(1, fName);
		preparedStatement.setString(3, String.valueOf(mi));
		preparedStatement.setString(4, address);
		preparedStatement.setString(5, city);
		preparedStatement.setString(6, state);
		preparedStatement.setString(7, telephone);
		
		preparedStatement.executeUpdate();
		
		System.out.print("Record Updated!");
		
		
	}
	public void delete(Message message) throws SQLException {
		
		statement = connect.createStatement();
		
		String sql = "DELETE FROM rasudhandb.staff WHERE id=?)";
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.execute();
		
		System.out.print("Record Deleted!");
	}
	
	public void close(Message message) throws IOException, SQLException{
		inputFromClient.close();
		outputToClient.close();
		resultSet.close();
		 statement.close();
		connect.close();
		
		System.exit(0);

	}
	
	class HandleAClient implements Runnable {
	    private Socket socket; // A connected socket

	    /** Construct a thread */
	    public HandleAClient(Socket socket) {
	      this.socket = socket;
	      
	    }

	    /** Run a thread */
	    public void run() {
	      try {
	        // Create data input and output streams
	        ObjectInputStream inputFromClient = new ObjectInputStream(
	          socket.getInputStream());
	        ObjectOutputStream outputToClient = new ObjectOutputStream(
	          socket.getOutputStream());
	        
	        jTA.append("Client connected!");

	        // Continuously serve the client
	        while (true) {
	        	Message message = null;	
	    		message = (Message)inputFromClient.readObject();
	    		
	    		
	            switch (message.opType) { 
	    		
	    		case "VIEW": view(message);
	    				        break; 
	    				        
	    		 case "INSERT": insert(message);
	                                break; 

	    		 case "UPDATE":  update(message);
	                                 break;
	                                 
	    		 case "DELETE":  delete(message);
                 					break; 
	    		 }
	    			
	            outputToClient.writeObject(message);
	    	  }
	        
	       
	      }
	      catch(IOException e) {
	        System.err.println(e);
	      } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	  }
	
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		
		new StaffServer(8557); //Set listening port
	//	new StaffServer(); //Create the GUI
		

	}

}
