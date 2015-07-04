package com.example.next.firsapp.core;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.example.next.firsapp.model.Favorite;
import com.example.next.firsapp.view.FavoriteListener;

/**
 * Created by movile on 04/07/15.
 */
public class FavoriteLoader implements LoaderManager.LoaderCallbacks<Favorite> {
    protected Context mContext;
    private String mSlug;
    private String mTitle;
    private String mType;
    private FavoriteListener mListener;

    public FavoriteLoader (Context context, FavoriteListener listener, String slug, String title, String type) {
        mContext = context;
        mSlug = slug;
        mTitle= title;
        mType = type;
        mListener = listener;
    }

    public Loader<Favorite> onCreateLoader(int id, Bundle args) {
        args = new Bundle();

        args.putString("slug", mSlug);
        args.putString("title", mTitle);
        args.putString("type", mType);
        return new FavoriteLoaderContent(mContext,args);
    }

    public void onLoadFinished(Loader loader, Favorite favorite) {
        mListener.displayFavorite(favorite);
    }

    public void onLoaderReset(Loader loader) {
    }
}
