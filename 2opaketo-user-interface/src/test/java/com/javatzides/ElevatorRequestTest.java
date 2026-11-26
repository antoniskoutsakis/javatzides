package com.javatzides;

import org.junit.Test;
import static org.junit.Assert.*;

public class ElevatorRequestTest {
    @Test
    public void testConstructorAndGetters() {
        ElevatorRequest request = new ElevatorRequest(0, 5);

        assertEquals(0,request.getOriginFloor());
        assertEquals(5,request.getDestinationFloor());
    }

    @Test
    public void testSetOriginFloor() {
        ElevatorRequest request = new ElevatorRequest(0, 5);
        request.setOriginFloor(2);
        assertEquals(2,request.getOriginFloor());
        assertEquals(5,request.getDestinationFloor());
    }

    @Test
    public void testSetDestinationFloor() {
        ElevatorRequest request = new ElevatorRequest(0, 5);
        request.setDestinationFloor(8);
        assertEquals(8,request.getDestinationFloor());
        assertEquals(0,request.getOriginFloor());
    }
}
