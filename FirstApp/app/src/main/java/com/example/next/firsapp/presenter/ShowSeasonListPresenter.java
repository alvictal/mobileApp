package com.example.next.firsapp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.next.firsapp.R;
import com.example.next.firsapp.model.Episode;
import com.example.next.firsapp.model.Season;
import com.example.next.firsapp.remote.service.EpisodeListRemoteService;
import com.example.next.firsapp.remote.service.SeasonRemoteService;
import com.example.next.firsapp.view.SeasonDetailsView;
import com.example.next.firsapp.view.ShowSeasonListView;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by movile on 27/06/15.
 */
public class ShowSeasonListPresenter {
    private ShowSeasonListView mView;
    private RestAdapter mAdapter;
    private Context context;

    public ShowSeasonListPresenter (Context contextP, ShowSeasonListView mViewP) {
        context = contextP;
        mView = mViewP;
    }

    // --> Usando agora retrofit para fazer a comunicação http e pegar os dados
    public void onSeasonDetailsRetrofit(String show) {
        mAdapter = new RestAdapter.Builder().setEndpoint(context.getResources().getString(R.string.api_url_base)).build();
        SeasonRemoteService service = mAdapter.create(SeasonRemoteService.class);
        service.getSeasonList(show, new Callback<List<Season>>() {
            @Override
            public void success(List<Season> seasons, Response response) {
                mView.displayShowSeasonList(seasons);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("retrofit", "Error to load http:" + error.getCause());
            }
        });
    }
}
