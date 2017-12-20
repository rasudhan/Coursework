//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 			DBASever accepts DBAClient and connects to MySqL				*	
//*																				*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: DBAServer.java		 	 					*
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

public class DBAServer {
	Socket conn;
	ServerSocket serverSocket;

	Connection connect;

	ObjectInputStream serverInputStream;
	ObjectOutputStream serverOutputStream; 
	Flight flight;
	DBAfunction df;

	private Status type;
	private ArrayList <Flight> list = new ArrayList<Flight>();

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";   
	static final String DB_URL = "jdbc:mysql://BusCISMySQL01:3306/group3db"; 
	static final String USER = "nearfar";
	static final String PASS = "831144840";

	public DBAServer (int port, DBAfunction df) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException, ParseException {
		startListening(port, df);
	}

	public Boolean startListening(int listeningPort, DBAfunction df) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException {
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
		JOptionPane.showMessageDialog(null, "Welcome Adminstrator" + "\n");
		receivingMessage(df);
		return true;
	}
	private void receivingMessage(DBAfunction df) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, EOFException, ParseException{

		while (true){

			list = null;	
			list= (ArrayList<Flight>)serverInputStream.readObject();


			switch (list.get(0).getType()) {

			case ADDFLIGHT_OP:
					sendMessage(df.addFlight(list, connect));
					break;

			
			case VIEW_OP:
					sendMessage(df.view(list, connect));
					break;
			
			case UPDATE_OP:
					sendMessage(df.update(list, connect));
					break;
					
			case CLOSE_OP:
				sendMessage(df.close(list, connect));
				serverInputStream.close();
				serverOutputStream.close();
				conn.close();
				System.exit(0);
				
			}
		}	
	}

	public void sendMessage(ArrayList<Flight> list) throws IOException {
		serverOutputStream.writeObject(list);

	}


}
