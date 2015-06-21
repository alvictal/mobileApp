package com.example.next.firsapp.core;

import android.content.Context;
import android.os.AsyncTask;

import com.example.next.firsapp.remote.service.onListenerEpisode;
import com.example.next.firsapp.business.FetchLocalEpisodeDetails;
import com.example.next.firsapp.model.Episode;

/**
 * Created by movile on 14/06/15.
 */
public class ReadyContent extends AsyncTask<Context, Integer, Episode> {

    private static onListenerEpisode mListener;

    public ReadyContent (onListenerEpisode listener) {
        mListener = listener;
    }

    protected Episode doInBackground(Context... contexts) {
        FetchLocalEpisodeDetails fetchEpisode = new FetchLocalEpisodeDetails();
        Episode episode = fetchEpisode.get(contexts[0]);
        return episode;
    }

    protected void onPostExecute(Episode episode) {
        mListener.onEpisodeLoaded(episode);
    }
}