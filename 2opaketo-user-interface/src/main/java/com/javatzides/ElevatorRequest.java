package com.javatzides;

public class ElevatorRequest {
    private int originFloor;
    private int destinationFloor;


    public ElevatorRequest(int originFloor, int destinationFloor) {
        this.originFloor = originFloor;
        this.destinationFloor = destinationFloor;
    }

    public int getOriginFloor() {
        return originFloor;
    }

    public void setOriginFloor(int originFloor) {
        this.originFloor = originFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }
}
}
    
