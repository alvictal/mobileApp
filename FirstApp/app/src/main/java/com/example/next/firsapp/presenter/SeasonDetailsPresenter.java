package com.example.next.firsapp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.next.firsapp.R;
import com.example.next.firsapp.model.Season;
import com.example.next.firsapp.remote.service.SeasonRemoteService;
import com.example.next.firsapp.view.SeasonDetailsView;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by aluisio on 6/21/15.
 */
public class SeasonDetailsPresenter {
    private SeasonDetailsView mView;
    private RestAdapter mAdapter;
    private Context context;

    public SeasonDetailsPresenter (Context contextP, SeasonDetailsView mViewP) {
        context = contextP;
        mView = mViewP;
    }

    // --> Usando agora retrofit para fazer a comunicação http e pegar os dados
    public void onSeasonDetailsRetrofit(String show, long season) {
        mAdapter = new RestAdapter.Builder().setEndpoint(context.getResources().getString(R.string.api_url_base)).build();
        SeasonRemoteService service = mAdapter.create(SeasonRemoteService.class);
        service.getEpisodeDetails(show, season, new Callback<Season>() {
            @Override
            public void success (Season season, Response response) {
                onSeasonLoaded(season);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("retrofit", "Error to load http:" + error.getCause());
            }
        });
    }

    public void onSeasonLoaded(Season season){
        mView.displaySeason(season);
    }
}
