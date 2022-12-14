package com.example.discover.service.impl;

import com.example.discover.exception.NoMatchingPrefixException;
import com.example.discover.exception.PatternNotKnownException;
import com.example.discover.model.Data;
import com.example.discover.service.DataService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataServiceImpl implements DataService {

    public List<Data> getMatchingSupportedPanLength(List<Data> dataList, String panNumber) {
        return dataList.stream()
                       .filter(a -> panNumber.length() == a.getSupportedPanLength())
                       .collect(Collectors.toList());
    }

    public List<Data> getDataMatchingPrefixFromMatchingSupportedPanLength(String panNumber,
                                                                          List<Data> matchingSupportedPanLength) {
        final List<Data> matchingPrefix = new ArrayList<>();
        for (Data data : matchingSupportedPanLength) {
            final int prefixLength;
            final int innRangeLow;
            final int innRangeHigh;
            final int prefix;
            prefixLength = data.getPrefixLength();
            prefix = Integer.parseInt(panNumber.substring(0, prefixLength));
            innRangeLow = data.getInnRangeLow();
            innRangeHigh = data.getInnRangeHigh();
            if ((innRangeLow <= prefix) && (prefix <= innRangeHigh)) {
                matchingPrefix.add(data);
            }
        }
        return matchingPrefix;
    }

    public void checkIfPatternIsKnown(List<Data> matchingPrefix) {
        Data data = matchingPrefix.stream().findFirst().orElseThrow(NoMatchingPrefixException::new);
        if ("Pattern not known".equals(data.getPattern())) {
            throw new PatternNotKnownException();
        }
    }

    public String handlePatterns(String panNumber, List<Data> matchingPrefix) {
        List<Character> pan = panNumber.chars()
                                       .mapToObj(c -> (char) c)
                                       .collect(Collectors.toList());
        Data data = matchingPrefix.stream()
                                  .findFirst()
                                  .orElseThrow(NoMatchingPrefixException::new);
        char[] pattern = data.getPattern()
                             .toCharArray();
        List<Character> resultArray = new ArrayList<>();
        for (char character : pattern) {
            int count = 0;
            if (character == '#' && !pan.isEmpty()) {
                character = pan.get(count);
                pan.remove(count);
                count++;
            }
            resultArray.add(character);
        }
        return resultArray.stream()
                          .map(Object::toString)
                          .collect(Collectors.joining(""));
    }
}