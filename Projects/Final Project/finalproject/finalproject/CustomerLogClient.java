//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 			CustomerLogClient connect to MainServer for login process		*	
//*																				*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: CustomerLogClient.java		 	 			*
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
import java.util.Calendar;

import javax.swing.JOptionPane;

public class CustomerLogClient {
	private Socket connect;
	private ObjectOutputStream clientOutputStream;
	private ObjectInputStream clientInputStream;
	Status status;

	private Customer customer;

	CustomerGUI gui;
	BookingGUI bgui;
	Flight flight;
	public CustomerLogClient(String hostname, int port) {

		try {
			connect(hostname, port);
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 


	}

	public void viewCustomerGui() {
		gui = new CustomerGUI(this);
		gui.pack();
		gui.setTitle("Customer Login UI");
		gui.setVisible(true);
		

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

	public void sendMessage(int id, String fName, String lName, String address, String account, String password,String telephone, String email, int points, Status type) throws IOException, ClassNotFoundException {

		customer = new Customer(id, fName, lName, address, account, password, telephone, email, points, type);
		clientOutputStream.writeObject(customer);
		
		receivingMessage(customer);

	}

	private void receivingMessage(Customer customer) throws IOException, ClassNotFoundException {

		customer = (Customer) clientInputStream.readObject();

		switch (customer.getType()) {

		case LOGIN_OP:
			if (customer.getCustID() != 0) {
				JOptionPane.showMessageDialog(null, "Login Success!");	
				switchClient(customer);
				clientOutputStream.close();
				clientInputStream.close();
				connect.close();
				gui.dispose();
				break;
			}
			else {
				JOptionPane.showMessageDialog(null, "the username or password is incorrect!");
				break;
			}

		case CREATE_OP:
			if (customer.getCustID() != 0) {
				JOptionPane.showMessageDialog(null, "Create new customer success");
				break;
			}			
			else {
				JOptionPane.showMessageDialog(null, "the username has already existed");
				return;
			} 
	
		}

	}// end of receivingMessage action method
	


	public void loginCustomer (Customer customer) throws ClassNotFoundException, IOException {


		sendMessage( 0, null, null, null, null, null, customer.getUsername(), customer.getPassword(), 0, customer.getType());

	}

	public void createCustomer (Customer customer) throws ClassNotFoundException, IOException {

		sendMessage( 0, customer.getFirstName(), customer.getLastName(), customer.getAddress(), customer.getTelephone(), customer.getEmail(), customer.getUsername(), customer.getPassword(), 0, customer.getType());

	}
	
	public void switchClient(Customer customer) {
		String hostname = "localhost";
		int port = 8345;
		
		BookingClient client = new BookingClient(hostname, port, customer);
		
	}
	


}
