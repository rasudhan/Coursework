//*************************************************************************
//* 																
//* 				CIS611 Spring 2017 Anand RameshKannan
//* 															
//* 					Program Assignment PA08				 
//* 																 
//* 				Program to View/Insert/Update/Delete records in DataBase
//* 																 
//* 					Date Created: 04.07.2017 					 
//*					Saved in: StaffClient.java		 	 
//* 																 
//**************************************************************************
package PA08;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;



import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class StaffClient extends JFrame implements ActionListener {
	String hostname;
	int port;
	
	//GUI Components                     
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField4;
    private JTextField jTextField5;
    private JTextField jTextField6;
    private JTextField jTextField7;
    private JTextField jTextField8;
        

    Message message;
    Socket socket;
    ObjectInputStream fromServer;
    ObjectOutputStream toServer;
    
	public StaffClient(String hostname,int port) throws ClassNotFoundException, SQLException, UnknownHostException, IOException {
		
		
		  // Create a socket to connect to the server
	      socket = new Socket(hostname,port);
		
	      // Create an input stream to receive data from the server
	    fromServer = new ObjectInputStream(socket.getInputStream());

	      // Create an output stream to send data to the server
	      toServer =new ObjectOutputStream(socket.getOutputStream());
	
	}
	
	public StaffClient() {
		// TODO Auto-generated constructor stub
		//GUI Creation
		
		jLabel1 = new JLabel("ID");
        jTextField1 = new JTextField();
        jLabel2 = new JLabel("Last Name");
        jTextField2 = new JTextField();
        jLabel3 = new JLabel("First Name");
        jTextField3 = new JTextField();
        jLabel4 = new JLabel("Middle");
        jTextField4 = new JTextField();
        jLabel5 = new JLabel("Address");
        jTextField5 = new JTextField();
        jLabel6 = new JLabel("City");
        jTextField6 = new JTextField();
        jLabel7 = new JLabel("State");
        jTextField7 = new JTextField();
        jLabel8 = new JLabel("Telephone");
        jTextField8 = new JTextField();
        jButton1 = new JButton("View");
        jButton2 = new JButton("Insert");
        jButton3 = new JButton("Update");
        jButton4 = new JButton("Delete");
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel1)
                                        .addGap(41, 41, 41))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel8))
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton4)))
                    .addContainerGap(46, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(23, 23, 23)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(23, 23, 23)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton3)
                        .addComponent(jButton4))
                    .addContainerGap(42, Short.MAX_VALUE))
            );

            jButton1.addActionListener(this);
            jButton2.addActionListener(this);
            jButton3.addActionListener(this);
            jButton4.addActionListener(this);
            
            pack();
	    
		this.setTitle("Staff Client");
				
	}
	
	public void sendMessage(int id, String firstName, String lastName, char mi, String address, String city, String state, String telephone, String type) throws IOException, ClassNotFoundException {	

		message = new Message (id, firstName, lastName, mi, address, city, state, telephone, "VIEW");
		toServer.writeObject(message); //Send to server
		receivingMessage(); //Receive from server

	}

	private void receivingMessage() throws IOException, ClassNotFoundException {

		message = (Message)fromServer.readObject();


		switch (message.opType) { 

		case "VIEW": if (message.id != 0){

			jTextField2.setText(message.lastName);
			 jTextField3.setText(message.firstName);
			 jTextField4.setText(String.valueOf(message.mi));
			 jTextField5.setText(message.address);
			 jTextField6.setText(message.city);
			 jTextField7.setText(message.state);
			 jTextField8.setText(message.telephone);
			 break;

		}  
		
		case "DELETE":
			jTextField1.setText(null);
			jTextField2.setText(null);
			jTextField3.setText(null);
			jTextField4.setText(null);
			jTextField5.setText(null);
			jTextField6.setText(null);
			jTextField7.setText(null);
			jTextField8.setText(null);
			jTextField1.requestFocus();
			break;
			

		case "INSERT":
			jTextField1.setText(null);
			jTextField2.setText(null);
			jTextField3.setText(null);
			jTextField4.setText(null);
			jTextField5.setText(null);
			jTextField6.setText(null);
			jTextField7.setText(null);
			jTextField8.setText(null);
			jTextField1.requestFocus();
			break;
		
		default: //for UPDATE 
			jTextField1.setText(String.valueOf(message.id));
			jTextField2.setText(message.lastName);
			 jTextField3.setText(message.firstName);
			 jTextField4.setText(String.valueOf(message.mi));
			 jTextField5.setText(message.address);
			 jTextField6.setText(message.city);
			 jTextField7.setText(message.state);
			 jTextField8.setText(message.telephone);
			 
		}

	}

	void view_actionPerformed(ActionEvent e) throws IOException, ClassNotFoundException {
	
		int id;
		try {
			id=Integer.parseInt(jTextField1.getText());
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Please enter a valid ID! Ex:1");
			return;
		}
		 
		 
		 sendMessage(id, "", "",'A',"","", "", "", "VIEW");
	      
		 message=(Message)fromServer.readObject();
		 
		 jTextField2.setText(message.lastName);
		 jTextField3.setText(message.firstName);
		 jTextField4.setText(String.valueOf(message.mi));
		 jTextField5.setText(message.address);
		 jTextField6.setText(message.city);
		 jTextField7.setText(message.state);
		 jTextField8.setText(message.telephone);
		 
		 
	      
	}
	void insert_actionPerformed(ActionEvent e) throws ClassNotFoundException, IOException {
		
		String input;int id;
		try {
			input = jTextField1.getText();
			id = Integer.parseInt(input);
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Please enter your valid id number");
			return;
		}

		String lName = jTextField2.getText();
		String fName = jTextField3.getText();
		if (jTextField2.getText().equalsIgnoreCase("") || jTextField3.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Please enter your last name and first name");
			return;
		}

		char mi;
		try {
			input = jTextField4.getText();
		     mi = input.charAt(0);
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Please enter a valid mi character (Ex: F)");
			return;
		}

		String address = jTextField5.getText();
		if (jTextField5.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Please enter your  address");
			return;
		}

		String city = jTextField6.getText();
		if (jTextField6.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Please enter your city of address");
			return;
		}

		String state = jTextField7.getText();
		if (jTextField7.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Please enter your state of address");
			return;
		}
		String telephone ;
		try {
			telephone = jTextField8.getText();

			if (!telephone.matches("^[0-9]{10}$")) {
				JOptionPane.showMessageDialog(null, "Please enter a valid phone number! Ex: 9704027949");	
				return;
			}
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Please enter valid phone number! Ex: 9704027949");
			return;
		}
		sendMessage(id, fName, lName, mi, address, city, state, telephone, "INSERT");
		System.out.println("Record Inserted successfully!");

			
		}
	void update_actionPerformed(ActionEvent e) throws ClassNotFoundException, IOException {
		
		String input;int id;
		try {
			input = jTextField1.getText();
			id = Integer.parseInt(input);
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Please enter your valid id number");
			return;
		}

		String lName = jTextField2.getText();
		String fName = jTextField3.getText();
		if (jTextField2.getText().equalsIgnoreCase("") || jTextField3.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Please enter your last name and first name");
			return;
		}

		char mi;
		try {
			input = jTextField4.getText();
		     mi = input.charAt(0);
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Please enter a valid mi character (Ex: F)");
			return;
		}

		String address = jTextField5.getText();
		if (jTextField5.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Please enter your  address");
			return;
		}

		String city = jTextField6.getText();
		if (jTextField6.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Please enter your city of address");
			return;
		}

		String state = jTextField7.getText();
		if (jTextField7.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Please enter your state of address");
			return;
		}
		String telephone ;
		try {
			telephone = jTextField8.getText();

			if (!telephone.matches("^[0-9]{10}$")) {
				JOptionPane.showMessageDialog(null, "Please enter a valid phone number! Ex: 9704027949");	
				return;
			}
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Please enter valid phone number! Ex: 9704027949");
			return;
		}
		sendMessage(id, fName, lName, mi, address, city, state, telephone, "UPDATE");
		JOptionPane.showMessageDialog(null, "Record Updated Successfully!");
		
	}
	void delete_actionPerformed(ActionEvent e) throws ClassNotFoundException, IOException {
		
		String input;int id=0;
		try {
			input = jTextField1.getText();
			id = Integer.parseInt(input);
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Please enter the id of record you want to delete! Ex:1");
			return;
		}
 		
		sendMessage(id, null, null, '\u0000', null, null, null, null, "DELETE");
		
		//Reset input fields
		jTextField1.setText(null);
		jTextField2.setText(null);
		jTextField3.setText(null);
		jTextField4.setText(null);
		jTextField5.setText(null);
		jTextField6.setText(null);
		jTextField7.setText(null);
		jTextField8.setText(null);
		jTextField1.requestFocus();
		
		JOptionPane.showMessageDialog(null, "Record Deletion Successful!");
		
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, UnknownHostException, IOException {
		
		
		new StaffClient("localhost",8557); //Listen to port and host //Not database!
		
		
		StaffClient frame = new StaffClient(); // Create a frame
	    frame.pack();/// Set the frame size
	    frame.setLocationRelativeTo(null); // New since JDK 1.4
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true); // Display the frame
	}

	@Override /**Action Events*/
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==jButton1) {
			try {
				view_actionPerformed(e);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==jButton2)
			try {
				insert_actionPerformed(e);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		else if(e.getSource() == jButton3)
			try {
				update_actionPerformed(e);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		else if(e.getSource()==jButton4)
			try {
				delete_actionPerformed(e);
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
	}
	
}
