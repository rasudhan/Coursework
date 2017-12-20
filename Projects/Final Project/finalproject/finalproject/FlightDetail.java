//*******************************************************************************
//* 																			*
//* 				CIS611 Spring 2017  Jeffrey Cheng							*		
//* 																			*	
//* 						Final Project					 					*
//* 																 			*
//* 			FlightDetail has less attributes and be extended by Flight 		*	
//*																				*
//*																				*	
//* 																 			*
//* 					Date Created: 04.30.2017 					 			*
//*						Saved in: FlightDetail.java		 	 					*
//* 																 			*
//*******************************************************************************
package finalproject;

import java.io.Serializable;
import java.util.Calendar;

public class FlightDetail implements Serializable{
	
	private int flightID;
	private double prices;
	private int availableSeats;
	private String departureTime;
	private Status type;
	
	public FlightDetail(int flightID, String departueTime, int availableSeats, double prices, Status type) {
		super();
		this.flightID = flightID;
		this.departureTime = departueTime;
		this.availableSeats = availableSeats;
		this.prices = prices;
		this.type = type;
		
	}
	
	public FlightDetail() {
		
	}

	public int getFlightID() {
		return flightID;
	}

	public void setFlightID(int flightID) {
		this.flightID = flightID;
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

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departueTime) {
		this.departureTime = departueTime;
	}

	public Status getType() {
		return type;
	}

	public void setType(Status type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "FlightDetail [flightID=" + flightID + ", prices=" + prices + ", availableSeats=" + availableSeats
				+ ", departueTime=" + departureTime + ", type=" + type + "]";
	}
	

	


}
