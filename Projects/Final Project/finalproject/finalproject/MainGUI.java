//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 			starting with MainGUI for this program after connect to MySQL	*	
//*																				*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: MainGUI.java		 	 						*
//* 																 			*
//*******************************************************************************
package finalproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MainGUI extends JFrame{

	private JPanel top;
	private JPanel bot;
	private JPanel center;
	private JLabel label1;
	private JRadioButton customerButton;
	private JRadioButton dbaButton;
	private JButton startButton;
	private CustomerLogClient client;

	private String hostname = "localhost";
	private int port = 8888;
	public MainGUI() {
		initGUI();
		layoutGUI();

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					start();
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
		label1 = new JLabel("Please select your status");
		customerButton = new JRadioButton("Customer");
		dbaButton = new JRadioButton("DBA");
		startButton = new JButton("Start !");

		ButtonGroup groupButton = new ButtonGroup();
		groupButton.add(customerButton);
		groupButton.add(dbaButton);


	}


	public void layoutGUI() {

		setLayout( new BorderLayout());
		this.add(top, "North");
		this.add(center, "Center");
		this.add(bot, "South");
		this.setPreferredSize(new Dimension(400,150));

		top.setLayout(new FlowLayout()); 

		top.add(label1);


		center.setLayout( new FlowLayout());
		center.add(customerButton);
		center.add(dbaButton);


		bot.setLayout(new FlowLayout());
		bot.add(startButton);


	}


	public void start() throws UnknownHostException, IOException {

		if (customerButton.isSelected() == false && dbaButton.isSelected() == false) {
			JOptionPane.showMessageDialog(null, "Please select your status before clicking start");
			return;
		}

		else if (customerButton.isSelected() == true ) {

			CustomerLogClient client = new CustomerLogClient(hostname, port);
			client.viewCustomerGui();
			dispose();

		}

		else if (dbaButton.isSelected() == true) {

			DBALoginClient dbac = new DBALoginClient(hostname, port);
			dbac.viewGUI();
			dispose();
		}
	}




	public static void main(String[] args) {
		MainGUI gui = new MainGUI();
		gui.setTitle("Welcome to Airline Ticket System");
		gui.pack();
		gui.setLocationRelativeTo(null); // Center the frame
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
	}
}
