package com.github.adeshmukh.clisql.cli;

import org.hsqldb.types.Types;

public class Header {

    private String name;
    private int sqlType;
    private String sqlTypeDef;

    public Header(String name, int sqlType, int size) {
        this.name = name;
        this.sqlType = sqlType;
        switch (sqlType) {
        case Types.INTEGER:
            this.sqlTypeDef = "int";
        case Types.BOOLEAN:
            this.sqlTypeDef = "boolean";
        case Types.TIMESTAMP:
            this.sqlTypeDef = "timestamp";
        default: // default to varchar
        case Types.VARCHAR:
            this.sqlTypeDef = "varchar(" + size + ")";
        }
    }

    public String getName() {
        return name;
    }

    public int getSqlType() {
        return sqlType;
    }

    public String getSqlTypeDef() {
        return sqlTypeDef;
    }
}
