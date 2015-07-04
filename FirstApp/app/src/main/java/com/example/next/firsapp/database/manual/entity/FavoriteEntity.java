package com.example.next.firsapp.database.manual.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.example.next.firsapp.database.manual.helper.DatabaseHelper;

/**
 * Created by movile on 04/07/15.
 */
public class FavoriteEntity {
    public static class FavoriteEntityFields implements BaseColumns {
        public static final String TABLE_NAME = "favorite";

        public static final String COLUMN_SLUG = "slug";
        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_SLUG_TYPE = "text";
        public static final String COLUMN_NAME_TYPE = "text";

        public static String createSql() {
            StringBuilder builder = new StringBuilder();
            builder.append("create table ").append(TABLE_NAME).append(" (");
            builder.append(DatabaseHelper.createColumnSql(_ID, "integer", "primary key", true));
            builder.append(DatabaseHelper.createColumnSql(COLUMN_SLUG, COLUMN_SLUG_TYPE, "", true));
            builder.append(DatabaseHelper.createColumnSql(COLUMN_TITLE, COLUMN_NAME_TYPE, "", false));
            builder.append(");");

            return builder.toString();
        }

        public static String dropSql() {
            return DatabaseHelper.dropSql(TABLE_NAME);
        }
    }

    private String mSlug;
    private String mTitle;

    public FavoriteEntity() {
    }

    public FavoriteEntity(String slug, String title) {
        mSlug = slug;
        mTitle = title;
    }

    public String slug() {
        return mSlug;
    }

    public String title() {
        return mTitle;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(FavoriteEntityFields.COLUMN_SLUG, mSlug);
        values.put(FavoriteEntityFields.COLUMN_TITLE, mTitle);
        return values;
    }

    public FavoriteEntity fromCursor(Cursor cursor) {
        mTitle = cursor.getString(cursor.getColumnIndexOrThrow(FavoriteEntityFields.COLUMN_TITLE));
        mSlug = cursor.getString(cursor.getColumnIndexOrThrow(FavoriteEntityFields.COLUMN_SLUG));
        return this;
    }

}
