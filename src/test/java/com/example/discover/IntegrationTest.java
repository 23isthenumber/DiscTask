package com.example.discover;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Test
    void main_validInput_applicationStartsAndWorksCorrectly() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        String expectedOutput = "4444 4444 4444 4444";

        mockInput();
        assertDoesNotThrow(() -> DiscovertaskApplication.main(new String[0]));
        assertTrue(outContent.toString()
                             .contains(expectedOutput));
    }

    private void mockInput() {
        String input = "4444444444444444";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}