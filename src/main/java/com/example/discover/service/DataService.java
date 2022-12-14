package com.example.discover.service;

import com.example.discover.model.Data;

import java.util.List;

public interface DataService {

    void checkIfPatternIsKnown(List<Data> matchingPrefix);

    String handlePatterns(String panNumber, List<Data> matchingPrefix);

    List<Data> getMatchingSupportedPanLength(List<Data> dataList, String panNumber);

    List<Data> getDataMatchingPrefixFromMatchingSupportedPanLength(String panNumber,
                                                                   List<Data> matchingSupportedPanLength);
}
