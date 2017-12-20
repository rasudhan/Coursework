//*******************************************************************************
//* 																			*
//* 			CIS611 Spring 2017  Jeffrey Cheng								*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 			GUI for booking client containing final output format			*
//*																				*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: Booking.java		 	 						*
//* 																 			*
//*******************************************************************************
package finalproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


public class BookingGUI extends JFrame {


	private JPanel top;
	private JPanel center;
	private JPanel bot;
	private JPanel center2;

	private JButton searchButton;
	private JButton bookingButton;
	private JButton viewStatusButton;
	private JButton leaveButton;
	private JButton viewFlightButton;
	private JButton cancelButton;

	private JLabel searching;
	private JLabel payment;
	private JLabel checking;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel label8;
	private JLabel label9;
	private JLabel label10;

	private JTextField field1;
	private JTextField field2;
	private JTextField field3;
	private JTextField field4;
	private JTextField field5;
	private JTextField field6;
	private JTextField field7;
	private JTextField field8;
	private JTextField field9;
	private JTextField field10;

	private JTextArea textArea;
	private JScrollPane scrollPane;

	private JRadioButton roundButton;
	private JRadioButton onewayButton;

	private JComboBox fromBox;
	private JComboBox toBox;
	Customer customer;
	Flight flight;
	BookingClient client;
	Status type;
	private String message = "";
	private String output = "";
	private Random rnd = new Random();

