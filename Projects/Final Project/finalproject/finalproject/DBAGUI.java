//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 					DBAGUI for DBAClient 									*	
//*																				*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: DBAGUI.java		 	 						*
//* 																 			*
//*******************************************************************************
package finalproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class DBAGUI extends JFrame{
	
	
	private JPanel top;
	private JPanel bot;
	private JPanel center;

	private JLabel title;
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

	
	private JButton addButton;
	private JButton viewButton;
	private JButton updateButton;
	private JButton leaveButton;
	DBAClient dbac;
	Customer dba;
	Flight flight;
	Status type;
	

	public DBAGUI(DBAClient dbac, Customer dba) {
		this.dbac = dbac;
		this.dba = dba;
		initGUI();
		layoutGUI();
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view();
			}
		});
		
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		
		leaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	public void initGUI() {
		top = new JPanel();
		center = new JPanel();
		bot = new JPanel();
		
		
		addButton = new JButton("Add Flight");
		leaveButton = new JButton("EXIT");
		viewButton = new JButton("View");
		updateButton = new JButton("Update");
		
		title = new JLabel("Add Flight Information");
		label1 = new JLabel("FlightID: ");
		label2 = new JLabel("Airline Name: ");
		label3 = new JLabel("From Airport: ");
		label4 = new JLabel("To Airport: ");
		label5 = new JLabel("Depart Time");
		label6 = new JLabel("Arrival Time");
		label7 = new JLabel("Total Seats: ");
		
		label8 = new JLabel("Available Seats:");
		label9 = new JLabel("Prices");
		
		field1 = new JTextField(10);
		field2 = new JTextField(10);
		field3 = new JTextField(10);
		field4 = new JTextField(10);
		field5 = new JTextField(8);
		field6 = new JTextField(8);
		field7 = new JTextField(5);
		field8 = new JTextField(5);
		field9 = new JTextField(5);
		
		textArea = new JTextArea(12, 40);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		scrollPane = new JScrollPane(textArea);
		//scrollPane.setPreferredSize(new Dimension (10, 60));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
	}
	
	public void layoutGUI() {
		
		setLayout( new BorderLayout());
		this.add(top, "North");
		this.add(center, "Center");
		this.add(bot, "South");
		this.setPreferredSize(new Dimension(500, 600));
		
		top.setLayout(new FlowLayout()); 
		top.setPreferredSize(new Dimension(500, 200));
		top.add(title);
		top.add(Box.createRigidArea(new Dimension(300, 30)));
		
		top.add(label1);
		top.add(field1);
		top.add(Box.createRigidArea(new Dimension(30, 0)));
		
		top.add(label2);
		top.add(field2);
		top.add(Box.createRigidArea(new Dimension(20, 30)));
		
		top.add(label3);
		top.add(field3);
		top.add(Box.createRigidArea(new Dimension(20, 0)));
		
		top.add(label4);
		top.add(field4);
		top.add(Box.createRigidArea(new Dimension(60, 30)));
		
		top.add(label5);
		top.add(field5);
		top.add(Box.createRigidArea(new Dimension(50, 0)));
		top.add(label6);
		top.add(field6);
		top.add(Box.createRigidArea(new Dimension(70, 30)));
		
		top.add(label7);
		top.add(field7);
		
		top.add(label8);
		top.add(field8);
		
		top.add(label9);
		top.add(field9);
		
		
		
		center.setLayout( new FlowLayout());
		center.setPreferredSize(new Dimension(500, 100));
		center.add(scrollPane);
		
		

		
		bot.setLayout(new FlowLayout());
		bot.setPreferredSize(new Dimension(500, 50));
		bot.add(addButton);
		bot.add(viewButton);
		bot.add(updateButton);
		bot.add(leaveButton);
		
	}
	
	
	public void insert() {
		
		String input = "";
		int flightID;
		String airlineName;
		String from;
		String to;
		String dTime;
		String aTime;
		int totalSeats;
		int avaSeats;
		double prices;
		
		try {
			input = field1.getText();
			flightID = Integer.parseInt(input);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid flight ID input");
			field1.setText("");
			field1.requestFocus();
			return;
		}
		
		airlineName = field2.getText();
		if (airlineName.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Invalid airlineName input");
			field2.setText("");
			field2.requestFocus();
			return;
		}
		
		from = field3.getText();
		if (from.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Invalid from airport input");
			field3.setText("");
			field3.requestFocus();
			return;
		}
		
		to = field4.getText();
		if (to.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Invalid to airport input");
			field4.setText("");
			field4.requestFocus();
			return;
		}
		
		input = field5.getText();
		if (input.matches("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}\\s([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
			dTime = input;
		}
		else {
			JOptionPane.showMessageDialog(null, "Invalid date time input");
			field5.setText("");
			field5.requestFocus();
			return;
			
		}
		input = field6.getText();
		if (input.matches("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}\\s([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
			aTime = input;
		}
		else {
			JOptionPane.showMessageDialog(null, "Invalid date time input");
			field6.setText("");
			field6.requestFocus();
			return;
			
		}
		
		try {
			input = field7.getText();
			totalSeats = Integer.parseInt(input);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid seats number ");
			field7.setText("");
			field7.requestFocus();
			return;
		}
		
		try {
			input = field8.getText();
			avaSeats = Integer.parseInt(input);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid seats number");
			field8.setText("");
			field8.requestFocus();
			return;
		}
		
		try {
			input = field9.getText();
			prices = Double.parseDouble(input);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid prices number");
			field9.setText("");
			field9.requestFocus();
			return;
		}
			
		try {
			field1.setText("");
			field2.setText("");
			field3.setText("");
			field4.setText("");
			field5.setText("");
			field6.setText("");
			field7.setText("");
			field8.setText("");
			field9.setText("");		
			dbac.sendMessage(0, 0, flightID, airlineName, from, to, dTime, aTime, totalSeats, avaSeats, prices, 0, type.ADDFLIGHT_OP);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void view() {
		try {
			dbac.sendMessage(0, 0, 0, null, null, null, null, null, 0, 0, 0, 0, type.VIEW_OP);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		String input = "";
		int flightID;
		int avaSeats;
		double prices;
		
		
		try {
			input = field1.getText();
			flightID = Integer.parseInt(input);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid flight ID input");
			field1.setText("");
			field1.requestFocus();
			return;
		}
		try {
			input = field8.getText();
			avaSeats = Integer.parseInt(input);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid seats number");
			field8.setText("");
			field8.requestFocus();
			return;
		}
		
		try {
			input = field9.getText();
			prices = Double.parseDouble(input);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid prices number");
			field9.setText("");
			field9.requestFocus();
			return;
		}
		try {
			field1.setText("");
			field8.setText("");
			field9.setText("");
			dbac.sendMessage(0, 0, flightID, null, null, null, null, null, 0, avaSeats, prices, 0, type.UPDATE_OP);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addFlightText(ArrayList<Flight> list) {
		String message = "Add flight success" + "\n";
		for (int i = 0; i < list.size(); i ++) {
			//flightID  + "\t"  + airlineName + "\t" + fromAirport + "\t" + toAirport + "\t" + departueTime + "\t" + arrivalTime + "\t" + totalSeats + "\t" + availableSeats
			//+ "\t" + prices + "\t";
			message += "Flight ID: " + list.get(i).getFlightID() + "," + "\t" + "Airline Name: " + list.get(i).getAirlineName() + "," + "\t" + "From Airport: " + list.get(i).getFromAirport() + 
					"," + "\t" + "To Airport: " + list.get(i).getToAirport() + "," + "\t" + "Departure Time: " + list.get(i).getDepartureTime() + "," + "\t" + "Arrival Time: " + list.get(i).getArrivalTime() 
					+ "," + "\t" + "Total Seats: " + list.get(i).getTotalSeats() + "," + "\t" + "Available Seats:" + list.get(i).getAvailableSeats() + "," + "\t" + "Prices: " + list.get(i).getPrices() + "\n";
		}
		
		textArea.append(message);
		
	}
	
	public void viewText(ArrayList<Flight> list) {
		
		String title = "Booking ID" + "\t" + "flight ID" + "\t" + "customer ID" + "\t" + "Total Prices" + "\n";
		String message = "";
		//message += flight.getBookingID() + "\t" + flight.getFlightID() + "\t" + flight.getCustomerID() + "\t" + flight.getPrices() + "\n";
		for (int i = 0; i < list.size(); i ++) {
			message += list.get(i).getBookingID() + "\t"  + list.get(i).getFlightID() + "\t" + list.get(i).getCustomerID() + "\t" + list.get(i).getPrices() + "\n";
		}
		
		textArea.append(title);
		textArea.append(message);
	}
	
	public void updateText(ArrayList<Flight> list) {
		String message = "UPDATE Success on " + list.get(0).getFlightID() + "\n";
		for (int i = 0; i < list.size(); i ++) {
			message += "Flight ID: " + list.get(i).getFlightID() + "," + "\t" + "Departure Date: " + list.get(i).getDepartureTime() + "," + "\t" + "Available Seats:" + list.get(i).getAvailableSeats() 
					+ "," + "\t" + "Prices: " + list.get(i).getPrices() + "\n";
		}
		textArea.append(message);
		
	}
	
	public void close() throws IOException {
		dbac.close();
	}
	
	
	
	/*public static void main(String[] args) {
		
		DBAGUI dbaGUI = new DBAGUI();
		dbaGUI.setTitle("Welcome to Airline Ticket System");
		
		dbaGUI.pack();
		dbaGUI.setLocationRelativeTo(null); // Center the frame
		dbaGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dbaGUI.setVisible(true);
	}*/

}
