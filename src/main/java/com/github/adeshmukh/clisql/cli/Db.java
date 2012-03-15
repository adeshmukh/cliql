package com.github.adeshmukh.clisql.cli;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.base.Joiner;

public class Db {

    private static final Logger LOG = LoggerFactory.getLogger(Db.class);

    private static final Db EMPTY_DB = new Db() {

        @Override
        public Db query(String sql, Function<Map<String, Object>, Void> handler) {
            return this;
        }

        @Override
        public void close() {
        }
    };

    private Handle HANDLE = new DBI("jdbc:hsqldb:file:/Users/amol/developer/tmp/testdb;shutdown=true", "SA", "").open();

    public static Db newDb(Data data) {
        checkArgument(data != null, "data cannot be null");
        return data == Data.EMPTY ? EMPTY_DB : new Db(data);
    }

    private Db() {
    }

    private Db(Data data) {
        ddl(data.getHeaders());
        insert(data.getHeaders(), data.getRecords());
    }

    public Db query(String sql, Function<Map<String, Object>, Void> rowHandler) {
        for (Map<String, Object> row : HANDLE.createQuery(sql).list()) {
            rowHandler.apply(row);
        }
        return this;
    }

    public void close() {
        HANDLE.close();
    }

    private void ddl(List<Header> headers) {
        String tableDdl = Headers.tableDdl(headers);
        LOG.debug(tableDdl);
        HANDLE.execute(tableDdl);
    }

    private void insert(List<Header> headers, List<List<Object>> records) {
        String insertSql = Headers.insertSql(headers);
        LOG.debug(insertSql);
        for (List<Object> record : records) {
            LOG.debug("Inserting: " + Joiner.on(",").join(record));
            HANDLE.execute(insertSql, record.toArray());
        }
    }
}
