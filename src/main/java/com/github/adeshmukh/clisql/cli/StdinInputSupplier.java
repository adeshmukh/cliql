package com.github.adeshmukh.clisql.cli;

import java.io.IOException;
import java.io.InputStreamReader;

import com.google.common.io.InputSupplier;

public class StdinInputSupplier implements InputSupplier<InputStreamReader> {

    private static final StdinInputSupplier INSTANCE = new StdinInputSupplier();

    @Override
    public InputStreamReader getInput() throws IOException {
        return new InputStreamReader(System.in);
    }

    public static StdinInputSupplier instance() {
        return INSTANCE;
    }

}
