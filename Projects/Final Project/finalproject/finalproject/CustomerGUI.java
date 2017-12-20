//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 			CustomerGUI class for CustomerLogClient 						*	
//*																				*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: CustomerGUI.java		 	 					*
//* 																 			*
//*******************************************************************************
package finalproject;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomerGUI extends JFrame {

	private JPanel main;
	private JPanel cards;
	private CustomPanel newCustomer;
	private CustomPanel curCustomer;

	private JComboBox statusBox;

	private JButton createButton;
	private JButton loginButton;
	private JButton cancelButton;
	private JButton leaveButton;

	private JLabel accLabel;
	private JLabel passLabel;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JTextField field1;
	private JTextField field2;
	private JTextField field3;
	private JTextField field4;
	private JTextField field5;
	private JTextField field6;
	private JTextField field7;
	private JTextField field8;
	private JTextField field9;
	private MainGUI g;

	Customer customer;
	Status type;
	CustomerLogClient client;

	class CustomPanel extends JPanel {
		private final String name;

		public CustomPanel(String name) {
			this.name = name;
			this.setPreferredSize(new Dimension(500, 240));
			this.add(new JLabel(name));

		}

		@Override
		public String toString() {
			return name;
		}
	}

	public CustomerGUI(CustomerLogClient client) {
		this.client = client;
		initGUI();
		doTheLayout();


		statusBox.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					
					Object item = e.getItem();
					
					if (item.equals(newCustomer)) {
						JComboBox b = (JComboBox) e.getSource();
						CardLayout c = (CardLayout) cards.getLayout();
						c.show(cards, b.getSelectedItem().toString());
						
					} else if (item.equals(curCustomer)) {
						JComboBox b = (JComboBox) e.getSource();
						CardLayout c = (CardLayout) cards.getLayout();
						c.show(cards, b.getSelectedItem().toString());
					}
				}
			}

		});

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enter();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		createButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				create();
			}
			
		});
		
		leaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}



	public void initGUI() {

		CardLayout card = new CardLayout();
		main = new JPanel();
		cards = new JPanel(card);
		curCustomer = new CustomPanel("Current Customer Log in");
		newCustomer = new CustomPanel("New Customer Sign up");

		statusBox = new JComboBox();
		statusBox.addItem(curCustomer);
		statusBox.addItem(newCustomer);

		cards.add(curCustomer, curCustomer.toString());
		cards.add(newCustomer, newCustomer.toString());

		main.add(statusBox);

		createButton = new JButton("Create account");
		loginButton = new JButton("Login");
		cancelButton = new JButton("Cancel");
		leaveButton = new JButton("Leave");

		accLabel = new JLabel("Username: ");
		passLabel = new JLabel("Password: ");
		field1 = new JTextField(15);
		field2 = new JTextField(15);
		
		label1 = new JLabel("New Username: ");
		label2 = new JLabel("New Password: ");
		label3 = new JLabel("First Name: ");
		label4 = new JLabel("Last Name: ");
		label5 = new JLabel("Address: ");
		label6 = new JLabel("Telephone number: ");
		label7 = new JLabel("E-mail Address: ");
		
		field3 = new JTextField(15);
		field4 = new JTextField(15);
		field5 = new JTextField(10);
		field6 = new JTextField(10);
		field7 = new JTextField(30);
		field8 = new JTextField(10);
		field9 = new JTextField(10);
	}

	public void doTheLayout() {
		
		

		curCustomer.setLayout(new FlowLayout());
		curCustomer.setPreferredSize(new Dimension(500, 150));
		curCustomer.add(Box.createRigidArea(new Dimension(300, 0)));
		curCustomer.add(accLabel);
		curCustomer.add(field1);
		curCustomer.add(passLabel);
		curCustomer.add(field2);
		curCustomer.add(Box.createRigidArea(new Dimension(0, 120)));
		curCustomer.add(loginButton);
		curCustomer.add(Box.createRigidArea(new Dimension(10, 0)));
		curCustomer.add(leaveButton);
	

		newCustomer.setLayout(new FlowLayout());
		newCustomer.setPreferredSize(new Dimension(500, 300));
		newCustomer.add(Box.createRigidArea(new Dimension(300, 30)));
		
		newCustomer.add(label1);
		newCustomer.add(field3);
		newCustomer.add(Box.createRigidArea(new Dimension(185, 0)));
		
		newCustomer.add(label2);
		newCustomer.add(field4);
		newCustomer.add(Box.createRigidArea(new Dimension(187, 30)));
		
		newCustomer.add(label3);
		newCustomer.add(field5);
		newCustomer.add(label4);
		newCustomer.add(field6);
		newCustomer.add(Box.createRigidArea(new Dimension(55, 10)));
		
		newCustomer.add(label5);
		newCustomer.add(field7);
		newCustomer.add(Box.createRigidArea(new Dimension(40, 10)));
		
		newCustomer.add(label6);
		newCustomer.add(field8);
		newCustomer.add(label7);
		newCustomer.add(field9);
		newCustomer.add(Box.createRigidArea(new Dimension(380, 40)));
		
		
		newCustomer.add(createButton);
		newCustomer.add(Box.createRigidArea(new Dimension(10, 0)));
		newCustomer.add(cancelButton);
		
		
		setLayout(new BorderLayout());
		this.add(main, "North");
		this.add(cards, "Center");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); // Center the frame
		this.setVisible(false);
	}

	public void enter() {
		
		String account = "";
		String password = "";
		
		account = field1.getText();
		password = field2.getText();
		if (field1.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Please enter your username!");
			field1.requestFocus();
			return;
		}
		else if (field2.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Please enter your password!");
			field2.requestFocus();
			return;
		}
		
		Customer customer = new Customer (0, null, null, null, null, null, account, password, 0, type.LOGIN_OP);
		
		try {
			//client = new CustomerLogClient(hostname, port);
			client.loginCustomer(customer);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		field1.setText(null);
		field2.setText(null);
	}
	
	public void create() {
		
		String account = "";
		String password = "";
		String fName = "";
		String lName = "";
		String address = "";
		String email = "";
		String phone = "";
		
		account = field3.getText();
		password = field4.getText();		
		fName = field5.getText();
		lName = field6.getText();
		address = field7.getText();
		phone = field8.getText();
		email = field9.getText();
		
		if (field3.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Please enter your username!");
			field3.requestFocus();
			return;
		}
		else if (field4.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Please enter your password!");
			field4.requestFocus();
			return;
		}
		
		else if (field5.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Please enter your first name!");
			field5.requestFocus();
			return;
		}
		
		else if (field6.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Please enter your last name!");
			field6.requestFocus();
			return;
		}
		
		else if (field7.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Please enter your address!");
			field7.requestFocus();
			return;
		}
		
		else if (field8.getText().equalsIgnoreCase("") || !phone.matches("[0-9]{10}")) {
			JOptionPane.showMessageDialog(null, "Phone number invalid!");
			field8.setText(null);
			field8.requestFocus();
			return;
		}
		
		else if (field9.getText().equalsIgnoreCase("") || !email.contains("@")) {
			JOptionPane.showMessageDialog(null, "Email address invalid!");
			field9.setText(null);
			field9.requestFocus();
			return;
		}

		
		Customer customer = new Customer(0, fName, lName, address, phone, email, account, password, 0, type.CREATE_OP);
		
		try {
			//client = new CustomerLogClient(hostname, port);
			client.createCustomer(customer);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		field3.setText(null);
		field4.setText(null);
		field5.setText(null);
		field6.setText(null);
		field7.setText(null);
		field8.setText(null);
		field9.setText(null);
	}
	
	public void closeGui() {
		dispose();
		
	}

	

}
