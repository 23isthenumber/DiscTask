package com.example.discover;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class IntegrationTest {

    @Test
    void test() {
        mockInput();
        DiscovertaskApplication.main(new String[0]);
    }

    private void mockInput() {
        String expectedResponse = "4444444444444444";
        InputStream in = new ByteArrayInputStream(expectedResponse.getBytes());
        System.setIn(in);
    }
}