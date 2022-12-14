package com.example.discover.model;

@lombok.Data
public class Data {

    private final String issuerName;

    private final int supportedPanLength;

    private final int prefixLength;

    private final int innRangeLow;

    private final int innRangeHigh;

    private final String pattern;
}
