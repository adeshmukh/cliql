package com.github.adeshmukh.clisql.cli;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.transform;
import static java.util.Arrays.asList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.base.Joiner;

public final class Headers {
    private static final Logger LOG = LoggerFactory.getLogger(Headers.class);

    private static final List<String> A2Z = asList("A,B,C,D,E,F,G,HI,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(","));
    private static final Function<Header, String> COL_DEF_FN = new Function<Header, String>() {
        @Override
        public String apply(Header input) {
            return input.getName() + " " + input.getSqlTypeDef();
        }
    };
    private static final Function<? super Header, ? extends Object> COL_NAME_FN =
            new Function<Header, String>() {
                @Override
                public String apply(Header input) {
                    return input.getName();
                }
            };

    private static final Joiner COMMA = Joiner.on(',');

    private Headers() {
    }

    public static List<String> letters(int length) {
        checkArgument(length <= A2Z.size(), "length greater than %s is not supported", length);
        return A2Z.subList(0, length);
    }

    public static String tableDdl(List<Header> headers) {
        if (headers == null || headers.isEmpty()) {
            return null;
        }

        StringBuilder sb = new StringBuilder("create table tab (");
        sb.append(COMMA.join(transform(headers, COL_DEF_FN)));
        sb.append(")");
        return sb.toString();
    }

    public static String insertSql(List<Header> headers) {
        int numCols = headers.size();
        StringBuilder csvValues = new StringBuilder();
        for (int i = 0; i < numCols; i++) {
            if (i != 0) {
                csvValues.append(',');
            }
            csvValues.append('?');
        }

        return "insert into tab ("
                + COMMA.join(transform(headers, COL_NAME_FN))
                + ") values ("
                + csvValues.toString()
                + ")";
    }
}
