import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Elevator {
    private final String elevatorId; // Αρχικοποιηση βασικων μεταβλητων//
    private int currentFloor=0; // Αρχικοποιηση βασικων μεταβλητων//
    private int targetFloor=0;
    private boolean doorsOpen=false;
    private Queue<Integer> floorQueue = new ConcurrentLinkedQueue<>(); //Ουρα στασεων

    //ΚΑΤΑΣΚΕΥΑΣΤΗΣ
    public Elevator(String elevatorID, int startFloor) {
        this.elevatorID = elevatorID;
        this.currentFloor = startFloor;
        this.targetFloor = startFloor;
        this.doorsOpen = false;
    }

// -----ΜΕΘΟΔΟΙ ΚΙΝΗΣΗΣ ΚΑΙ ΣΤΑΣΕΩΝ------
    
    //Βάζει σταση στην ουρα αν δεν υπαρχει ηδη
    public void addStop(int floor) {
        if (!floorQueue.contains(floor)) {
            floorQueue.add(floor);
        }
    }

    // Μεθοδος για να πάει ο ανελκυστήρας στον όροφο που ζητήθηκε
    public void goToFloor(int floor) {
        this.targetFloor = floor;
        if (this.currentFloor == this.targetFloor) {
            this.doorOpen = true;  
        } else {
            this.doorsOpen = false;
        }
    }
    //Μεθοδος για εναρξη εκινησησ Ανελκυστηρα
    public void moveOnestep() {
        // Αν ο ανελκυστηρασ ειναι στον σωστό όροφο, παιρνουμε την επομενη σταση απο την ουρά
        if (currentFloor == targetFloor && !floorQueue.isEmpty()) {
            targetFloor = floorQueue.poll();
            doorsOpen = false; // Κλείνουν οι πόρτες όταν ξεκινάει η κίνηση προς τον επόμενο όροφο
        }
        
        if (currentFloor < targetFloor) {
            currentFloor++; //Ανεβαινει ενα οροφο 
            doorsOpen = false;
        } else if (currentFloor > targetFloor) {
            currentFloor--; // Κατεβαινει ενα οροφο
            doorsOpen = true; //Εφτασε στον οροφο οποτε πορτεσ ανοιγουν  
        } else {
            doorsOpen = true; // Εφτασε στον οροφο οποτε πορτεσ ανοιγουν
        }
    }
     //-----getters ωστε να μπορει ο κοντινος να διαβαζει τη κατασταση οποιου στοιχειου θελει
    public String getElevatorId() {
        return elevatorId;
    }
    public int getCurrentFloor() {
        return currentFloor;
    }
    public int getTargetFloor() {
        return targetFloor;
    }
    public boolean isDoorsOpen() {
        return doorsOpen;
    }
    public Queue<Integer> getFloorQueue() {
        return floorQueue;
    }  
}
