import org.junit.Test;
import static org.junit.Assert.*;

public class ElevatorTest {
    @Test
    public void testGotoFloorAndOpenDoors() {
        //ΦΤΙΑΧΝΟΥΜΕ ΑΝΕΛΚΥΣΤΗΡΑ ΣΤΟΝ ΟΡΟΦΟ 0
        Elevator lift = new Elevator("A", 0);
        lift.goToFloor(3); // ΤΟΥ ΛΕΜΕ ΝΑ ΠΑΕΙ ΣΤΟΝ ΟΡΟΦΟ 3
        // ΚΑΝΟΥΜΕ 3 ΒΗΜΑΤΑ ΚΙΝΗΣΗΣ ΚΑΙ ΕΛΕΓΧΟΥΜΕ ΤΟΝ ΟΡΟΦΟ ΚΑΘΕ ΦΟΡΑ
        lift.moveOneStep();
        assertEquals(1, lift.getCurrentFloor());
        assertFalse(lift.isDoorsOpen()); // οσο κινειτε πρεπει οι πορτεσ να ειναι κλειστες
        lift.moveOneStep();
        assertEquals(2, lift.getCurrentFloor());
        assertFalse(lift.isDoorsOpen());
        lift.moveOneStep();
        assertEquals(3, lift.getCurrentFloor());
        assertFalse(lift.isDoorsOpen());  
        lift.moveOneStep(); // ΤΩΡΑ ΠΡΕΠΕΙ ΝΑ ΑΝΟΙΞΟΥΝ ΟΙ ΠΟΡΤΕΣ
        assertTrue(lift.isDoorsOpen());
    }
}
