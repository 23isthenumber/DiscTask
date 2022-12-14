package com.example.discover.controller;

import com.example.discover.exception.InvalidInputException;
import com.example.discover.exception.PatternNotKnownException;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class InputHandler {

    public String getInput() {
        log.info("input:");
        Scanner scanner = new Scanner(System.in);
        String panNumber = scanner.nextLine();
        if (panNumber.matches(".*[a-z].*")){
            throw new InvalidInputException();
        }
        scanner.close();
        return panNumber;
    }
}
