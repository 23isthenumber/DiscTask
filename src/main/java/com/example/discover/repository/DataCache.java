package com.example.discover.repository;

import com.example.discover.model.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataCache {

    public List<Data> readFromCSV(String fileName) {
        List<Data> data = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        System.out.println(pathToFile.toAbsolutePath());
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            br.readLine();
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Data book = createData(attributes);
                data.add(book);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return data;
    }

    private Data createData(String[] metadata) {
        final String issuerName = metadata[0];
        final int supportedPanLength = Integer.parseInt(metadata[1]);
        final int prefixLength = Integer.parseInt(metadata[2]);
        final int innRangeLow = Integer.parseInt(metadata[3]);
        final int innRangeHigh = Integer.parseInt(metadata[4]);
        final String pattern = metadata[5];
        return new Data(issuerName, supportedPanLength, prefixLength, innRangeLow, innRangeHigh, pattern);
    }
}
