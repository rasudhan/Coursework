//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 			BookingSever accept Bookingclient and connect to MySQL			*
//*																				*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: BookingServer.java		 	 				*
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
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class BookingServer {

	Socket conn;
	ServerSocket serverSocket;

	Connection connect;

	ObjectInputStream serverInputStream;
	ObjectOutputStream serverOutputStream; 
	Flight flight;
	DBAfunction dba;
	Booking book;

	private Status type;

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";   
	static final String DB_URL = "jdbc:mysql://BusCISMySQL01:3306/group3db"; 
	static final String USER = "nearfar";
	static final String PASS = "831144840";
	private ArrayList <Flight> list = new ArrayList<Flight>();
	
	public BookingServer (int port, Booking book) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException, ParseException {
		startListening(port, book);
	}

	public Boolean startListening(int listeningPort, Booking book) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException {
		try {
			serverSocket = new ServerSocket(listeningPort);
			Class.forName(JDBC_DRIVER); 
			connect = DriverManager.getConnection(DB_URL, USER, PASS); 
			//JOptionPane.showMessageDialog(null,"Database connected"); 
			//JOptionPane.showMessageDialog(null,"Waiting for client"); 
		}
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage()+"   :It couldn't listen on the port "+listeningPort + "\n");
			return false;
		}
		conn = serverSocket.accept(); 
		serverInputStream = new ObjectInputStream(conn.getInputStream());
		serverOutputStream = new ObjectOutputStream(conn.getOutputStream());
		//JOptionPane.showMessageDialog(null, "Connecting to Server" + "\n");
		receivingMessage(book);
		return true;
	}
	private void receivingMessage(Booking book) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, EOFException, ParseException{

		while (true){

			list = null;	
			list= (ArrayList<Flight>)serverInputStream.readObject();


			switch (list.get(0).getType()) {

			case SERACH_OP:
					sendMessage(book.serachAction(list, connect));
					break;


			case BOOKING_OP:
					sendMessage(book.bookingAction(list, connect));
					break;

			case VIEWFLIGHT_OP:
					sendMessage(book.viewAction(list, connect));
					break;
			
			case CANCEL_OP:
					
					sendMessage(book.cancelFlight(list, connect));
					break;
				
				
			case CLOSE_OP:
				
				//serverInputStream.close();
				//serverOutputStream.close();
				//conn.close();
				//System.exit(0);
				
			}
		}	
	}

	public void sendMessage(ArrayList<Flight> list) throws IOException {
		serverOutputStream.writeObject(list);

	}

}
