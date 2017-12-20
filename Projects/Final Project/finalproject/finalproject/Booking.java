//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 			booking class has methods which that bookingSever to 			*	
//*						override table on my SQL								*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: Booking.java		 	 						*
//* 																 			*
//*******************************************************************************
package finalproject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Booking {

	private Status type;

	private Statement st = null;
	private Statement st1 = null;
	private PreparedStatement preparedStmt = null;
	private PreparedStatement preparedStmt1 = null;
	private ResultSet rs = null;
	private ResultSet rs1 = null;
	private Random rnd = new Random();
	private String [] airline = {"Alaska Airlines", "Allegiant Air", "American Airlines", "Delta Air Lines", "Frontier Airlines", "Hawaiian Airlines", "JetBlue", "Southwest Airlines", 
			"Spirit Airlines", "United Airlines", "Virgin America"};


	public Booking() {

	}

	public Flight createFlight(ArrayList<Flight> list, Connection connect) throws SQLException {

		int idNumber = 0;
		int flightID = 0;
		int customerID = list.get(0).getCustomerID();
		String fromAirport = list.get(0).getFromAriport();
		String toAirport = list.get(0).getToAirport();
		int ticket = list.get(0).getTicket();

		String dDate = list.get(0).getDepartureTime();
		String dTime = dDate;
		String aTime = dTime;
		int totalSeats = 0;
		int avaSeats = 0;
		double prices = 0;
		int x = rnd.nextInt(10) + 1;
		String airlineName = airline[x];

		idNumber = 100 + rnd.nextInt(900);
		flightID = idNumber;

		String query2 = "SELECT * FROM group3db.flight WHERE flightID_PK = ?";
		preparedStmt = connect.prepareStatement(query2);
		while (true) {
			preparedStmt.setInt(1, flightID);
			rs = preparedStmt.executeQuery();
			if (rs.next()) {
				idNumber = 100 + rnd.nextInt(900);
				flightID = idNumber;
				continue;
			}
			else {
				break;
			}
		}	

		int h  = rnd.nextInt(20) + 1;
		int m = rnd.nextInt(59);

		dTime = dTime + "  " + String.valueOf(h) + ":" + String.valueOf(m);
		aTime = aTime + "  " + String.valueOf(h + 3) + ":" + String.valueOf(m);

		totalSeats = 200 + rnd.nextInt(130);//total seat range
		avaSeats = totalSeats - rnd.nextInt(130) - 72;//min available seat is 0
		prices = rnd.nextInt(150) + rnd.nextInt(200) + 150;//price range

		//st = connect.createStatement();
		String query1= "INSERT INTO group3db.flight VALUES(?,?,?,?,?,?,?)";
		preparedStmt = connect.prepareStatement(query1);
		preparedStmt.setString(1, airlineName);
		preparedStmt.setInt(2, flightID);
		preparedStmt.setString(3, fromAirport);
		preparedStmt.setString(4, toAirport);
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

		Flight flight = new Flight (0, customerID, flightID, airlineName, fromAirport, toAirport, dTime, aTime, totalSeats, avaSeats, prices, ticket, type.SERACH_OP);

		return flight;
	}



	public ArrayList<Flight> serachAction(ArrayList<Flight> list, Connection connect) throws SQLException, ParseException {

		int customerID = list.get(0).getCustomerID();
		String fromAirport = list.get(0).getFromAriport();
		String toAirport = list.get(0).getToAirport();
		int ticket = list.get(0).getTicket();

		int idNumber = 0;
		int flightID = 0;
		idNumber = 100 + rnd.nextInt(900);
		flightID = idNumber;

		int x = 0;
		String airlineName;
		x = rnd.nextInt(10) + 1;
		airlineName = airline[x];

		String dDate = list.get(0).getDepartureTime();
		String dTime = dDate;

		String aTime = dTime;
		int totalSeats;
		int avaSeats;
		double prices;
		ArrayList<Flight> list1 = new ArrayList<Flight>();


		for (int i = 0; i < 10; i++) {
			String query ="SELECT * FROM group3db.flight WHERE from_airport = ?";
			preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, fromAirport);
			rs = preparedStmt.executeQuery();

			if(rs.next()) {
				String query1 ="SELECT * FROM group3db.flight WHERE to_airport = ?";
				preparedStmt = connect.prepareStatement(query1);
				preparedStmt.setString(1, toAirport);
				rs = preparedStmt.executeQuery();

				if(rs.next()) {
					String query2 = "SELECT * FROM group3db.flight_details WHERE departure_date = ?";
					preparedStmt = connect.prepareStatement(query2);
					preparedStmt.setString(1, dDate);
					rs = preparedStmt.executeQuery();				
					if(rs.next()) {

						if (flightID == rs.getInt("flightID_FK")) {
							prices = rs.getDouble("prices");
							avaSeats = rs.getInt("available_seats");

							String query4 = "SELECT * FROM group3db.flight WHERE flightID_PK = ?";
							preparedStmt = connect.prepareStatement(query4);
							preparedStmt.setInt(1, flightID);
							rs = preparedStmt.executeQuery();
							if (rs.next()) {
								//flightID = rs4.getInt("flightID");
								airlineName = rs.getString("airline_name");
								dTime = rs.getString("departure_time");
								aTime = rs.getString("arrival_time");
								totalSeats = rs.getInt("total_seats");	

								Flight flight = new Flight(0, customerID, flightID, airlineName, fromAirport, toAirport, dTime, aTime, totalSeats, avaSeats, prices, ticket, type.SERACH_OP);

								list1.add(flight);
							}
						}
					}
				}
			}
			list1.add(createFlight(list, connect));//create new flight and add into my arraylist
		}
		return list1;

	}//end of serachAction


	public ArrayList<Flight> bookingAction(ArrayList<Flight> list, Connection connect) throws SQLException {
		int flightID = list.get(0).getFlightID();
		int customerID = list.get(0).getCustomerID();

		int ticket = list.get(0).getTicket();
		double prices = 0;
		double totalPrices = 0;
		int avaSeats = 0;
		int bookingID = 1000 + rnd.nextInt(9000);
		int flightNumber = 0;
		int extrapoints = 0;
		String dDate = null;
		String cDate = null;

		//check if customer has already booked a flight on same day
		String query2 ="SELECT * FROM group3db.booking_ticket WHERE custID_FK = ?";
		preparedStmt = connect.prepareStatement(query2);
		preparedStmt.setInt(1, customerID);
		rs = preparedStmt.executeQuery();
		if (rs.next()) {
			flightNumber = rs.getInt("flightID_FK");	

			String query3 ="SELECT * FROM group3db.flight_details WHERE flightID_FK = ?";
			preparedStmt = connect.prepareStatement(query3);
			preparedStmt.setInt(1, flightNumber);
			rs = preparedStmt.executeQuery();
			if(rs.next()) {
				cDate = rs.getString("departure_date");

				String query1 ="SELECT * FROM group3db.flight_details WHERE flightID_FK = ?";
				preparedStmt = connect.prepareStatement(query1);
				preparedStmt.setInt(1, flightID);
				rs = preparedStmt.executeQuery();
				if (rs.next()) {
					dDate = rs.getString("departure_date");

					if (cDate.equals(dDate)){
						Flight flight = new Flight (0, customerID, flightID, null, null, null, null, null, 0, 0, 0, ticket, type.BOOKING_OP);
						list = new ArrayList<Flight>();
						list.add(flight);
						return list;
					}
				}


			}

		}

		//check customer extra point for discount
		String query3 = "SELECT * FROM group3db.customer WHERE custID_PK = ?";
		preparedStmt = connect.prepareStatement(query3);
		preparedStmt.setInt(1, customerID);
		rs = preparedStmt.executeQuery();

		if (rs.next()) {
			extrapoints = rs.getInt("extrapoints");
		}

		String query ="SELECT * FROM group3db.flight_details WHERE flightID_FK = ?";
		preparedStmt = connect.prepareStatement(query);
		preparedStmt.setInt(1, flightID);
		rs = preparedStmt.executeQuery();
		if (rs.next()) {
			prices= rs.getDouble("prices");
			avaSeats = rs.getInt("available_seats");
			dDate = rs.getString("departure_date");

			avaSeats = avaSeats - ticket;
			totalPrices = prices * ticket;

			//10% discount if extrapoints >= 2000
			if (extrapoints >= 2000) {
				totalPrices = totalPrices - totalPrices * 0.1;
			}



			//Updating new available seats after customer booking
			String query1 = "UPDATE group3db.flight_details SET available_seats = ? WHERE flightID_FK = ?";
			preparedStmt1 = connect.prepareStatement(query1);
			preparedStmt1.setInt(1, avaSeats);
			preparedStmt1.setInt(2, flightID);
			preparedStmt1.execute();

			while (true){//check if the bookingID is duplicated
				String query4 = "SELECT * FROM group3db.booking_ticket WHERE bookingID_PK = ?";
				preparedStmt = connect.prepareStatement(query4);
				preparedStmt.setInt(1, bookingID);
				rs = preparedStmt.executeQuery();

				if(rs.next()) {
					bookingID = 1000 + rnd.nextInt();
					continue;

				}
				else {
					break;
				}

			}
			//inserting new data into booking_tick table
			String query4 = "INSERT INTO group3db.booking_ticket VALUES(?,?,?,?)";
			preparedStmt = connect.prepareStatement(query4);
			preparedStmt.setInt(1, customerID);
			preparedStmt.setInt(2, flightID);
			preparedStmt.setDouble(3, totalPrices);
			preparedStmt.setInt(4, bookingID);
			preparedStmt.execute();
			//Updating new extra points for customer
			String query5 = "UPDATE group3db.customer SET extrapoints = ? WHERE custID_PK = ?";
			preparedStmt1 = connect.prepareStatement(query5);
			preparedStmt1.setInt(1, extrapoints + 500);
			preparedStmt1.setInt(2, customerID);
			preparedStmt1.execute();

			Flight flight = new Flight (bookingID, customerID, flightID, null, null, null, null, null, 0, avaSeats, prices, ticket, type.BOOKING_OP);
			list = new ArrayList<Flight>();
			list.add(flight);
			return list;

		}

		else {
			Flight flight = new Flight (0, customerID, flightID, null, null, null, null, null, 0, 0, 0, ticket, type.BOOKING_OP);
			list = new ArrayList<Flight>();
			list.add(flight);
			return list;
		}





	}//end of bookingAction

	public ArrayList<Flight> viewAction(ArrayList<Flight> list, Connection connect) throws SQLException {
		int bookingID = list.get(0).getBookingID();
		int customerID = list.get(0).getCustomerID();
		double prices = 0;
		int flightID = 0;
		String airline;
		String from;
		String to;
		String dTime;
		String aTime;

		if (bookingID != 0) {
			String query ="SELECT * FROM group3db.booking_ticket WHERE bookingID_PK = ?";
			preparedStmt = connect.prepareStatement(query);
			preparedStmt.setInt(1, bookingID);
			rs = preparedStmt.executeQuery();
			if (rs.next()) {
				flightID = rs.getInt("flightID_FK");
				prices = rs.getDouble("prices");

				String query1 ="SELECT * FROM group3db.flight WHERE flightID_PK = ?";
				preparedStmt1 = connect.prepareStatement(query1);
				preparedStmt1.setInt(1, flightID);
				rs1 = preparedStmt1.executeQuery();
				if (rs1.next()) {
					airline = rs1.getString("airline_name");
					from = rs1.getString("from_airport");
					to = rs1.getString("to_airport");
					dTime = rs1.getString("departure_time");
					aTime = rs1.getString("arrival_time");
					
					Flight flight = new Flight (bookingID, customerID, flightID, airline, from, to, dTime, aTime, 0, 0 ,prices, 0, type.VIEWFLIGHT_OP);
					list = new ArrayList<Flight>();
					list.add(flight);
					return list;

				}

			}
			else {
				Flight flight = new Flight (0, customerID, 0, null, null, null, null, null, 0, 0, 0, 0, type.VIEWFLIGHT_OP);
				list = new ArrayList<Flight>();
				list.add(flight);
				return list;
			}	
		}
		
			String query ="SELECT * FROM group3db.booking_ticket WHERE customerID_FK = ?";
			preparedStmt = connect.prepareStatement(query);
			preparedStmt.setInt(1, customerID);
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				flightID = rs.getInt("flightID_FK");
				prices = rs.getDouble("prices");

				String query1 ="SELECT * FROM group3db.flight WHERE flightID_PK = ?";
				preparedStmt1 = connect.prepareStatement(query1);
				preparedStmt1.setInt(1, flightID);
				rs1 = preparedStmt1.executeQuery();
				if (rs1.next()) {
					airline = rs1.getString("airline_name");
					from = rs1.getString("from_airport");
					to = rs1.getString("to_airport");
					dTime = rs1.getString("departure_time");
					aTime = rs1.getString("arrival_time");
					
					Flight flight = new Flight (bookingID, customerID, flightID, airline, from, to, dTime, aTime, 0, 0 ,prices, 0, type.VIEWFLIGHT_OP);
					list = new ArrayList<Flight>();
					list.add(flight);

				}	
			}	
			return list;

	}//end of viewAction



	public ArrayList<Flight> cancelFlight(ArrayList<Flight> list, Connection connect) throws SQLException {
		int customerID = list.get(0).getCustomerID();
		int bookingID = list.get(0).getBookingID();
		int points;

		String query = "SELECT * FROM group3db.booking_ticket WHERE bookingID_PK = ?";
		preparedStmt = connect.prepareStatement(query);
		preparedStmt.setInt(1, bookingID);
		rs = preparedStmt.executeQuery();

		if (rs.next()) {
			String sql = "DELETE FROM group3db.booking_ticket WHERE bookingID_PK = ?";
			preparedStmt = connect.prepareStatement(sql);
			preparedStmt.setInt(1, bookingID);
			preparedStmt.execute();
			//reducing extra points if customer cancel reservation
			String query1 = "SELECT * FROM group3db.customer WHERE custID_PK = ?";
			preparedStmt = connect.prepareStatement(query1);
			preparedStmt.setInt(1, customerID);
			rs = preparedStmt.executeQuery();
			if (rs.next()) {
				points = rs.getInt("extrapoints");
				
				sql = "UPDATE group3db.customer SET extrapoints = ? WHERE custID_PK = ?";
				preparedStmt1 = connect.prepareStatement(sql);
				preparedStmt1.setDouble(1, points - 500);
				preparedStmt1.setInt(2, customerID);
				preparedStmt1.executeUpdate();
			}

			Flight flight = new Flight(1, customerID, 0, null, null, null, null, null, 0 , 0, 0, 0, type.CANCEL_OP);
			list = new ArrayList<Flight>();
			list.add(flight);
			return list;
		}
		else {
			Flight flight = new Flight(0, customerID, 0, null, null, null, null, null, 0 , 0, 0, 0, type.CANCEL_OP);
			list = new ArrayList<Flight>();
			list.add(flight);
			return list;
		}

	}//end of cancelFlight action


	public ArrayList<Flight> close(ArrayList<Flight> list, Connection connect) {
		Flight flight = new Flight(0, 0, 0, null, null, null, null, null, 0 , 0, 0, 0, type.CLOSE_OP);
		list = new ArrayList<Flight>();
		list.add(flight);
		return list;
	}

}	
