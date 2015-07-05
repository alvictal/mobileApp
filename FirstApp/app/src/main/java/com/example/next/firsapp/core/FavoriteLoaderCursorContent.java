package com.example.next.firsapp.core;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import com.example.next.firsapp.database.dbflow.FavoriteDB;
import com.example.next.firsapp.model.Favorite;

/**
 * Created by movile on 05/07/15.
 */
public class FavoriteLoaderCursorContent extends CursorLoader {
    private Context mContext;

    public FavoriteLoaderCursorContent(Context context){
        super(context);
        mContext = context;
    }


    public Cursor loadInBackground() {
        FavoriteDB favoriteDB = new FavoriteDB(mContext);
        Cursor cursor = null;
        try {
            cursor = favoriteDB.all();
        } finally {

        }
        return cursor;
    }


}
