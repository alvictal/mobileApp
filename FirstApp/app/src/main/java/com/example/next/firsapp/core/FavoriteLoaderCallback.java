package com.example.next.firsapp.core;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.example.next.firsapp.remote.service.FavoriteCursor;

/**
 * Created by movile on 05/07/15.
 */
public class FavoriteLoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {
    private Context mContext;
    private FavoriteCursor mListener;

    public FavoriteLoaderCallback(Context context, FavoriteCursor listener){
        mContext = context;
        mListener = listener;
    }
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        return new FavoriteLoaderCursorContent(mContext);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mListener.displayFavorite(data);
    }


    public void onLoaderReset(Loader<Cursor> loader) {  }
}
