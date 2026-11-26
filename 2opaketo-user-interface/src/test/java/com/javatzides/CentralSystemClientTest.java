package com.javatzides;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

public class CentralSystemClientTest {
    private HttpServer server;
    private CentralSystemClient client;

    private volatile String requestMethod;
    private volatile String requestPath;
    private volatile String requestBody;

    private volatile int responseStatus = 200;
    private volatile String responseJson = "{\"status\":200,\"assignedElevator\":\"E1\"}";
    
    @Before
    public void setUp() throws IOException {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1",0),0);
        server.createContext("/", exchange -> {

            requestMethod = exchange.getRequestMethod();
            requestPath = exchange.getRequestURI().getPath();

            requestBody = new String(
                    exchange.getRequestBody().readAllBytes(),
                    StandardCharsets.UTF_8);

            byte[] response = responseJson.getBytes(
                    StandardCharsets.UTF_8);

            exchange.getResponseHeaders().set(
                    "Content-Type", "application/json");

            exchange.sendResponseHeaders(
                    responseStatus, response.length);

            exchange.getResponseBody().write(response);
            exchange.close();
        });

        server.start();

        client = new CentralSystemClient("http://127.0.0.1:" + server.getAddress().getPort());
    }

    @After 
    public void tearDown() {
        server.stop(0);
    }

    @Test 
    public void testRequestMethod() throws IOException {
        client.sendRequest(new ElevatorRequest(0,5));
        assertEquals("POST", requestMethod);
    }

    @Test 
    public void testRequestPath() throws IOException {
        client.sendRequest(new ElevatorRequest(0, 5));
        assertEquals("/api/v1/central/requests", requestPath);
    }

    @Test
    public void testRequestBody() throws IOException {
        client.sendRequest(new ElevatorRequest(0, 5));
        Gson gson = new Gson();
        ElevatorRequest request =  gson.fromJson(requestBody, ElevatorRequest.class);
        assertEquals(0, request.getOriginFloor());
        assertEquals(5, request.getDestinationFloor());
    }

    @Test
    public void testSuccessfulResponse() throws IOException {
        DispatchResponse response = client.sendRequest(new ElevatorRequest(0,5));
        assertEquals(200,response.getStatus());
        assertEquals("E1",response.getAssignedElevator());
        assertTrue(response.isSuccess());
    }

    @Test
    public void testFailedResponse() throws IOException {
        responseStatus = 400;
        responseJson = "{\"status\":400,\"error\":\"Invalid request\"}";
        DispatchResponse response = client.sendRequest(new ElevatorRequest(0, 5));
        assertEquals(400, response.getStatus());
        assertEquals("Invalid request", response.getError());
        assertFalse(response.isSuccess());
    }

     @Test(expected = IOException.class)
    public void testConnectionFailure() throws IOException {
        server.stop(0);
        client.sendRequest(new ElevatorRequest(0, 5));
    }

}
