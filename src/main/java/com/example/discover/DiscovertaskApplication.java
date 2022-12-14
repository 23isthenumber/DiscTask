package com.example.discover;

import com.example.discover.controller.InputHandler;
import com.example.discover.controller.OutputView;
import com.example.discover.service.impl.DataServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiscovertaskApplication {

    public static void main(String[] args) {
        final String panNumber;
        final OutputView outputView;
        final InputHandler inputHandler = new InputHandler();

        panNumber = inputHandler.getInput();
        outputView = new OutputView(panNumber, new DataServiceImpl());
        outputView.getOutput();
    }
}