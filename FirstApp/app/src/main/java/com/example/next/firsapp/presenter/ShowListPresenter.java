package com.example.next.firsapp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.next.firsapp.R;
import com.example.next.firsapp.model.Season;
import com.example.next.firsapp.model.Show;
import com.example.next.firsapp.remote.service.SeasonRemoteService;
import com.example.next.firsapp.remote.service.ShowRemoteService;
import com.example.next.firsapp.view.ShowListView;
import com.example.next.firsapp.view.ShowSeasonListView;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by movile on 27/06/15.
 */
public class ShowListPresenter {
    private ShowListView mView;
    private RestAdapter mAdapter;
    private Context context;

    public ShowListPresenter (Context contextP, ShowListView mViewP) {
        context = contextP;
        mView = mViewP;
    }

    // --> Usando agora retrofit para fazer a comunicação http e pegar os dados
    public void onShowListRetrofit() {
        mAdapter = new RestAdapter.Builder().setEndpoint(context.getResources().getString(R.string.api_url_base)).build();
        ShowRemoteService service = mAdapter.create(ShowRemoteService.class);
        service.getShowListDetails( new Callback<List<Show>>() {
            @Override
            public void success(List<Show> shows, Response response) {
                mView.displayShowList(shows);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("retrofit", "Error to load http:" + error.getCause());
            }
        });
    }
}
