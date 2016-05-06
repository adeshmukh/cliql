package com.github.adeshmukh.cliql.cli;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Data {
    private static final Logger LOG = LoggerFactory.getLogger(Data.class);

    public static final Data EMPTY = new Data();

    private List<Header> headers;
    private List<List<Object>> records;

    private Data() {
    }

    public Data(List<Header> headers, List<List<Object>> records) {
        checkNotNull(headers, "headers cannot be null");
        checkNotNull(records, "records cannot be null");
        int lineNum = 1;
        int numHeaders = headers.size();
        LOG.debug("numHeaders: {}", numHeaders);
        for (List<Object> record : records) {
            checkState(record.size() == numHeaders, "Mismatch in the number of headers [%s] and data in line [%s]", numHeaders, lineNum++);
        }

        this.headers = headers;
        this.records = records;
    }

    public static Data getEmpty() {
        return EMPTY;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public List<List<Object>> getRecords() {
        return records;
    }
}
