package com.github.adeshmukh.cliql.cli;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import com.google.common.base.Function;
import com.google.common.collect.Range;
import com.google.common.io.CharStreams;

public class Main {
    private static final Options OPT = new Options();
    private static final CmdLineParser CLIP = new CmdLineParser(OPT);
    private static final Function<Map<String, Object>, Void> ROW_DISPLAY_HANDLER = new RowDisplayHandler();

    public static void main(String[] args) {
        try {
            CLIP.parseArgument(args);
            Db.newDb(data(OPT.getRange(), OPT.getHeaderOption(), OPT.getDelimiter()))
                    .query(OPT.getSql(), ROW_DISPLAY_HANDLER)
                    .close();
        } catch (CmdLineException e) {
            showUsage(e, System.err);
        }
    }

    private static Data data(Range<Integer> range, HeaderOption headerOption, String delimiter) {
        InputProcessor inputProcessor = new InputProcessor(range, delimiter, headerOption);
        try {
            return CharStreams.readLines(StdinInputSupplier.instance(), inputProcessor);
        } catch (IOException ioe) {
            System.err.printf("Error processing line [%s], underlying error [%s]%n", inputProcessor.getLastProcessedLineNum(), ioe.getMessage());
        }
        return Data.EMPTY;
    }

    private static void showUsage(CmdLineException e, PrintStream stream) {
        stream.println(e.getMessage());
        CLIP.printUsage(stream);
    }

}
