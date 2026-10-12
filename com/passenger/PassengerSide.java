package com.passenger;

import java.sql.Connection;
import java.util.Date;

import com.brs.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerSide {
	Connection connection=null;

	public void register(String passengerName, int age, String gender, long phone) {
		connection=ConnectionManager.getConnection();
		String query="insert into passengers(passengerName,age,gender,phone) values(?,?,?,?)";
		
		try {
			PreparedStatement statement= connection.prepareStatement(query);
			
			statement.setString(1,passengerName);
			statement.setInt(2, age);
			statement.setString(3, gender);
			statement.setLong(4, phone);
			int count=statement.executeUpdate();
			if(count!=0) {
				connection.commit();
				System.out.println("Record Inserted SuccessFully.");
			}else {
				connection.rollback();
				System.out.println("Error In Inserting Data.");
			}
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	    public boolean passengerExists(int passengerId) {
	        connection = ConnectionManager.getConnection();
	        String query = "SELECT passengerId FROM passengers WHERE passengerId=?";
	        try {
	            PreparedStatement statement = connection.prepareStatement(query);
	            statement.setInt(1, passengerId);
	            ResultSet result = statement.executeQuery();
	            if (result.next()) {
	                return true;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }


	    public void displaySeats(String busNumber, Date travelDate) {
	        connection = ConnectionManager.getConnection();

	        String query ="SELECT totalSeats FROM buses WHERE busNumber=?";

	        try {
	            PreparedStatement statement =connection.prepareStatement(query);
	            statement.setString(1, busNumber);
	            ResultSet result = statement.executeQuery();
	            if (result.next()) {
	                int totalSeats = result.getInt("totalSeats");
	                System.out.println("\nAvailable Seats:");
	                for (int seat = 1; seat <= totalSeats; seat++) {
	                    String seatQuery = "SELECT seatNumber FROM reservations WHERE busNumber=? AND travelDate=? AND seatNumber=? AND status='CONFIRMED'";
	                    
	                    PreparedStatement seatStatement =connection.prepareStatement(seatQuery);
	                    seatStatement.setString(1, busNumber);
	                    seatStatement.setDate(2,new java.sql.Date(travelDate.getTime()));
	                    seatStatement.setInt(3, seat);
	                    ResultSet seatResult =seatStatement.executeQuery();

	                    if (!seatResult.next()) {
	                        System.out.print(seat + " ");
	                    }
	                }
	                System.out.println();
	            } else {
	                System.out.println("Bus not found.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void bookTicket(int passengerId,String busNumber,Date travelDate,int seatNumber) {
	        connection = ConnectionManager.getConnection();

	        String checkQuery ="SELECT seatNumber FROM reservations WHERE busNumber=? AND travelDate=? AND seatNumber=? AND status='CONFIRMED'";

	        try {
	            PreparedStatement checkStatement =connection.prepareStatement(checkQuery);
	            checkStatement.setString(1, busNumber);
	            checkStatement.setDate(2,new java.sql.Date(travelDate.getTime()) );
	            checkStatement.setInt(3, seatNumber);
	            ResultSet result = checkStatement.executeQuery();
	            if (result.next()) {
	                System.out.println("Seat " + seatNumber +" is already booked.");
	                return;
	            }
	            String query ="INSERT INTO reservations (busNumber, passengerId, reservationDate, travelDate, seatNumber, status) VALUES (?, ?, CURDATE(), ?, ?, 'CONFIRMED')";

	            PreparedStatement statement =connection.prepareStatement(query);
	            statement.setString(1, busNumber);
	            statement.setInt(2, passengerId);
	            statement.setDate(3,new java.sql.Date(travelDate.getTime()));
	            statement.setInt(4, seatNumber);
	            int count = statement.executeUpdate();
	            if (count > 0) {
	            	connection.commit();
	                System.out.println("Ticket booked successfully!");
	                System.out.println("Passenger ID : " + passengerId );
	                System.out.println("Bus Number   : " + busNumber);
	                System.out.println("Seat Number  : " + seatNumber);
	            }else {
	                connection.rollback();
	                System.out.println("Ticket booking failed.");
	            }
	            connection.close();
	        } catch (SQLException e) {
	        	try {
	                connection.rollback();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	            e.printStackTrace();
	        }
	    }

		public void cancelTicket(int reservationId) {
			connection=ConnectionManager.getConnection();
			String query="UPDATE reservations SET status = 'CANCELLED' WHERE reservationId = ?";
			
			try {
				PreparedStatement statement= connection.prepareStatement(query);
				
				statement.setInt(1,reservationId);
				int count=statement.executeUpdate();
				if(count!=0) {
					connection.commit();
					System.out.println("Record Updated SuccessFully.");
				}else {
					connection.rollback();
					System.out.println("Error In Updatinging Data.");
				}
				connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public void viewMyBookings(int passengerId) {
			
			Connection connection=null;
			connection = ConnectionManager.getConnection();
			
			String query="select * from reservations where passengerId = ? ";
			
			try {
				PreparedStatement statement = connection.prepareStatement(query);

				statement.setInt(1,passengerId);
				ResultSet set= statement.executeQuery();
				System.out.println("ID\tBus\tPassenger\tReservation Date\tTravel Date\tSeat\tStatus" );
				System.out.println("--------------------------------------------------------------------------------------------------------------");
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

