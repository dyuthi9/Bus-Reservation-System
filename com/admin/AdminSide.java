package com.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.brs.ConnectionManager;

public class AdminSide {
	Connection connection=null;

	public void addbus(String busNumber, String source, String destination,String busType, Date departureTime,
			Date arrivalTime, int totalSeats) {
		connection=ConnectionManager.getConnection();
		String query="insert into buses values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			
			statement.setString(1, busNumber);
			statement.setString(2, source);
			statement.setString(3, destination);
			statement.setString(4, busType);
			statement.setDate(5, new java.sql.Date(departureTime.getTime()));
			statement.setDate(6, new java.sql.Date(arrivalTime.getTime()));
			statement.setInt(7, totalSeats);

			int count =statement.executeUpdate();
			
			if(count!=0) {
				connection.commit();
				System.out.println("Record inserted Successfully.");
			}else {
				connection.rollback();
				System.out.println("Error in Updating the Record.");
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
	

		}
	}

	public void viewBuses() {
		Connection connection=null;
		connection = ConnectionManager.getConnection();
		
		String query="select * from buses";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet set= statement.executeQuery();
            System.out.println("BusNumber       Source        Destination        BusType        DepartureTime        ArrivalTime        TotalSeats");
			while(set.next()) {
				System.out.print(set.getString(1)+"        ");
				System.out.print(set.getString(2)+"        ");
				System.out.print(set.getString(3)+"        ");
				System.out.print(set.getString(4)+"        ");
				System.out.print(set.getDate(5)+"          ");
				System.out.print(set.getDate(6)+"          ");
				System.out.print(set.getInt(7)+"           ");
				System.out.println();
			}
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	public void searchBus(String busNumber) {
		Connection connection =null;
		connection=ConnectionManager.getConnection();
		String query="select * from buses where busnumber=?";
		try {
			PreparedStatement st=connection.prepareStatement(query);
			st.setString(1,busNumber);
			ResultSet set=st.executeQuery();
			if(set.next()) {
                System.out.println("BusNumber       Source        Destination        BusType        DepartureTime        ArrivalTime        TotalSeats");
				System.out.print(set.getString(1)+"        ");
				System.out.print(set.getString(2)+"        ");
				System.out.print(set.getString(3)+"        ");
				System.out.print(set.getString(4)+"        ");
				System.out.print(set.getDate(5)+"          ");
				System.out.print(set.getDate(6)+"          ");
				System.out.print(set.getInt(7)+"           ");
				System.out.println();
			}else {
				System.out.println("There is No Bus with that Number.");
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public void updateBus(String busNumber, String column, String value) {
		connection=ConnectionManager.getConnection();
		String query="update buses set "+column+"= ? where busNumber=?";
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			
			statement.setString(1, value);
			statement.setString(2, busNumber);
			
			int count =statement.executeUpdate();
			
			if(count!=0) {
				connection.commit();
				System.out.println("Record update Successfully.");
			}else {
				connection.rollback();
				System.out.println("Error in Updating the Record.");
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
	

		}
	}
	public void updateBus(String busNumber, String column, Date value) {
		connection=ConnectionManager.getConnection();
		String query="update buses set "+column+"= ? where busNumber=?";
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			
			statement.setDate(1,new java.sql.Date(value.getTime()));
			statement.setString(2, busNumber);
			
			int count =statement.executeUpdate();
			
			if(count!=0) {
				connection.commit();
				System.out.println("Record update Successfully.");
			}else {
				connection.rollback();
				System.out.println("Error in Updating the Record.");
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
	

		}
	}
	public void updateBus(String busNumber, String column, int value) {
		connection=ConnectionManager.getConnection();
		String query="update buses set "+column+"= ? where busNumber=?";
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			
			statement.setInt(1, value);
			statement.setString(2, busNumber);
			
			int count =statement.executeUpdate();
			
			if(count!=0) {
				connection.commit();
				System.out.println("Record update Successfully.");
			}else {
				connection.rollback();
				System.out.println("Error in Updating the Record.");
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
	

		}
	}

	public void deleteBus(String busNumber) {
		connection=ConnectionManager.getConnection();
		String query="delete from buses where busNumber=?";
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			
			statement.setString(1, busNumber);

			int count =statement.executeUpdate();
			
			if(count!=0) {
				connection.commit();
				System.out.println("Record deleted Successfully.");
			}else {
				connection.rollback();
				System.out.println("Error in deleting the Record.");
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
	

		}
		
	}

	public void viewPassengers() {
		Connection connection=null;
		connection = ConnectionManager.getConnection();
		
		String query="select * from passengers";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet set = statement.executeQuery();

			System.out.printf("%-15s %-20s %-8s %-10s %-15s%n","PassengerID", "PassengerName", "Age", "Gender", "PhoneNumber");
			System.out.println("--------------------------------------------------------------------------");

			while (set.next()) {
			    System.out.printf("%-15s %-20s %-8d %-10s %-15d%n",
			            set.getString(1),
			            set.getString(2),
			            set.getInt(3),
			            set.getString(4),
			            set.getLong(5));
			}
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	public void viewReservations() {
		Connection connection=null;
		connection = ConnectionManager.getConnection();
		
		String query="select * from reservations";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet set= statement.executeQuery();
			System.out.println("ID\tBus\tPassenger\tReservation Date\tTravel Date\tSeat\tStatus" );
			System.out.println("---------------------------------------------------------------");
			while (set.next()) {System.out.println(set.getInt("reservationId") + "\t" +
				                set.getString("busNumber") + "\t" +
				                set.getInt("passengerId") + "\t\t" +
				                set.getDate("reservationDate") + "\t" +
				                set.getDate("travelDate") + "\t" +
				                set.getInt("seatNumber") + "\t" +
				                set.getString("status")
				            );
				}
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
