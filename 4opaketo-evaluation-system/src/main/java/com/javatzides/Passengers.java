package com.javatzides;

public class Passengers {

    private int passengerID;
    private int originFloor;
    private int destinationFloor;
    
    //Constructor
    public Passengers(int passengerID, int originFloor, int destinationFloor) {
        this.passengerID = passengerID;
        this.originFloor = originFloor;
        this.destinationFloor = destinationFloor;
    }
     
    //getMethods
    public int getPassengerID() {
        return passengerID;
    }
    public int getOriginFloor() {
        return originFloor;
    }
    public int getDestinationFloor() {
        return destinationFloor;
    }
}
