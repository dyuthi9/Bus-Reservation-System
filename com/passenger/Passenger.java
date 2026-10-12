package com.passenger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.admin.AdminSide;

public class Passenger {
	public  void call() {
		AdminSide admin=new AdminSide();
		PassengerSide passenger=new PassengerSide();
        Scanner sc = new Scanner(System.in);

        while (true) {
        	System.out.println("-----------------------------------------");
            System.out.println("|          WELCOME PASSENGER            |");
            System.out.println("|                                       |");
            System.out.println("|          1. Register.                 |");
            System.out.println("|          2. Display Buses.            |");
            System.out.println("|          3. Search Bus.               |");
            System.out.println("|          4. Book Ticket.              |");
            System.out.println("|          5. Cancel Ticket.            |");
            System.out.println("|          6. View My Bookings.         |");
            System.out.println("|          7. Logout.                   |");
            System.out.println("|                                       |");
            System.out.println("-----------------------------------------");

            System.out.print("Enter Your Choice : ");
            int choice2 = sc.nextInt();

            switch (choice2) {
            
                case 1:
                    System.out.println("---------------Register Passenger---------------");
                    System.out.print("Enter Name : ");
                    String passengerName=sc.next();
                    System.out.print("Enter Age : ");
                    int age=sc.nextInt();
                    System.out.print("Enter Gender : ");
                    String gender=sc.next();
                    System.out.print("Enter Phone : ");
                    long phone=sc.nextLong();

                    passenger.register(passengerName,age,gender,phone);
                    break;

                case 2:
                    System.out.println("---------------Display Buses---------------");
                    admin.viewBuses();
                    break;

                case 3:
                    System.out.println("---------------Search Bus---------------");
                    System.out.print("Enter Bus Number : ");
                    String busNumber=sc.next();
                    admin.searchBus(busNumber);
                    break;

                case 4:
                    System.out.println("---------------Book Ticket---------------");
                    System.out.print("Enter the Passenger ID : ");
                    int passengerId = sc.nextInt();
                    if (passenger.passengerExists(passengerId)) {
                        System.out.print("Passenger ID verified.");
                        System.out.print("Enter Bus Number : ");
                        busNumber = sc.next();
                        System.out.print("Enter Travel Date : ");
                        String dateInput1 = sc.next();
                        SimpleDateFormat sdf1 =new SimpleDateFormat("dd-MM-yyyy");
                        try {
                            Date travelDate = sdf1.parse(dateInput1);
                            passenger.displaySeats(busNumber, travelDate);
                            System.out.print("Enter Seat Number : ");
                            int seatNumber = sc.nextInt();
                            passenger.bookTicket( passengerId,busNumber,travelDate,seatNumber);
                        } catch (ParseException e) {
                            System.out.println( "Invalid date format. Use dd-MM-yyyy");
                        }
                    } else {
                        System.out.println("Invalid Passenger ID.");
                    }
					
                    break;

                case 5:
                    System.out.println("---------------Cancel Ticket---------------");
                    System.out.println("Enter the Reservation ID : ");
                    int reservationId =sc.nextInt();
                    passenger.cancelTicket(reservationId);
                    break;

                case 6:
                    System.out.println("---------------View My Bookings---------------");
                    System.out.println("Enter the Reservation ID : ");
                    passengerId =sc.nextInt();
                    passenger.viewMyBookings(passengerId);
                    break;

                case 7:
                    System.out.println("---------------Passenger Logged Out.---------------");
                    break;

                default:
                    System.out.println("Enter valid Choice (1-7).");
            }
            if (choice2 == 7) {
                break;
            }
        }
	}
}
