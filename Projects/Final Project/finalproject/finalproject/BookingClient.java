//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 			Booking Client connect to Booking server						*
//*																				*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*					Saved in: BookingClient.java		 	 					*
//* 																 			*
//*******************************************************************************
package finalproject;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class BookingClient {
	
	private Socket connect1;
	private ObjectOutputStream clientOutputStream1;
	private ObjectInputStream clientInputStream1;
	Status status;

	private Customer customer;
	private String hostname = "localhost";
	private int port;
	CustomerGUI gui;
	BookingGUI bgui;
	private Flight flight;
	private ArrayList<Flight> list;
	
	public BookingClient(String hostname, int port, Customer customer) {
		
		viewBookingGui(customer);
		
		try {
			connect(hostname, port);
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		
	}
	
	public void viewBookingGui(Customer customer) {
		bgui = new BookingGUI(this, customer);
		bgui.pack();
		bgui.setVisible(true);

	}
	
	
	public void connect(String hostAddress, int connectingPort) throws IOException, UnknownHostException, StreamCorruptedException {
		
		connect1 = new Socket(hostAddress, connectingPort);

		try {
			clientOutputStream1 = new ObjectOutputStream(connect1.getOutputStream());
			clientInputStream1 = new ObjectInputStream(connect1.getInputStream());
			
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();

		}
		
	}
	
	
	public void sendMessage(int bookingID, int customerID, int flightID, String airlineName, String from, String to, String departueTime, String arrivalTime, int totalSeats, 
			int availableSeats, double prices, int ticket, Status type) throws IOException, ClassNotFoundException {
		
		Flight flight = new Flight(bookingID, customerID, flightID, airlineName, from, to, departueTime, arrivalTime, totalSeats, availableSeats, prices, ticket, type);
		
		list = new ArrayList<Flight>();
		list.add(flight);
		clientOutputStream1.writeObject(list);
		//clientOutputStream1.close();
		receivingMessage(list);
		
	}
	

	private void receivingMessage(ArrayList<Flight> list) throws IOException, ClassNotFoundException, EOFException {
		
		try {
			list = (ArrayList<Flight>) clientInputStream1.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		switch (list.get(0).getType()) {
		
		case SERACH_OP:
			if (list.get(0).getFlightID() != 0) {
				flightINFO(list);
				break;
			}
			else {
				JOptionPane.showMessageDialog(null, "No flight available!");
				break;
			}
		case BOOKING_OP:
			if (list.get(0).getBookingID() != 0) {
				bookingINFO(list);
				break;
			
			}
			else {
				JOptionPane.showMessageDialog(null, "Transaction failed!, Invalid Flight Number or you already have a reservation flight on same time");
				break;
			}
		case VIEWFLIGHT_OP:
			if (list.get(0).getBookingID() != 0) {
				viewINFO(list);
				break;
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Invalid booking ID");
				break;
			}
		case CANCEL_OP:
			if (list.get(0).getBookingID() == 1) {
				cancelConfirm(list);
				break;
			}
			else if (list.get(0).getBookingID() == 0) {
				JOptionPane.showMessageDialog(null, "Invalid booking ID");
				break;
			}
		case CLOSE_OP:
			close();
			break;
		}
		
	}
	
	public void flightINFO(ArrayList<Flight> list) {

		bgui.newText(list);	
		
	}
	public void bookingINFO(ArrayList<Flight> list) {
		
		
		bgui.bookingText(list);
		
	}
	public void viewINFO(ArrayList<Flight> list) {
		bgui.viewText(list);
	}
	public void cancelConfirm(ArrayList<Flight> list) {
		bgui.cancelText(list);
	}
	
	public void close() throws IOException, ClassNotFoundException {
			
		clientOutputStream1.close();
		clientInputStream1.close();
		connect1.close();
		System.exit(0);
		
	}

}
