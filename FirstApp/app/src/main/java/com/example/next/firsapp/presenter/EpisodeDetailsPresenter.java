package com.example.next.firsapp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.next.firsapp.R;
import com.example.next.firsapp.core.ReadyContent;
import com.example.next.firsapp.model.Episode;
import com.example.next.firsapp.remote.service.EpisodeRemoteService;
import com.example.next.firsapp.remote.service.onListenerEpisode;
import com.example.next.firsapp.view.EpisodeDetailsView;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by movile on 20/06/15.
 */
public class EpisodeDetailsPresenter implements onListenerEpisode {

    private EpisodeDetailsView mView;
    private RestAdapter mAdapter;
    private Context context;
    private onListenerEpisode mListener;

    public EpisodeDetailsPresenter(Context contextP, EpisodeDetailsView mviewP) {
        context = contextP;
        mView = mviewP;
    }

    public EpisodeDetailsPresenter(Context contextP, onListenerEpisode mListenerP) {
        context = contextP;
        mListener = mListenerP;
    }


    // --> Usando agora retrofit para fazer a comunicação http e pegar os dados
    public void onEpisodeDetailsRetrofit(String show, long season, long episode) {
        mAdapter = new RestAdapter.Builder().setEndpoint(context.getResources().getString(R.string.api_url_base)).build();
        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);
        service.getEpisodeDetails(show, season, episode, new Callback<Episode>() {
            @Override
            public void success (Episode episode, Response response) {
                onEpisodeLoaded(episode);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("retrofit", "Error to load http:" + error.getCause());
            }
        });
    }


    public void onEpisodeDetailsOffline() {
        //  -> Usado para pegar json no próprio local
        new ReadyContent(mListener).execute(context);
    }


    public void onEpisodeLoaded(Episode episode){
        mView.displayEpisode(episode);
    }
}
