//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 			DBAClient class connect to DBASever								*	
//*																				*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: DBAClient.java		 	 					*
//* 																 			*
//*******************************************************************************
package finalproject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DBAClient {
	
	private Socket connect;
	private ObjectOutputStream clientOutputStream;
	private ObjectInputStream clientInputStream;
	Status status;
	DBAGUI dgui;
	Flight flight;
	private ArrayList<Flight> list;
	
	public DBAClient(String hostname, int port, Customer customer) throws UnknownHostException, IOException {
		viewGUI(customer);
		connect(hostname, port);
		
	}
	
	
	public void viewGUI(Customer customer) {
		dgui = new DBAGUI(this, customer);
		dgui.pack();
		dgui.setVisible(true);

	}


	public void connect(String hostAddress, int connectingPort) throws IOException, UnknownHostException {
		connect = new Socket(hostAddress, connectingPort);

		try {
			clientOutputStream = new ObjectOutputStream(connect.getOutputStream());
			clientInputStream = new ObjectInputStream(connect.getInputStream());
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();

		}
	}

	public void sendMessage(int bookingID, int customerID, int flightID, String airlineName, String fromAirport, String toAirport, String departueTime, String arrivalTime, 
			int totalSeats, int availableSeats, double prices, int ticket, Status type) throws IOException, ClassNotFoundException {

		flight = new Flight(bookingID, customerID, flightID, airlineName, fromAirport, toAirport, departueTime, arrivalTime, 
				totalSeats, availableSeats, prices, ticket, type);
		
		list = new ArrayList<Flight>();
		list.add(flight);
		clientOutputStream.writeObject(list);
		
		receivingMessage(list);

	}

	private void receivingMessage(ArrayList<Flight> list) throws IOException, ClassNotFoundException {

		list = (ArrayList<Flight>) clientInputStream.readObject();

		switch (list.get(0).getType()) {

		case ADDFLIGHT_OP:
			if (list.get(0).getFlightID() != 0) {
				addFlight(list);
				break;
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Flight ID has alread in database");
				break;
			}
		case VIEW_OP:
			view(list);
			break;
		
		case UPDATE_OP:
			if (list.get(0).getFlightID() != 0) {
				update(list);
				break;
			}
			else {
				JOptionPane.showMessageDialog(null, "Cant not find the flight ID in database");
				break;
			}
		case CLOSE_OP:
			close();
			break;
		}

	}// end of receivingMessage action method
	
	public void addFlight(ArrayList<Flight> list) {
		dgui.addFlightText(list);
	}
	
	public void view(ArrayList<Flight> list) {
		dgui.viewText(list);
	}
	
	public void update(ArrayList<Flight> list) {
		dgui.updateText(list);
	}
	public void close() throws IOException {
		clientOutputStream.close();
		clientInputStream.close();
		connect.close();
		System.exit(0);
	}
	
}	

