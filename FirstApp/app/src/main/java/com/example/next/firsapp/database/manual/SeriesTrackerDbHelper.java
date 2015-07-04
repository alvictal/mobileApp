package com.example.next.firsapp.database.manual;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.next.firsapp.database.manual.entity.FavoriteEntity;

/**
 * Created by movile on 04/07/15.
 */
public class SeriesTrackerDbHelper extends SQLiteOpenHelper {

    public SeriesTrackerDbHelper(Context context) {
        super(context, DatabaseConfiguration.NAME, null, DatabaseConfiguration.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(FavoriteEntity.FavoriteEntityFields.createSql());
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(FavoriteEntity.FavoriteEntityFields.dropSql());
        onCreate(database);
    }


}
