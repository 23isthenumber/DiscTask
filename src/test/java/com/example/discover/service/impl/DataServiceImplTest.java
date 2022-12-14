package com.example.discover.service.impl;

import com.example.discover.exception.NoMatchingPrefixException;
import com.example.discover.exception.PatternNotKnownException;
import com.example.discover.model.Data;
import com.example.discover.service.DataService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataServiceImplTest {

    private final DataService dataService = new DataServiceImpl();

    @Test
    void handlePatterns_supportedPanLength13_StringFormattedSuccessfully() {
        //given
        String panNumber = "5000011111111";
        List<Data> matchingPrefix = List.of(new Data("Maestro", 13, 6, 500000, 509999, "#### #### #####"));
        String expectedResponse = "5000 0111 11111";
        String actualResponse;
        //when
        actualResponse = dataService.handlePatterns(panNumber, matchingPrefix);
        //then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void handlePatterns_supportedPanLength14_StringFormattedSuccessfully() {
        //given
        String panNumber = "30111111111111";
        List<Data> matchingPrefix = List.of(new Data("Diners Club Carte Blanche", 14, 3, 300, 305, "#### ###### ####"));
        String expectedResponse = "3011 111111 1111";
        String actualResponse;
        //when
        actualResponse = dataService.handlePatterns(panNumber, matchingPrefix);
        //then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void handlePatterns_supportedPanLength16_StringFormattedSuccessfully() {
        //given
        String panNumber = "4444444444444444";
        List<Data> matchingPrefix = List.of(new Data("Visa (incl. VPay)", 16, 1, 4, 4, "#### #### #### ####"));
        String expectedResponse = "4444 4444 4444 4444";
        String actualResponse;
        //when
        actualResponse = dataService.handlePatterns(panNumber, matchingPrefix);
        //then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void handlePatterns_supportedPanLength15_StringFormattedSuccessfully() {
        //given
        String panNumber = "344444444444444";
        List<Data> matchingPrefix = List.of(new Data("American Express", 15, 2, 34, 34, "#### ###### #####"));
        String expectedResponse = "3444 444444 44444";
        String actualResponse;
        //when
        actualResponse = dataService.handlePatterns(panNumber, matchingPrefix);
        //then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void handlePatterns_supportedPanLength15forUATP_StringFormattedSuccessfully() {
        //given
        String panNumber = "111111111111111";
        List<Data> matchingPrefix = List.of(new Data("UATP", 15, 2, 34, 34, "#### ##### ######"));
        String expectedResponse = "1111 11111 111111";
        String actualResponse;
        //when
        actualResponse = dataService.handlePatterns(panNumber, matchingPrefix);
        //then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void handlePatterns_supportedPanLength19_StringFormattedSuccessfully() {
        //given
        String panNumber = "5000000000000000000";
        List<Data> matchingPrefix = List.of(new Data("Maestro", 19, 6, 500000, 509999, "#### #### #### #### ###"));
        String expectedResponse = "5000 0000 0000 0000 000";
        String actualResponse;
        //when
        actualResponse = dataService.handlePatterns(panNumber, matchingPrefix);
        //then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void handlePatterns_supportedPanLength19forChinaUnionPay_StringFormattedSuccessfully() {
        //given
        String panNumber = "6222222222222222222";
        List<Data> matchingPrefix = List.of(new Data("China UnionPay", 19, 2, 62, 62, "###### #############"));
        String expectedResponse = "622222 2222222222222";
        String actualResponse;
        //when
        actualResponse = dataService.handlePatterns(panNumber, matchingPrefix);
        //then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void handlePatterns_noSupportedPanLength_throwsNoMatchingPrefixException() {
        //given
        String panNumber = "6222222222222222222";
        List<Data> matchingPrefix = new ArrayList<>();
        //then when
        assertThrows(NoMatchingPrefixException.class, () -> dataService.handlePatterns(panNumber, matchingPrefix));
    }

    @Test
    void checkIfPatternIsKnown_patternNotSpecifiedInData_ThrowsPatternNotKnownException() {
        //given
        List<Data> matchingPrefix = List.of(new Data("Visa (incl. VPay)", 13, 1, 4, 4, "Pattern not known"));
        //then when
        assertThrows(PatternNotKnownException.class, () -> dataService.checkIfPatternIsKnown(matchingPrefix));
    }

    @Test
    void checkIfPatternIsKnown_incorrectInput_ThrowsNoMatchingPrefixException() {
        //given
        List<Data> matchingPrefix = new ArrayList<>();
        //then when
        assertThrows(NoMatchingPrefixException.class, () -> dataService.checkIfPatternIsKnown(matchingPrefix));
    }

    @Test
    void getMatchingSupportedPanLength_validDataListAndPanNumber_matchesSuccessfully() {
        //given
        String panNumber = "6222222222222222222";
        List<Data> dataList = List.of(new Data("China UnionPay", 19, 2, 62, 62, "###### #############"),
                                      new Data("This is not matching", 15, 1, 4, 4, "########## #####"));
        List<Data> expectedResponse = List.of(new Data("China UnionPay", 19, 2, 62, 62, "###### #############"));
        List<Data> actualResponse;
        //when
        actualResponse = dataService.getMatchingSupportedPanLength(dataList, panNumber);
        //then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getDataMatchingPrefixFromMatchingSupportedPanLength_matchingSupportedPanLengthListAndPanNumber_matchesSuccessfully() {
        //given
        String panNumber = "6222222222222222222";
        List<Data> dataList = List.of(new Data("China UnionPay", 19, 2, 62, 62, "###### #############"),
                                      new Data("Maestro", 19, 6, 500000, 509999, "#### #### #### #### ###"));
        List<Data> expectedResponse = List.of(new Data("China UnionPay", 19, 2, 62, 62, "###### #############"));
        List<Data> actualResponse;
        //when
        actualResponse = dataService.getDataMatchingPrefixFromMatchingSupportedPanLength(panNumber, dataList);
        //then
        assertEquals(expectedResponse, actualResponse);
    }
}
