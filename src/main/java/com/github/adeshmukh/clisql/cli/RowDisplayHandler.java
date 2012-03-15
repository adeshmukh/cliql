package com.github.adeshmukh.clisql.cli;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import com.google.common.base.Function;

public class RowDisplayHandler implements Function<Map<String, Object>, Void> {

    public RowDisplayHandler() {

    }

    @Override
    public Void apply(Map<String, Object> row) {
        for (Map.Entry<String, Object> rowEntry : row.entrySet()) {
            System.out.printf("%s\t", rowEntry.getValue());
        }
        System.out.println();
        return null;
    }

    private static String padAround(String s, int minLength, char padchar) {
        checkNotNull(s);
        if (s.length() >= minLength) {
            return s;
        }
        int len = s.length();
        int rpad = (minLength - len) / 2;
        int lpad = (minLength - len - rpad);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lpad; i++) {
            sb.append(padchar);
        }
        sb.append(s);
        for (int i = 0; i < rpad; i++) {
            sb.append(padchar);
        }
        return sb.toString();
    }

}