	public BookingGUI(BookingClient client, Customer customer) {
		this.client = client;
		this.customer = customer;
		initGUI();
		doTheLayout();


		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search(customer);
			}
		});

		bookingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				booking(customer);
			}
		});

		viewStatusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewStatus(customer);
			}
		});

		viewFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					viewFlight(customer);
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cancelFlight(customer);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		leaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					exit();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		/*roundButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			radioButton();
		}
		});

		onewayButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			radioButton();
		}
		});*/

	}

	public void initGUI() {
		top = new JPanel();
		center = new JPanel();
		center2 = new JPanel();
		bot = new JPanel();

		searchButton = new JButton("Search");
		bookingButton = new JButton("Book Flight");
		viewStatusButton = new JButton("View Customer Status");
		viewFlightButton = new JButton("View Your Flight");
		leaveButton = new JButton("Leave");
		cancelButton = new JButton("Cancel Reservation");

		roundButton = new JRadioButton("Round trip");
		onewayButton = new JRadioButton("One way");

		ButtonGroup groupButton = new ButtonGroup();
		groupButton.add(roundButton);
		groupButton.add(onewayButton);

		payment = new JLabel("Payment and Booking");
		payment.setForeground(Color.blue);
		searching = new JLabel("Viewing Flight");
		searching.setForeground(Color.blue);

		checking = new JLabel("Checking your flight");
		checking.setForeground(Color.blue);


		label1 = new JLabel("From");
		label2 = new JLabel("To");
		label3 = new JLabel("Depart Date");
		label4 = new JLabel("Return Date");
		label5 = new JLabel("Number");

		label6 = new JLabel("Customer Name:");
		label7 = new JLabel("Flight ID");
		label8 = new JLabel("Credit Card Number: ");
		label9 = new JLabel("Expire Date: ");
		label10 = new JLabel("Booking ID: ");

		field1 = new JTextField(10);
		field2 = new JTextField(10);

		field3 = new JTextField(8);
		field4 = new JTextField(8);

		field5 = new JTextField(3);

		field6 = new JTextField(15);
		field6.setText(customer.getFirstName() + "  " + customer.getLastName());


		field7 = new JTextField(10);
		field8 = new JTextField(15);
		field9 = new JTextField(5);
		field10 = new JTextField(10);


		textArea = new JTextArea(12, 40);
		textArea.setEditable(false);
		//textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane = new JScrollPane(textArea);
		//scrollPane.setPreferredSize(new Dimension (10, 60));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		String[] listState = { "","Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", 
				"Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", 
				"Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma",
				"Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
				"Wisconsin", "Wyoming"};

		fromBox = new JComboBox(listState);
		fromBox.setPreferredSize(new Dimension(140,23));
		toBox = new JComboBox(listState);
		toBox.setPreferredSize(new Dimension(140,23));


	}

	public void doTheLayout() {
		//field3.setEnabled(false);
		field4.setEnabled(false);
		field6.setEnabled(false);


		top.setLayout(new FlowLayout());
		top.setPreferredSize(new Dimension(500, 130));

		top.add(Box.createRigidArea(new Dimension(200, 0)));
		top.add(searching);
		top.add(Box.createRigidArea(new Dimension(200, 0)));

		top.add(label1);
		top.add(fromBox);
		top.add(Box.createRigidArea(new Dimension(60, 0)));

		top.add(label2);
		top.add(toBox);	
		top.add(Box.createRigidArea(new Dimension(20, 0)));

		/*top.add(roundButton);
		top.add(Box.createRigidArea(new Dimension(30, 0)));
		top.add(onewayButton);
		top.add(Box.createRigidArea(new Dimension(100, 0)));*/

		top.add(label3);
		top.add(field3);
		top.add(Box.createRigidArea(new Dimension(10, 0)));

		/*top.add(label4);
		top.add(field4);
		top.add(Box.createRigidArea(new Dimension(10, 0)));*/
		top.add(label5);
		top.add(field5);

		center.setLayout(new FlowLayout());
		center.setPreferredSize(new Dimension(500, 150));
		center.add(payment);
		center.add(Box.createRigidArea(new Dimension(400, 10)));

		center.add(label6);
		center.add(field6);

		center.add(label7);
		center.add(field7);
		center.add(Box.createRigidArea(new Dimension(12, 30)));

		center.add(label8);
		center.add(field8);

		center.add(label9);
		center.add(field9);

		center2.setLayout(new FlowLayout());
		center2.setPreferredSize(new Dimension(500, 50));
		center2.add(checking);
		center2.add(Box.createRigidArea(new Dimension(120, 20)));
		center2.add(label10);
		center2.add(field10);



		bot.setLayout(new FlowLayout());
		bot.setPreferredSize(new Dimension(500, 300));
		bot.add(searchButton);
		bot.add(bookingButton);
		bot.add(viewStatusButton);
		bot.add(viewFlightButton);
		bot.add(cancelButton);
		bot.add(leaveButton);

		bot.add(scrollPane);




		Container contentPane = this.getContentPane(); 
		contentPane.add(top);
		contentPane.add(center);
		contentPane.add(bot);
		BoxLayout boxLayout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);		
		setLayout(boxLayout);
		add(top);
		add(center);
		add(center2);
		add(bot);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); // Center the frame
		this.setVisible(false);

	}

	public void search(Customer customer) {

		int customerID = customer.getCustID();
		String input = "";
		String input1 ="";
		String from = "";
		String to = "";
		String trip = "";
		int ticket;

		//SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String dTime = "";
		String rTime = "";

		from = (String) fromBox.getSelectedItem();

		to = (String) toBox.getSelectedItem();

		if (from.equalsIgnoreCase(to)) {
			JOptionPane.showMessageDialog(null, "Your destination should not be same as your departure place");
			return;
		}

		/*if (roundButton.isSelected() == false && onewayButton.isSelected() == false) {
			JOptionPane.showMessageDialog(null, "Please select round trip or one way trip!");
			return;
		}

			if (roundButton.isSelected() == true) {
			try {
				input = field3.getText();
				if (!input.matches("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$")) {
					JOptionPane.showMessageDialog(null, "Please enter correct date formate (Ex: 04/28/2017!)");
					return;

				}
				else {
					dTime = input ;
					//trip = "round";
				}
			}	

			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Invalid date time!");
				return;
			}

			/*try {
				input1 = field4.getText();
				if (!input1.matches("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$")) {
					JOptionPane.showMessageDialog(null, "Please enter correct date formate (Ex: 04/28/2017!)");
					return;

				}
				else {
					rTime = input;
					trip = "oneway";
				}
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Invalid date time!");
				return;


			}

		}*/

		//else if (onewayButton.isSelected() == true) {
		try {
			input = field3.getText();
			if (!input.matches("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$")) {
				JOptionPane.showMessageDialog(null, "Please enter correct date formate (Ex: 04/28/2017!)");
				return;
			}
			else {
				dTime = input;

			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid date time!");
			field3.setText("");
			field3.requestFocus();
			return;
		}
		//}


		try {
			input = field5.getText();
			ticket = Integer.parseInt(input);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Please enter the number correctly!");
			return;
		}
		//int flightID, String airlineName, String fromAirport, String toAirport, Calendar departueTime, Calendar arrivalTime, int totalSeats, int availableSeats, double prices, Status type, String fromAriport

		try {
			client.sendMessage(0, customerID, 0, null, from, to, dTime, null, 0, 0, 0, ticket, type.SERACH_OP);

		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	public void booking(Customer customer) {

		CardValidation validation = new CardValidation();
		int customerID = customer.getCustID();
		int flightID = 0;
		String input = "";
		String card;
		int ticket;
		String date = "";
		try {
			input = field5.getText();
			ticket = Integer.parseInt(input);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Please enter the number correctly!");
			field5.setText("");
			field5.requestFocus();
			return;
		}

		try {
			input = field7.getText();
			flightID = Integer.parseInt(input);
		}
		catch (Exception e ){
			JOptionPane.showMessageDialog(null, "Invalid flight id number!");
			field7.setText("");
			field7.requestFocus();
			return;
		}

		try {
			card = field8.getText();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid credit card number input!");
			return;
		}

		input = field9.getText();
		try {
			if (!input.matches("^((0[1-9])|(1[0-2]))\\/(\\d{4})$")) {
				JOptionPane.showMessageDialog(null, "Please enter your month with specific format (Ex: 03/2017)");
				field9.setText("");
				field9.requestFocus();
				return;
			}
			else {
				date = input;
			}
		}	
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Please enter your month with specific format (Ex: 03/2017)");
			field9.setText("");
			field9.requestFocus();
			return;
		}

		if(validation.aValidNumber(card)) {
			JOptionPane.showMessageDialog(null, "Credit Card Validation Successful!");
		}
		else {
			JOptionPane.showMessageDialog(null, "Invalid Card number! ");
			field8.setText("");
			field8.requestFocus();
			return;
		}

		JFrame frame = new JFrame();
		int input3 = JOptionPane.showConfirmDialog(frame,"Do you sure you want to book this flight?");

		if (input3 == JOptionPane.CANCEL_OPTION || input3 == JOptionPane.NO_OPTION ) {
			return;			
		}
		else if (input3 == JOptionPane.YES_OPTION) {
			try {
				field8.setText("");
				field9.setText("");
				field7.setText("");
				client.sendMessage(0, customerID, flightID, null, null, null, null, null, 0, 0, 0, ticket, type.BOOKING_OP);

			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}

		}
	}


	public void viewStatus(Customer customer) {
		textArea.setText(customer.toString());

	}

	public void viewFlight(Customer customer) throws ClassNotFoundException, IOException {
		String input = "";
		int bookingID;
		int customerID = customer.getCustID();
		try {
			input = field10.getText();
			bookingID = Integer.parseInt(input);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "invalid flight ID number");
			return;
		}
		client.sendMessage(bookingID, customerID, 0, null, null, null, null, null, 0, 0, 0, 0, type.VIEWFLIGHT_OP);


	}

	/*public void radioButton() {
		if (roundButton.isSelected() == true) {
			field3.setEnabled(true);
			field4.setEnabled(true);
		}
		else if (onewayButton.isSelected() == true) {
			field3.setEnabled(true);
			field4.setEnabled(false);
		}
	}*/

	public void cancelFlight(Customer customer) throws ClassNotFoundException, IOException {
		String input = "";
		int bookingID;
		int customerID = customer.getCustID();
		try {
			input = field10.getText();
			bookingID = Integer.parseInt(input);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "invalid flight ID number");
			return;
		}
		client.sendMessage(bookingID, customerID, 0, null, null, null, null, null, 0, 0, 0, 0, type.CANCEL_OP);
	}

	public void newText(ArrayList<Flight> list) {

		                                                                                                                          
		String message = "Flight ID" + "\t" + "Airline Name    " + "\t" + "From Airport " + "\t" + "To Airport " + "\t" + "Departure Time       " +"\t" + "Arrival Time       " + "\t" + "Total Seats" + "\t" + "Available Seats" + "\t"
				+ "Prices" + "\t" + "Rank Star" + "\t" + "\n";
		for (int i = 0; i < list.size(); i ++) {
			int star  = rnd.nextInt(5) + 1;
			message += list.get(i).toString() + star + "\n";
		}
		textArea.append(message);	
	}

	public void bookingText(ArrayList<Flight> list) {

		output = "Booking ID: " + list.get(0).getBookingID() + "," + "\t" + "Customer ID: " + list.get(0).getCustomerID() + "," + "\t" + "Flight ID: " + list.get(0).getFlightID() + "," + "\t"
				+ "Tickets: " + list.get(0).getTicket() + "," + "\t" + "Total Prices: " + list.get(0).getPrices() + "\n";

		textArea.append(output);
	}

	public void viewText(ArrayList<Flight> list) {
		
		String message = "";
		
		for (int i = 0; i < list.size(); i ++) {
			message += "Booking ID: " + list.get(i).getBookingID() + "\n" + "Customer ID: " + list.get(i).getCustomerID() + "\n" + "Flight ID: " + list.get(i).getFlightID() + "\n" + 
					"Airline Name: " + list.get(i).getAirlineName() + "\n" + "From Airport: " + list.get(i).getFromAirport() + "\n" + "To Airport: " +
					list.get(i).getToAirport() + "\n" + "Departure Time: " + list.get(i).getDepartureTime() + "\n" + "Arrival Time: " + list.get(i).getArrivalTime() + "\n" + 
					"Total prices: " + list.get(i).getPrices() + "\n";
		}
		
		textArea.append(message);
	}
	public void cancelText(ArrayList<Flight> list) {
		String message = " Your flight reservation has been canceled";
		textArea.append(message);
	}


	public void exit() throws IOException, ClassNotFoundException {

		client.close();

	}

}
