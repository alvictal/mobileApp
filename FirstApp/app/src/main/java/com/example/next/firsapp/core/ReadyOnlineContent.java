package com.example.next.firsapp.core;


import android.content.Context;
import android.os.Bundle;
import android.content.AsyncTaskLoader;

import com.example.next.firsapp.model.Episode;
import com.example.next.firsapp.remote.FetchRemoteEpisodeDetails;

/**
 * Created by aluisio on 6/19/15.
 */
public class ReadyOnlineContent extends AsyncTaskLoader<Episode> {
    protected Context context;

    public ReadyOnlineContent(Context context_s, Bundle args){
        super(context_s);
        context = context_s;
    }

    public Episode loadInBackground() {
        FetchRemoteEpisodeDetails fetchEpisode = new FetchRemoteEpisodeDetails();
        Episode episode = fetchEpisode.get(context, "https://api-v2launch.trakt.tv/shows/silicon-valley/seasons/2/episodes/5?extended=full,images");

        return episode;
    }
}

