package com.brs;

import java.util.Scanner;

import com.admin.Admin;
import com.passenger.Passenger;

public class BusReservationSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Admin admin=new Admin();
        Passenger passenger=new Passenger();

        while (true) {

            System.out.println("-----------------------------------------");
            System.out.println("|              WELCOME TO               |");
            System.out.println("|         BUS MANAGEMENT SYSTEM         |");
            System.out.println("|                                       |");
            System.out.println("|             1. Admin                  |");
            System.out.println("|             2. Passenger              |");
            System.out.println("|             3. Exit                   |");
            System.out.println("|                                       |");
            System.out.println("-----------------------------------------");

            System.out.print("Enter Your Choice : ");
            int choice = sc.nextInt();

            switch (choice) {
            	case 1:
            		admin.call();
            		break;

            	case 2:
            		passenger.call();
            		break;

	            case 3:
	                System.out.println("Thank You for using Bus Management System.");
	                sc.close();
	                return;
	
	            default:
	                System.out.println("Enter valid Choice (1-3).");
            }
        }
    }
}