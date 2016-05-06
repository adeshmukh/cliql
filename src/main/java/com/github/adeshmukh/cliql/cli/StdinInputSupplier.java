package com.github.adeshmukh.cliql.cli;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;

import com.google.common.io.InputSupplier;

public class StdinInputSupplier implements Readable {

    private static final int BUFFER_SIZE = 1024 * 1024;
    private static final StdinInputSupplier INSTANCE = new StdinInputSupplier();

    @Override
    public int read(CharBuffer charBuffer) throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        int n = System.in.read(bytes);
        charBuffer.put(bytes, 0, n);
        return n;
    }

    public static StdinInputSupplier instance() {
        return INSTANCE;
    }

}
