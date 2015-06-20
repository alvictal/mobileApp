package com.example.next.firsapp.core;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.example.next.firsapp.activity.onListenerEpisode;
import com.example.next.firsapp.model.Episode;

/**
 * Created by aluisio on 6/19/15.
 */
public class ReadyOnlineContentClient implements LoaderManager.LoaderCallbacks<Episode> {
    protected Context context;

    private static onListenerEpisode mListener;

    public ReadyOnlineContentClient (onListenerEpisode listener, Context context_s) {
        context = context_s;
        mListener = listener;
    }

    public Loader<Episode> onCreateLoader(int id, Bundle args) {
        // init loader depending on id

        return new ReadyOnlineContent(context, args);

    }

    public void onLoadFinished(Loader loader, Episode episode) {
        mListener.onEpisodedLoaded(episode);
    }

    public void onLoaderReset(Loader loader) {
    }
}
