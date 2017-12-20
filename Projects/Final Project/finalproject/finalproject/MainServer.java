//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 		MainSever accepts clients and connect to MySQL, starting with 		*	
//*				this class first. 												*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: MainServer.java		 	 					*
//* 																 			*
//*******************************************************************************
package finalproject;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import javax.swing.JOptionPane;


public class MainServer {

	Socket conn;
	ServerSocket serverSocket;

	Connection connect;

	ObjectInputStream serverInputStream;
	ObjectOutputStream serverOutputStream; 
	Customer customer;
	Customer dba;
	DBAfunction df;

	private Status type;

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";   
	static final String DB_URL = "jdbc:mysql://BusCISMySQL01:3306/group3db"; 
	static final String USER = "nearfar";
	static final String PASS = "831144840";


	public MainServer (int port, Login log) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException, ParseException{
		startListening(port, log);


	}

	public Boolean startListening(int listeningPort, Login log) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException {
		try {
			serverSocket = new ServerSocket(listeningPort);
			Class.forName(JDBC_DRIVER); 
			JOptionPane.showMessageDialog(null,"Driver loaded"); 
			connect = DriverManager.getConnection(DB_URL, USER, PASS); 
			JOptionPane.showMessageDialog(null,"Database connected"); 
			JOptionPane.showMessageDialog(null,"Waiting for client"); 
		}
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage()+"   :It couldn't listen on the port "+listeningPort + "\n");
			return false;
		}
		conn = serverSocket.accept(); 
		serverInputStream = new ObjectInputStream(conn.getInputStream());
		serverOutputStream = new ObjectOutputStream(conn.getOutputStream());
		JOptionPane.showMessageDialog(null, "Connecting to Server" + "\n");
		receivingMessage(listeningPort, log);
		return true;
	}

	private void receivingMessage(int port, Login log) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, EOFException, ParseException{

		while(true) {
			customer = null;	
			customer = (Customer)serverInputStream.readObject();


			switch (customer.getType()) { 

			case LOGIN_OP:
				if (log.loginAction(customer, connect).getCustID() != 0) {//Login success
					sendMessage(log.loginAction(customer, connect));
					switchServer();
					//serverInputStream.close();
					//serverOutputStream.close();
					//connect.close();
					break;

				}
				else {
					sendMessage(log.loginAction(customer, connect));
					break;
				}
			case CREATE_OP: 
					sendMessage(log.createAction(customer, connect));

					break;
			
					
			case DBALOGIN_OP:	
					sendMessage(log.dbaLogin(customer, connect));
					switchDBAServer();
					break;
			}
						
		}	
				
	}

	

	public void sendMessage(Customer customer) throws IOException {

		serverOutputStream.writeObject(customer);

	}//end of sendMessage action method
	
	public void close(Customer customer) throws IOException, SQLException, ClassCastException{

		serverInputStream.close();
		serverOutputStream.close();
		connect.close();

	}//end of close action method

	public void switchServer() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException, ParseException {
		Booking book = new Booking();
		int port = 8345;
		BookingServer bookingS = new BookingServer(port, book);
	}
	
	public void switchDBAServer() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException, ParseException {
		DBAfunction df = new DBAfunction();
		int port = 6655;
		DBAServer ds = new DBAServer(port, df);
	}
	
	public static void main (String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException, ParseException {
		int 	port 	= 8888; 
		Login log = new Login();
		MainServer server = new MainServer(port, log);

	}
}

