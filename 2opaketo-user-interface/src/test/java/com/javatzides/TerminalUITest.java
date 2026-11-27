package com.javatzides;

import org.junit.Test;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class TerminalUITest {
    private TerminalUI ui;
    private Gson gson;
    private ByteArrayOutputStream output;
    private InputStream originalIn;
    private PrintStream originalOut;

    @Before
    public void setUp() {
        originalIn = System.in;
        originalOut = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output,true,StandardCharsets.UTF_8));
        FloorCheck validator = new FloorCheck(0, 100);
        CentralSystemClient client = new CentralSystemClient("http://localhost:8081");
        ui = new TerminalUI(0, validator, client);
        gson = new Gson();
    }

    @After
    public void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }


    @Test
    public void testSuccessfulResponse() {
        String json = "{\"status\":200,\"assignedElevator\":\"E1\"}";
        DispatchResponse response = gson.fromJson(json, DispatchResponse.class);
        ui.displayResult(response);
        assertTrue(output.toString(StandardCharsets.UTF_8).contains("ΠΑΡΑΚΑΛΩ ΠΗΓΑΙΝΕΤΕ ΣΤΟΝ ΑΝΕΛΚΥΣΤΗΡΑ: E1"));
    }

    @Test
    public void testFailedResponse() {
        String json = "{\"status\":400,\"error\":\"Invalid request\"}";
        DispatchResponse response = gson.fromJson(json, DispatchResponse.class);
        ui.displayResult(response);
        assertTrue(output.toString(StandardCharsets.UTF_8).contains("Απόρριψη αιτήματος: Invalid request"));
    }

    @Test
    public void testNullResponse() {
        ui.displayResult(null);
        assertTrue(output.toString(StandardCharsets.UTF_8).contains("Απόρριψη αιτήματος: Άγνωστο σφάλμα"));
    }

    @Test
    public void testMissingError() {
        String json = "{\"status\":400}";
        DispatchResponse response = gson.fromJson(json, DispatchResponse.class);
        ui.displayResult(response);
        assertTrue(output.toString(StandardCharsets.UTF_8).contains("Απόρριψη αιτήματος: Άγνωστο σφάλμα"));
    }

     @Test
    public void testInvalidInput() {
        String input = "abc\n-99\n";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ui.start();
        assertTrue(output.toString(StandardCharsets.UTF_8).contains("Παρακαλώ δώστε έγκυρο αριθμό ορόφου."));
    }

    @Test
    public void testSameFloor() {
        String input = "0\n-99\n";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ui.start();
        assertTrue(output.toString(StandardCharsets.UTF_8).contains("Σφάλμα: Μη έγκυρος όροφος προορισμού"));
    }

     @Test
    public void testOutOfRangeFloor() {
        String input = "101\n-99\n";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ui.start();
        assertTrue(output.toString(StandardCharsets.UTF_8).contains("Σφάλμα: Μη έγκυρος όροφος προορισμού"));
    }
    
     @Test(timeout = 2000)
    public void testExit() {
        String input = "-99\n";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ui.start();
        assertTrue(output.toString(StandardCharsets.UTF_8).contains("ΤΕΡΜΑΤΙΚΟ ΑΝΕΛΚΥΣΤΗΡΑ"));
    }
}
