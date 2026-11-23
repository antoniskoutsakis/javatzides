package com.javatzides.central;

public class ElevatorInfo {

    // Μεταβλητές ανελκυστήρα
    private final String elevatorId;
    private int currentFloor;
    private String status;
    private Integer activeRequestId;

    // Κατασκευαστής
    public ElevatorInfo(String elevatorId, int currentFloor) {

        this.elevatorId = elevatorId;
        this.currentFloor = currentFloor;
        this.status = "AVAILABLE";
        this.activeRequestId = null;
    }

    // Μέθοδοι get
    public String getElevatorId() {
        return elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public String getStatus() {
        return status;
    }

    public Integer getActiveRequestId() {
        return activeRequestId;
    }

    // Έλεγχος διαθεσιμότητας
    public boolean isAvailable() {
        return status.equals("AVAILABLE");
    }

    // Ενημέρωση τρέχοντος ορόφου
    public void updateCurrentFloor(int floor) {
        this.currentFloor = floor;
    }

    // Ανάθεση αιτήματος στον ανελκυστήρα
    public boolean assignRequest(int requestId) {

        if (!isAvailable() || requestId < 0) {
            return false;
        }

        this.activeRequestId = requestId;
        this.status = "BUSY";

        return true;
    }

    // Ολοκλήρωση εξυπηρέτησης αιτήματος
    public boolean finishRequest(int requestId) {

        if (isAvailable() || activeRequestId == null
                || activeRequestId != requestId) {
            return false;
        }

        this.activeRequestId = null;
        this.status = "AVAILABLE";

        return true;
    }
}

