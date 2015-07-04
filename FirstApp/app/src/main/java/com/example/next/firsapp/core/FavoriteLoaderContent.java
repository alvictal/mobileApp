package com.example.next.firsapp.core;

import android.content.Context;
import android.os.Bundle;
import android.content.AsyncTaskLoader;
import android.util.Log;

import com.example.next.firsapp.database.dbflow.FavoriteDB;
import com.example.next.firsapp.database.manual.FavoriteDao;
import com.example.next.firsapp.model.Favorite;

/**
 * Created by movile on 04/07/15.
 */
public class FavoriteLoaderContent extends AsyncTaskLoader<Favorite> {
    protected Context mContext;
    private String mSlug;
    private String mTitle;
    private String mType;

    public FavoriteLoaderContent(Context context, Bundle args){
        super(context);

        if (args != null) {
            mSlug = args.getString("slug");
            mTitle = args.getString("title");
            mType = args.getString("type");
        }

        mContext = context;
    }

    public Favorite loadInBackground() {
        Favorite favorite;
        FavoriteDB favoriteDB = new FavoriteDB(mContext);
        //FavoriteDao favoriteDao = new FavoriteDao(mContext);

       // favorite = favoriteDao.query(mSlug);
        favorite = favoriteDB.query(mSlug);
        if (mType == "add") {
            if (favorite == null) {
                favorite = new Favorite(mSlug,mTitle);
                favoriteDB.save(favorite);
         //       favoriteDao.save(favorite);
            }
        } else if (mType == "delete") {
            if (favorite != null) {
                favoriteDB.delete(mSlug);
           //     favoriteDao.delete(mSlug);
                favorite = null;
            }
        }

        return favorite;
    }
}
