package com.github.adeshmukh.clisql.cli;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.OptionHandler;
import org.kohsuke.args4j.spi.Parameters;
import org.kohsuke.args4j.spi.Setter;

import com.google.common.collect.Range;

public class RangeOptionHandler extends OptionHandler<Range<Integer>> {

    public RangeOptionHandler(CmdLineParser parser, OptionDef option, Setter<Range<Integer>> setter) {
        super(parser, option, setter);
    }

    @Override
    public int parseArguments(Parameters params) throws CmdLineException {
        return 0;
    }

    @Override
    public String getDefaultMetaVariable() {
        return null;
    }

}
