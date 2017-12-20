//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 	Login class has methods for MainSever to process login action			*	
//*																				*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: Login.java		 	 						*
//* 																 			*
//*******************************************************************************
package finalproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JOptionPane;

public class Login {

	private Status type;

	private Statement st = null;
	private Statement st1 = null;
	private PreparedStatement preparedStmt = null;
	private PreparedStatement preparedStmt1 = null;
	private ResultSet rs = null;
	private ResultSet rs1 = null;

	private Random rnd = new Random();


	public Login() {

	}
	
	public Customer loginAction (Customer customer, Connection connect) throws SQLException {
		
		String username = customer.getUsername();
		String password = customer.getPassword();
		
		String query ="SELECT * FROM group3db.customer WHERE username = ?";
		preparedStmt = connect.prepareStatement(query);
		preparedStmt.setString(1, username);
		rs = preparedStmt.executeQuery();
		
		if (rs.next()) {
			String query1 ="SELECT * FROM group3db.customer WHERE password = ?";
			preparedStmt1 = connect.prepareStatement(query1);
			preparedStmt1.setString(1, password);
			rs1 = preparedStmt1.executeQuery();
				if (rs1.next()) {		
						int custID = rs1.getInt("custID_PK");
						String fName = rs1.getString("firstname");
						String lName = rs1.getString("lastname");
						String address = rs1.getString("address");
						String phone = rs1.getString("telephone");
						String email = rs1.getString("email");
						int point = rs1.getInt("extrapoints");
						customer = new Customer (custID, fName, lName, address, phone, email, username, password, point, type.LOGIN_OP);
						return customer;
						
				}
				else {
					customer = new Customer (0, null, null, null, null, null, null, null, 0, type.LOGIN_OP);
					return customer;
				}
		}
		
		else {
			customer = new Customer (0, null, null, null, null, null, null, null, 0, type.LOGIN_OP);
			return customer;
		}
			
	}//ending loginAction method



	public Customer createAction(Customer customer, Connection connect) throws SQLException, IOException {


		int idNumber = 0;
		Integer id; 
		String firstname = customer.getFirstName();
		String lastname = customer.getLastName();
		String address = customer.getAddress();
		String username = customer.getUsername();
		String password = customer.getPassword();
		String telephone = customer.getTelephone();
		String email = customer.getEmail();
		Integer points = 0;
		
		idNumber = 10000000 + rnd.nextInt(90000000);
		id = idNumber;


		String query1 = "SELECT * FROM group3db.customer WHERE custID_PK = ?";
		preparedStmt1 = connect.prepareStatement(query1);


		while (true) {
			preparedStmt1.setInt(1, id);
			rs1 = preparedStmt1.executeQuery();
			if (rs1.next()) {
				idNumber = 10000000 + rnd.nextInt(90000000);
				id = idNumber;
				continue;
			}
			else {
				break;
			}
		}
		
		String query ="SELECT * FROM group3db.customer WHERE username = ?";
		preparedStmt = connect.prepareStatement(query);
		preparedStmt.setString(1, username);
		rs = preparedStmt.executeQuery();

		if (rs.next()) {
			customer = new Customer(0, null, null, null, null, null, null, null, 0, type.CREATE_OP);
			return customer;
		}
		else {
			st = connect.createStatement();
			String sql = "INSERT INTO group3db.customer VALUES(?,?,?,?,?,?,?,?,?)";
			preparedStmt = connect.prepareStatement(sql);
			preparedStmt.setInt(1, id);
			preparedStmt.setString(2, firstname);
			preparedStmt.setString(3, lastname);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, username);
			preparedStmt.setString(6, password);
			preparedStmt.setString(7, telephone);
			preparedStmt.setString(8, email);
			preparedStmt.setInt(9, points);
			preparedStmt.execute();
			customer = new Customer(id, firstname, lastname, address, username, password, telephone, email, points, type.CREATE_OP);
			return customer;			
		}
	}//end of createAction method
	
	public Customer dbaLogin(Customer customer, Connection connect) throws SQLException {

		String username = customer.getUsername();
		String password = customer.getPassword();
		String password1 = "";
		int dbaID;
		String fName = "";
		String lName = "";
		
		String query ="SELECT * FROM group3db.admin WHERE username = ?";
		preparedStmt = connect.prepareStatement(query);
		preparedStmt.setString(1, username);
		rs = preparedStmt.executeQuery();
		if (rs.next()) {
			password1 = rs.getString("password");
			
			if (password.equals(password1)) {
				dbaID = rs.getInt("adminID");
				fName = rs.getString("firstName");
				lName = rs.getString("lastName");
				customer = new Customer (dbaID, fName, lName, null, null, null, username, password, 0, type.DBALOGIN_OP);
				return customer;
				
			}
			else {
				customer = new Customer (0, fName, lName, null, null, null, username, password, 0, type.DBALOGIN_OP);
				return customer;
			}
			
		}
		else {
			customer = new Customer (0, fName, lName, null, null, null, username, password, 0, type.DBALOGIN_OP);
			return customer;
		}
		
		
		
	}
}
