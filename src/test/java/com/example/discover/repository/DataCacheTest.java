package com.example.discover.repository;

import com.example.discover.model.Data;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataCacheTest {

    private final DataCache dataCache = new DataCache();

    @Test
    void readFromCSV_Successful() {
        //given
        String fileName = "testcsv.csv";
        List<Data> expectedResponse =
                List.of(
                        new Data(
                                "Visa (incl. VPay)", 16, 1, 4, 4, "#### #### #### ####"),
                        new Data("Visa (incl. VPay)", 13, 1, 4, 4, "Pattern not known")
                );
        List<Data> actualResponse;
        //when
        actualResponse = dataCache.readFromCSV(fileName);
        //then
        assertEquals(expectedResponse, actualResponse);
    }
}