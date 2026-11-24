package com.javatzides;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class SimulatorTest {
    @Test
    public void testSimulator() {
        
        Simulator sim = new Simulator();

        //running a test for 10 passengers
        int numberOfPassengers = 10;
        List<Passengers> myRequest = sim.passengersRequests(10);

        //testing results
        /*check if the number of requests returned is correct */
        assertEquals(numberOfPassengers,myRequest.size());

        /*check if requests are valid*/
        /*for each passenger */
        for (Passengers p : myRequest) {
            assertTrue(p.getOriginFloor() >= 0);
            assertTrue(p.getOriginFloor() < 100); // Assuming there are 100 floors
            assertTrue(p.getDestinationFloor() >= 0);
            assertTrue(p.getDestinationFloor() < 100);

            assertNotEquals(p.getOriginFloor(), p.getDestinationFloor());
        }
    }
}
