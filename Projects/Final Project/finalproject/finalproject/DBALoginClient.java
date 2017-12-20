//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 			DBALoginClient connect to MainSever for DBA login process		*	
//*																				*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: DBALoginClient.java		 	 				*
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

import javax.swing.JOptionPane;

public class DBALoginClient {

	private Socket connect;
	private ObjectOutputStream clientOutputStream;
	private ObjectInputStream clientInputStream;
	Status status;
	DBALoginGUI dgui;
	
	public DBALoginClient (String hostname, int port) throws UnknownHostException, IOException {
		
		connect(hostname, port);
		
	}
	
	
	public void viewGUI() {
		dgui = new DBALoginGUI(this);
		dgui.pack();
		dgui.setTitle("DBA Login UI");
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

	public void sendMessage(int id, String fName, String lName, String address, String account, String password,String telephone, String email, int points, Status type) throws IOException, ClassNotFoundException {
		
		Customer customer = new Customer(id, fName, lName, address, account, password, telephone, email, points, type);
		clientOutputStream.writeObject(customer);
		
		receivingMessage(customer);

	}

	private void receivingMessage(Customer customer) throws IOException, ClassNotFoundException {

		customer = (Customer) clientInputStream.readObject();

		switch (customer.getType()) {

		case DBALOGIN_OP:
			if (customer.getCustID() != 0) {
				JOptionPane.showMessageDialog(null, "Login Success!");	
				switchClient(customer);
				clientOutputStream.close();
				clientInputStream.close();
				connect.close();
				dgui.dispose();
				break;
			}
			else {
				JOptionPane.showMessageDialog(null, "the username or password is incorrect!");
				break;
			}
	
		}

	}// end of receivingMessage action method
	
	public void switchClient(Customer customer) throws UnknownHostException, IOException {
		String hostname = "localhost";
		int port = 8002;
		
		DBAClient client = new DBAClient(hostname, port, customer);
		
	}

}
