package com.example.next.firsapp.core;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import com.example.next.firsapp.model.Episode;

/**
 * Created by aluisio on 6/19/15.
 */
public class ReadyOnlineContentClient implements LoaderManager.LoaderCallbacks<Episode> {
    Activity context;

    public Loader<Episode> onCreateLoader(int id, Bundle args) {
        // init loader depending on id
        return new ReadyOnlineContent(context, args);
    }

    public void onLoadFinished(Loader loader, Episode episode) {

    }

    public void onLoaderReset(Loader loader) {
    }
}
