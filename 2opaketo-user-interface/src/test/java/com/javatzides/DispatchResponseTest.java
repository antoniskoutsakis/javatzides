package com.javatzides;

import com.google.gson.Gson;
import org.junit.Test;
import static org.junit.Assert.*;

public class DispatchResponseTest {
    private final Gson gson = new Gson();

    @Test 
    public void testGetStatus() {
        String json = "{\"status\":200}";
        DispatchResponse response = gson.fromJson(json, DispatchResponse.class);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testGetAssignedElevator() {
        String json = "{\"assignedElevator\":\"E1\"}";
        DispatchResponse response = gson.fromJson(json, DispatchResponse.class);
        assertEquals("E1",response.getAssignedElevator());
    }

    @Test
    public void testGetError() {
        String json = "{\"error\":\"Invalid request\"}";
        DispatchResponse response = gson.fromJson(json, DispatchResponse.class);
        assertEquals("Invalid request", response.getError());
    }

    @Test
    public void testIsSuccessWithStatus200() {
        String json = "{\"status\":200}";
        DispatchResponse response = gson.fromJson(json, DispatchResponse.class);
        assertTrue(response.isSuccess());
    }

    @Test
    public void testIsSuccessWithStatus400() {
        String json = "{\"status\":400}";
        DispatchResponse response =  gson.fromJson(json,DispatchResponse.class);
        assertFalse(response.isSuccess());
    }
}
