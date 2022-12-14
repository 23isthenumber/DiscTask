package com.example.discover.controller;

import com.example.discover.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputHandlerTest {

    private final InputHandler inputHandler = new InputHandler();

    @Test
    void getInput_validInput_Successful() {
        //given
        String expectedResponse = "4444444444444444";
        injectInput(expectedResponse);
        String actualResponse;
        //when
        actualResponse = inputHandler.getInput();
        //then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getInput_invalidInput_throwInvalidInputException() {
        //given
        String input = "a tester walks into a bar to order a car";
        injectInput(input);
        //then when
        assertThrows(InvalidInputException.class, inputHandler::getInput);
    }

    private void injectInput(String expectedResponse) {
        InputStream in = new ByteArrayInputStream(expectedResponse.getBytes());
        System.setIn(in);
    }
}