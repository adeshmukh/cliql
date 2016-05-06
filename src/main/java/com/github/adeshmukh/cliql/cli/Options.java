package com.github.adeshmukh.cliql.cli;

import com.google.common.collect.Range;

import org.kohsuke.args4j.Option;

public class Options {

    @Option(name = "-s", aliases = "--sql", metaVar = "SQL"
            , required = true
            , usage = "SQL to execute")
    private String sql;

    @Option(name = "-d", aliases = "--delimiter", metaVar = "DELIM"
            , required = false
            , usage = "Delimiter string to split the line into columns. Defaults to whitespace.")
    private String delimiter;

    @Option(name = "-q", aliases = "--quoted", metaVar = "IS_QUOTED"
            , required = false
            , usage = "Whether the data in columns may be quoted, in which case quotes are stripped when they exist.")
    private boolean quoted;

    @Option(name = "-r", aliases = "--range", metaVar = "FROM:TO"
            , required = false
            , usage = "Range of lines to be processed as data, starts with 1. Only one of from or to is required. Examples: 2: , :3 , 1:3"
            , handler = RangeOptionHandler.class)
    private Range<Integer> range;

    @Option(name = "-e", aliases = "--headerOption", metaVar = "HEADER"
            , required = false
            , usage = "Valid values are: line => first line of data, letter => generated names as A, B, C... AA, AB etc."
            , handler = HeaderInferenceOptionHandler.class)
    private HeaderOption headerOption = HeaderOption.LETTER;

    public HeaderOption getHeaderOption() {
        return headerOption;
    }

    public Range<Integer> getRange() {
        return range;
    }

    public String getSql() {
        return sql;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public boolean isQuoted() {
        return quoted;
    }

    public boolean empty() {
        return sql == null;
    }

}
