package com.javatzides.central;

public class Request {
    //μετρητης για τον αριθμο των αιτηματων
    private static int totalReq = 0;

    //ορισμος μεταβλητων για τα αντικειμενα Request
    private final int requestId;
    private final int originFloor;
    private final int destinationFloor;
    private String status;
    private String assignedElevator;

    //κατασκευαστης (δεχεται οροφους απο διεπαφη)
    public Request(int originFloor, int destinationFloor) {
        this.requestId = totalReq++;
        this.originFloor = originFloor;
        this.destinationFloor = destinationFloor;
        this.status = "PENDING";
        this.assignedElevator = null;
    }

    //μεθοδοι get 
    public int getRequestId() {
        return requestId;
    }

    public int getOriginFloor() {
        return originFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public String getAssignedElevator() {
        return assignedElevator;
    }

    public String getStatus() {
        return status;
    }


    //μεθοδος set για αναθεση ανελκυστηρα στο αιτημα
    public void assignElevator(String elevatorId) {
        this.assignedElevator = elevatorId;
        this.status = "ASSIGNED";
    }

    //μεθοδος  set για αλλαγη της καταστασης ανελκυστηρα πχ "Completed"
    public void setStatus(String status) {
        this.status = status;
    }
}
