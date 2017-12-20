//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 			flight class has necessary attribute and implements 			*	
//*						Serializable											*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: Flight.java		 	 						*
//* 																 			*
//*******************************************************************************
package finalproject;

import java.io.Serializable;
import java.util.Calendar;

public class Flight extends FlightDetail implements Serializable {
	
	private int bookingID;
	private int customerID;
	private int flightID;
	private String airlineName;
	private String fromAirport;
	private String toAirport;
	private String departureTime;
	private String arrivalTime;
	private int totalSeats;
	private int availableSeats;
	private double prices;
	private int ticket;
	private String trip;
	private Status type;
	
	

	public Flight(int bookingID, int customerID, int flightID, String airlineName, String fromAirport, String toAirport, String departueTime, String arrivalTime, 
			int totalSeats, int availableSeats, double prices, int ticket, Status type) {
		super();
		this.bookingID = bookingID;
		this.customerID = customerID;
		this.flightID = flightID;
		this.airlineName = airlineName;
		this.fromAirport = fromAirport;	
		this.toAirport = toAirport;
		this.departureTime = departueTime;
		this.arrivalTime = arrivalTime;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.prices = prices;
		this.ticket = ticket;
		//this.trip = trip;
		this.type = type;
		
	}
	public Flight() {
		this(0, 0, 0, null, null, null, null, null, 0, 0, 0, 0, null);
	}

	
	
	public int getBookingID() {
		return bookingID;
	}



	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}



	public int getCustomerID() {
		return customerID;
	}



	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}



	public String getFromAirport() {
		return fromAirport;
	}



	public void setFromAirport(String fromAirport) {
		this.fromAirport = fromAirport;
	}



	public int getFlightID() {
		return flightID;
	}



	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}



	public String getAirlineName() {
		return airlineName;
	}


	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}


	public String getFromAriport() {
		return fromAirport;
	}


	public void setFromAriport(String fromAriport) {
		this.fromAirport = fromAriport;
	}


	public String getToAirport() {
		return toAirport;
	}


	public void setToAirport(String toAirport) {
		this.toAirport = toAirport;
	}


	public String getDepartureTime() {
		return departureTime;
	}


	public void setDepartureTime(String departueTime) {
		this.departureTime = departueTime;
	}


	public String getArrivalTime() {
		return arrivalTime;
	}


	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


	public int getTotalSeats() {
		return totalSeats;
	}


	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public Status getType() {
		return type;
	}


	public void setType(Status type) {
		this.type = type;
	}



	public double getPrices() {
		return prices;
	}



	public void setPrices(double prices) {
		this.prices = prices;
	}



	public int getAvailableSeats() {
		return availableSeats;
	}



	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
		

	/*public String getTrip() {
		return trip;
	}



	public void setTrip(String trip) {
		this.trip = trip;
	}*/



	public int getTicket() {
		return ticket;
	}



	public void setTicket(int ticket) {
		this.ticket = ticket;
	}



	@Override
	public String toString() {
		return flightID  + "\t"  + airlineName + "\t" + fromAirport + "\t" + toAirport + "\t" + departureTime + "\t" + arrivalTime + "\t" + totalSeats + "\t" + availableSeats
				+ "\t" + prices + "\t";
	}



	
}
