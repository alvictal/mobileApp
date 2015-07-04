package com.example.next.firsapp.database.manual.helper;

import java.text.MessageFormat;

/**
 * Created by movile on 04/07/15.
 */
public class DatabaseHelper {
    public static final String CREATE_COLUMN_SQL_BASE = "{0} {1} {2}";
    public static final String DROP_TABLE_SQL_BASE = "drop table if exists {0};";

    public static String createColumnSql(String columnName, String columnType, String columnExtra, boolean hasNext) {
        String sql = MessageFormat.format(CREATE_COLUMN_SQL_BASE, columnName, columnType, columnExtra);
        if (hasNext) {
            sql += ",";
        }
        return sql;
    }

    public static String dropSql(String tableName) {
        return MessageFormat.format(DROP_TABLE_SQL_BASE, tableName);
    }

}
