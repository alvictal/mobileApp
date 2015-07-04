package com.example.next.firsapp.database.manual.helper;

import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;

import com.example.next.firsapp.R;
import com.example.next.firsapp.database.manual.entity.FavoriteEntity;

/**
 * Created by movile on 04/07/15.
 */
public class ProviderUriHelper {
    private static final String SINGLE_PATH_SUFFIX = "/#";
    private static final String TYPE_SINGLE = "vnd.android.cursor.item";
    private static final String TYPE_MANY = "vnd.android.cursor.dir";

    public static final int CODE_FAVORITE_MANY = 1;
    public static final int CODE_FAVORITE_SINGLE = 2;
    private String mAuthority;

    private UriMatcher mUriMatcher;

    public ProviderUriHelper(Context context) {
        mAuthority = context.getString(R.string.database_authority);

        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(mAuthority, FavoriteEntity.FavoriteEntityFields.TABLE_NAME, CODE_FAVORITE_MANY);
        mUriMatcher.addURI(mAuthority, FavoriteEntity.FavoriteEntityFields.TABLE_NAME + SINGLE_PATH_SUFFIX, CODE_FAVORITE_SINGLE);
    }

    public int match(Uri uri) {
        return mUriMatcher.match(uri);
    }

    public boolean isSingle(Uri uri) {
        return uri.getPath().endsWith(SINGLE_PATH_SUFFIX);
    }

    public String table(Uri uri) {
        String path = uri.getPath();

        int end = path.length();
        if (isSingle(uri)) {
            end = path.indexOf(SINGLE_PATH_SUFFIX);
        }

        return path.substring(1, end);
    }

    public Uri mountManyUri(String table) {
        return Uri.parse("content://" + mAuthority + "/" + table);
    }

    public Uri mountSingleUri(String table, Long id) {
        return Uri.parse("content://" + mAuthority + "/" + table + "/" + id);
    }

    public String type(Uri uri) {
        if (isSingle(uri)) {
            return TYPE_SINGLE + "/" + table(uri);
        }
        return TYPE_MANY + "/" + table(uri);
    }

}
