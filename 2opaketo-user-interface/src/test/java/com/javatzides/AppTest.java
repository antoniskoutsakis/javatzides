package com.javatzides;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

public class AppTest {

    private InputStream originalIn;
    private PrintStream originalOut;

    private ByteArrayOutputStream output;

    @Before
    public void setUp() {

        originalIn = System.in;
        originalOut = System.out;

        output = new ByteArrayOutputStream();

        System.setOut(new PrintStream(
                output, true, StandardCharsets.UTF_8));
    }

    @After
    public void tearDown() {

        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void testDefaultArguments() {

        String input = "-99\n";

        System.setIn(new ByteArrayInputStream(
                input.getBytes(StandardCharsets.UTF_8)));

        App.main(new String[]{});

        assertTrue(output.toString(StandardCharsets.UTF_8)
                .contains("ΟΡΟΦΟΣ 0"));
    }

    @Test
    public void testInstalledFloor() {

        String input = "-99\n";

        System.setIn(new ByteArrayInputStream(
                input.getBytes(StandardCharsets.UTF_8)));

        App.main(new String[]{"5"});

        assertTrue(output.toString(StandardCharsets.UTF_8)
                .contains("ΟΡΟΦΟΣ 5"));
    }

    @Test
    public void testMaxFloor() {

        String input = "11\n-99\n";

        System.setIn(new ByteArrayInputStream(
                input.getBytes(StandardCharsets.UTF_8)));

        App.main(new String[]{"0", "10"});

        assertTrue(output.toString(StandardCharsets.UTF_8)
                .contains("Σφάλμα: Μη έγκυρος όροφος προορισμού"));
    }

    @Test(expected = NumberFormatException.class)
    public void testInvalidInstalledFloor() {

        App.main(new String[]{"abc"});
    }

    @Test(expected = NumberFormatException.class)
    public void testInvalidMaxFloor() {

        App.main(new String[]{"0", "abc"});
    }
}
