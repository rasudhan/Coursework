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

public class StaffClient extends JFrame {
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
        
    private DataOutputStream toServer;
    private DataInputStream fromServer;
	
	public StaffClient(String hostname,int port) throws ClassNotFoundException, SQLException, UnknownHostException, IOException {
		this.hostname=hostname;
		this.port=port;
		
		// Load the JDBC driver
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		
		// Establish a connection
		//Connection connection = DriverManager.getConnection("jdbc:mysql://BUSCISMYSQL01/rasudhanDB", "rasudhan", "831117967");
		//System.out.println("Database connected\n");
		
		  // Create a socket to connect to the server
	      //Socket socket = new Socket(hostname,port);
		

	      // Create an input stream to receive data from the server
	      //fromServer = new DataInputStream(
	        //socket.getInputStream());

	      // Create an output stream to send data to the server
	      //toServer =new DataOutputStream(socket.getOutputStream());
	      
	    
	    	 
		
//		DatabaseMetaData dbMetaData = connection.getMetaData();
//
//	    ResultSet rsTables = dbMetaData.getTables(null, null, null,
//	      new String[] {"TABLE"});
//	    System.out.print("User tables: ");
//	    while (rsTables.next())
//	      System.out.print(rsTables.getString("TABLE_NAME") + " ");
//
//	    // Close the connection
//	    connection.close();
	}
	
	public StaffClient() {
		// TODO Auto-generated constructor stub
		
		JPanel p1 = new JPanel();
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

            
            jButton1.addActionListener(new ActionListener(){

    			@Override
    			public void actionPerformed(ActionEvent e) {
    				// TODO Auto-generated method stub
    				try {
    					view_actionPerformed(e);
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    				
    			}});
            pack();
	    
		this.setTitle("Staff Client");
				
	}

	void view_actionPerformed(ActionEvent e) throws IOException {
	
		 toServer.writeInt(5);
	      
	      jTextField2.setText(fromServer.readLine());
	      
	}
	void insert_actionPerformed(ActionEvent e) {
			
		}
	void update_actionPerformed(ActionEvent e) {
		
	}
	void delete_actionPerformed(ActionEvent e) {
		
	}
	void close_actionPerformed(ActionEvent e) {
			
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, UnknownHostException, IOException {
		
		
		new StaffClient("localhost",8600);
		StaffClient frame = new StaffClient(); // Create a frame
		
	    frame.pack();/// Set the frame size
	    frame.setLocationRelativeTo(null); // New since JDK 1.4
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true); // Display the frame
	}
	
}
