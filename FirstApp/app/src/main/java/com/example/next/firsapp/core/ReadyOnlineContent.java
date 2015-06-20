package com.example.next.firsapp.core;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.content.AsyncTaskLoader;

import com.example.next.firsapp.model.Episode;
import com.example.next.firsapp.remote.FetchRemoteEpisodeDetails;

/**
 * Created by aluisio on 6/19/15.
 */
public class ReadyOnlineContent extends AsyncTaskLoader<Episode> {
    protected Context context;

    public ReadyOnlineContent(Context context, Bundle args){
        super(context);

    }

    public Episode loadInBackground() {
        FetchRemoteEpisodeDetails fetchEpisode = new FetchRemoteEpisodeDetails();
        Episode episode = fetchEpisode.get(context, "https://trakt.tv/shows/silicon-valley/seasons/2/episodes/10");

        return episode;
    }
}

