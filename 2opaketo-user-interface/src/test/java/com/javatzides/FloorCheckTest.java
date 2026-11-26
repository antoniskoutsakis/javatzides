package com.javatzides;

import org.junit.Test;
import static org.junit.Assert.*;

public class FloorCheckTest {
    private final FloorCheck validator = new FloorCheck(0, 100);
    
    @Test 
    public void testValidFloors() {
        assertTrue(validator.isFloorValid(0,5));
        assertTrue(validator.isFloorValid(5,0));
    }

    @Test 
    public void testOriginBelowMinimum() {
        assertFalse(validator.isFloorValid(-1, 5));
    }

    @Test 
    public void testOriginAboveMaximum() {
        assertFalse(validator.isFloorValid(101,5));
    }

    @Test
    public void testDestinationBelowMinimum() {
        assertFalse(validator.isFloorValid(5, -1));
    }

    @Test
    public void testDestinationAboveMaximum() {
        assertFalse(validator.isFloorValid(5, 101));
    }

    @Test
    public void testSameFloor() {
        assertFalse(validator.isFloorValid(5, 5));
    }

    @Test
    public void testBoundaryFloors() {
        assertTrue(validator.isFloorValid(0, 100));
        assertTrue(validator.isFloorValid(100, 0));
    }

}
