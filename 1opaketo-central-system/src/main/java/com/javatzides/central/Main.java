package com.javatzides.central;

public class Main {

    public static void main(String[] args) {

        // Δημιουργία δύο αιτημάτων
        Request r1 = new Request(2, 7);
        Request r2 = new Request(0, 5);

        // Έλεγχος των στοιχείων τους
        System.out.println("Request 1:");
        System.out.println("ID: " + r1.getRequestId());
        System.out.println("Origin: " + r1.getOriginFloor());
        System.out.println("Destination: " + r1.getDestinationFloor());
        System.out.println("Status: " + r1.getStatus());
        System.out.println("Elevator: " + r1.getAssignedElevator());

        // Ανάθεση ανελκυστήρα
        r1.assignElevator("E1");

        System.out.println("\nAfter assignment:");
        System.out.println("Status: " + r1.getStatus());
        System.out.println("Elevator: " + r1.getAssignedElevator());

        // Ολοκλήρωση αιτήματος
        r1.setStatus("COMPLETED");

        System.out.println("\nAfter completion:");
        System.out.println("Status: " + r1.getStatus());

        // Έλεγχος δεύτερου αιτήματος
        System.out.println("\nRequest 2 ID: " + r2.getRequestId());
    }
}