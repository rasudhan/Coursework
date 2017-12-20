//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 				DBALoginGUI for DBALoginClient								*	
//*																				*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: DBALoginGUI.java		 	 					*
//* 																 			*
//*******************************************************************************
package finalproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DBALoginGUI extends JFrame{
	
	private JPanel top;
	private JPanel bot;
	private JPanel center;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JTextField field1;
	private JTextField field2;
	
	
	private JButton loginButton;
	private JButton cancelButton;
	private DBALoginClient dbac;
	private Status type;

	
	public DBALoginGUI(DBALoginClient dbac) {
		this.dbac = dbac;
		initGUI();
		layoutGUI();
		
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	public void initGUI() {
		top = new JPanel();
		center = new JPanel();
		
		loginButton = new JButton("Login");
		cancelButton = new JButton("EXIT");
		
		label1 = new JLabel("DBA Login UI");
		label2 = new JLabel("Username: ");
		label3 = new JLabel("Password: ");
		
		field1 = new JTextField(10);
		field2 = new JTextField(10);
		
		
		
	}
	
	public void layoutGUI() {
		
		setLayout( new BorderLayout());
		this.add(top, "North");
		this.add(center, "Center");
		this.setPreferredSize(new Dimension(500,200));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); // Center the frame
		this.setVisible(false);
		
		top.setLayout(new FlowLayout()); 
		top.setPreferredSize(new Dimension(500, 100));
		top.add(label1);
		top.add(Box.createRigidArea(new Dimension(400, 20)));
		top.add(label2);
		top.add(field1);
		top.add(label3);
		top.add(field2);
			
		center.setLayout( new FlowLayout());
		center.setPreferredSize(new Dimension(500, 100));
		center.add(loginButton);
		center.add(cancelButton);

		
		
	}
	
	
	public void login() {
		String username = "";
		String password = "";
		username = field1.getText();
		password = field2.getText();
		if (username.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Invalid username");
			field1.setText("");
			field1.requestFocus();
			return;
		}
		if (password.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Invalid password");
			field2.setText("");
			field2.requestFocus();
			return;
		}
		
		try {
			dbac.sendMessage(0, null, null, null, null, null, username, password, 0, type.DBALOGIN_OP);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void closeGui() {
		dispose();
		
	}
	

}

