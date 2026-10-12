package com.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Admin {
	public  void call() {

        Scanner sc = new Scanner(System.in);

        AdminSide admin = new AdminSide();

        while (true) {
        	 System.out.println("-----------------------------------------");
             System.out.println("|            WELCOME ADMIN              |");
             System.out.println("|                                       |");
             System.out.println("|          1. Add Bus                   |");
             System.out.println("|          2. View Buses                |");
             System.out.println("|          3. Search Bus                |");
             System.out.println("|          4. Update Bus                |");
             System.out.println("|          5. Delete Bus                |");
             System.out.println("|          6. View Reservations         |");
             System.out.println("|          7. View Passengers           |");
             System.out.println("|          8. Logout                    |");
             System.out.println("|                                       |");
             System.out.println("-----------------------------------------");

             System.out.print("Enter Your Choice : ");
             int choice1 = sc.nextInt();

             switch (choice1) {

                 case 1:
                	 System.out.println("---------------Add Bus---------------");
                     System.out.print("Enter Bus Number : ");
                     String busNumber=sc.next();
                 	 System.out.print("Enter Source : ");
                 	 String source=sc.next();
                 	 System.out.print("Enter Destination : ");
                 	 String destination=sc.next();
                 	 System.out.print("Enter Bus Type : ");
                 	 String busType=sc.next();
                 	 System.out.print("Enter Departure Date (dd-MM-yyyy): ");
                 	 String dateInput = sc.next();
                 	 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                 	 System.out.print("Enter Arrival Date (dd-MM-yyyy): ");
                 	 String dateInput1 = sc.next();
                 	 SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
					 Date departureTime;
					 Date arrivalTime;
					 try {
						departureTime = sdf.parse(dateInput);
						arrivalTime = sdf1.parse(dateInput1);
						System.out.print("Enter Total Seats : ");
                 	    int totalSeats=sc.nextInt();
                        admin.addbus(busNumber,source,destination,busType,departureTime,arrivalTime,totalSeats);
					 } catch (ParseException e) {
						e.printStackTrace();
					}
                 	
                     break;

                 case 2:
                     System.out.println("---------------View Buses---------------");
//                     System.out.print("BusNumber       Source        Destination        BusType        DepatureTime        ArrivalTime        TotalSeats");
                     admin.viewBuses();
                     break;

                 case 3:
                     System.out.println("---------------Search Bus---------------");
                     System.out.print("Enter Bus Number : ");
                     busNumber=sc.next();
                     admin.searchBus(busNumber);
                     break;

                 case 4:
                     System.out.println("---------------Update Bus---------------");
                     System.out.print("Enter Bus Number : ");
                     busNumber=sc.next();
                     System.out.print("What you want to update : ");
                     String column=sc.next();
                     if(column.equalsIgnoreCase("busNumber") || column.equalsIgnoreCase("source") || column.equalsIgnoreCase("destination") || column.equalsIgnoreCase("busType")) {
                    	 System.out.print("Enter New Value : ");
                    	 String value=sc.next();
                    	 admin.updateBus(busNumber,column,value);
                     }else if(column.equalsIgnoreCase("departureTime") || column.equalsIgnoreCase("arrivalTime")) {
                    	 System.out.print("Enter Date (dd-MM-yyyy): ");
                     	 String dateInput2 = sc.next();
                     	 SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
    					 Date value;
                    	 try {
							value = sdf2.parse(dateInput2);
							admin.updateBus(busNumber,column,value);
						} catch (ParseException e) {
							e.printStackTrace();
						}
                     }else {
                    	 System.out.print("Enter New Value : ");
                    	 int value=sc.nextInt();
                    	 admin.updateBus(busNumber,column,value);
                     }
                     
                     break;

                 case 5:
                     System.out.println("---------------Delete Bus---------------");
                     System.out.print("Enter Bus Number : ");
                	 busNumber=sc.next();
                	 admin.deleteBus(busNumber);
                     break;

                 case 6:
                     System.out.println("---------------View Reservations---------------");
                     admin.viewReservations();
                     break;

                 case 7:
                     System.out.println("---------------View Passengers---------------");
                     admin.viewPassengers();
                     break;

                 case 8:
                     System.out.println("Admin Logged Out.");
                     break;

                 default:
                     System.out.println("Enter valid Choice (1-8).");
             }

             if (choice1 == 8) {
                 break;
             }
        }
	}
}
