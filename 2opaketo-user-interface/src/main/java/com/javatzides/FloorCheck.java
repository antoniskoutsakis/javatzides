package com.javatzides;

public class FloorCheck {
    private final int minFloor;
    private final int maxFloor;

    public FloorCheck(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public boolean isFloorValid(int originFloor, int destinationFloor) {
        if (originFloor < minFloor || originFloor > maxFloor) return false;
        if (destinationFloor < minFloor || destinationFloor > maxFloor) return false;
        if (originFloor == destinationFloor) return false;
        return true;
    }
}