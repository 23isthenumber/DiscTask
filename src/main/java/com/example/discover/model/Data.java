package com.example.discover.model;

@lombok.Data
public class Data {

    private final String issuerName;

    private final int supportedPanLength;

    private final int prefixLength;

    private final int innRangeLow;

    private final int innRangeHigh;

    private final String pattern;

    public Data(String issuerName, int supportedPanLength, int prefixLength, int innRangeLow, int innRangeHigh,
                String pattern) {
        this.issuerName = issuerName;
        this.supportedPanLength = supportedPanLength;
        this.prefixLength = prefixLength;
        this.innRangeLow = innRangeLow;
        this.innRangeHigh = innRangeHigh;
        this.pattern = pattern;
    }
}
