//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 			DBAfunction class has method which that DBASever to 			*	
//*						override table on my SQL								*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: DBAfunction.java		 	 					*
//* 																 			*
//*******************************************************************************
package finalproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBAfunction {
	private Status type;

	private Statement st = null;
	private Statement st1 = null;
	private PreparedStatement preparedStmt = null;
	private PreparedStatement preparedStmt1 = null;
	private ResultSet rs = null;
	private ResultSet rs1 = null;

	
	public DBAfunction() {
		
	}
	
	public ArrayList<Flight> addFlight(ArrayList<Flight> list, Connection connect) throws SQLException {
		int flightID = list.get(0).getFlightID();
		String airlineName = list.get(0).getAirlineName();
		String from = list.get(0).getFromAirport();
		String to = list.get(0).getToAirport();
		String dTime = list.get(0).getDepartureTime();
		String aTime = list.get(0).getArrivalTime();
		int totalSeats = list.get(0).getTotalSeats();
		int avaSeats = list.get(0).getAvailableSeats();
		double prices = list.get(0).getPrices();
		String dDate = "";
				
		String regex = "(^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(dTime);
		
		if (matcher.find()) {
			dDate = matcher.group(1);
		}

		
		
		String query = "SELECT * FROM group3db.flight WHERE flightID_PK = ?";
		preparedStmt = connect.prepareStatement(query);
		preparedStmt.setInt(1, flightID);
		rs = preparedStmt.executeQuery();
		
		if (rs.next()) {
			Flight flight = new Flight(0, 0, 0, null, null, null, null, null, 0, 0, 0, 0, type.ADDFLIGHT_OP);
			list.add(flight);
			return list;
		}
		else {
			//st = connect.createStatement();
			String query1= "INSERT INTO group3db.flight VALUES(?,?,?,?,?,?,?)";
			preparedStmt = connect.prepareStatement(query1);
			preparedStmt.setString(1, airlineName);
			preparedStmt.setInt(2, flightID);
			preparedStmt.setString(3, from);
			preparedStmt.setString(4, to);
			preparedStmt.setString(5, dTime);
			preparedStmt.setString(6, aTime);
			preparedStmt.setInt(7, totalSeats);
			preparedStmt.execute();	
			
			//st1 = connect.createStatement();
			String query3 = "INSERT INTO group3db.flight_details VALUES(?,?,?,?)";
			preparedStmt1 = connect.prepareStatement(query3);
			preparedStmt1.setInt(1, avaSeats);	
			preparedStmt1.setInt(2, flightID);
			preparedStmt1.setString(3, dDate);
			preparedStmt1.setDouble(4, prices);
			preparedStmt1.execute();
			
			Flight flight = new Flight (0, 0, flightID, airlineName, from, to, dTime, aTime, totalSeats, avaSeats, prices, 0, type.ADDFLIGHT_OP);
			list = new ArrayList<Flight>();
			list.add(flight);
			return list;
		}
		
		
	}
	
	public ArrayList<Flight> view(ArrayList<Flight> list, Connection connect) throws SQLException {
		
		st = connect.createStatement();
        rs = st.executeQuery("SELECT * FROM group3db.booking_ticket");
        list = new ArrayList<Flight>();
		while (rs.next()) {
			int customerID = rs.getInt("custID_FK");
			int flightID = rs.getInt("flightID_FK");
			double prices = rs.getDouble("prices");
			int bookingID = rs.getInt("bookingID_PK");
			
			Flight flight = new Flight (customerID, bookingID, flightID, null, null, null, null, null, 0, 0, prices, 0, type.VIEW_OP);		
			list.add(flight);
					
		}
		
		return list;
		
	}
	
	public ArrayList<Flight> update(ArrayList<Flight> list, Connection connect) throws SQLException {

		int flightID = list.get(0).getFlightID();
		double prices = list.get(0).getPrices();
		int avaSeats =list.get(0).getAvailableSeats();
		
		
		String query = "SELECT * FROM group3db.flight_details WHERE flightID_FK = ?";
		preparedStmt = connect.prepareStatement(query);
		preparedStmt.setInt(1, flightID);
		rs = preparedStmt.executeQuery();
		
		if (rs.next()) {
			double prices1 = rs.getDouble("prices");
			int avaSeats1 = rs.getInt("available_seats");
			String dDate = rs.getString("departure_date");
			
			if (prices != 0  && prices != prices1) {
				String sql = "UPDATE group3db.flight_details SET prices = ? WHERE flightID_FK = ?";
				preparedStmt1 = connect.prepareStatement(sql);
				preparedStmt1.setDouble(1, prices);
				preparedStmt1.setInt(2, flightID);
				preparedStmt1.executeUpdate();
			}
			if (avaSeats != 0 && avaSeats != avaSeats1) {
				String sql = "UPDATE group3db.flight_details SET available_seats = ? WHERE flightID_FK = ?";
				preparedStmt1 = connect.prepareStatement(sql);
				preparedStmt1.setInt(1, avaSeats);
				preparedStmt1.setInt(2, flightID);
				preparedStmt1.executeUpdate();
			}
			
				Flight flight = new Flight (0, 0, flightID, null, null, null, dDate, null, 0, avaSeats, prices, 0, type.UPDATE_OP);
				list = new ArrayList<Flight>();
				list.add(flight);
				return list;
		
	
		}

		else {
			Flight flight = new Flight (0, 0, 0, null, null, null, null, null, 0, avaSeats, prices, 0, type.UPDATE_OP);
			list = new ArrayList<Flight>();
			list.add(flight);
			return list;
		}
	
	}//end of update action
	
	public ArrayList<Flight> close(ArrayList<Flight> list, Connection connect) {
		Flight flight = new Flight(0, 0, 0, null, null, null, null, null, 0, 0, 0, 0, type.CLOSE_OP);
		list = new ArrayList<Flight>();
		list.add(flight);
		return list;
	}

}
