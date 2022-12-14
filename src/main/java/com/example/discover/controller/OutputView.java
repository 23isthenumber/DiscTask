package com.example.discover.controller;

import com.example.discover.model.Data;
import com.example.discover.repository.DataCache;
import com.example.discover.service.DataService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class OutputView {

    private final DataService dataService;
    private final String panNumber;
    private final List<Data> matchingPrefix;

    public OutputView(String panNumber, DataService dataService) {
        this.panNumber = panNumber;
        this.dataService = dataService;
        DataCache dataCache = new DataCache();
        List<Data> dataList = dataCache.readFromCSV("conf4.csv");
        List<Data> matchingSupportedPanLength = dataService.getMatchingSupportedPanLength(dataList, panNumber);
        matchingPrefix = dataService.getDataMatchingPrefixFromMatchingSupportedPanLength(panNumber,
                                                                                         matchingSupportedPanLength);
    }

    public void getOutput() {
        log.info("match");
        matchingPrefix.forEach(System.out::println);

        log.info("Output:");
        dataService.checkIfPatternIsKnown(matchingPrefix);
        String output = dataService.handlePatterns(panNumber, matchingPrefix);
        log.info(output);
    }
}
