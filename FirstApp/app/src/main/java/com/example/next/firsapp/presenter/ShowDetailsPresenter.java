package com.example.next.firsapp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.next.firsapp.R;
import com.example.next.firsapp.model.Show;
import com.example.next.firsapp.remote.service.ShowRemoteService;
import com.example.next.firsapp.view.ShowDetailsView;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by aluisio on 6/25/15.
 */
public class ShowDetailsPresenter {
    private ShowDetailsView mView;
    private RestAdapter mAdapter;
    private Context context;

    public ShowDetailsPresenter (Context contextP, ShowDetailsView mViewP) {
        context = contextP;
        mView = mViewP;
    }

    // --> Usando agora retrofit para fazer a comunicação http e pegar os dados
    public void onShowDetailsRetrofit(String show) {
        mAdapter = new RestAdapter.Builder().setEndpoint(context.getResources().getString(R.string.api_url_base)).build();
        ShowRemoteService service = mAdapter.create(ShowRemoteService.class);
        service.getEpisodeDetails(show,  new Callback<Show>() {
            @Override
            public void success (Show showC, Response response) {
                onShowLoaded(showC);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("retrofit", "Error to load http:" + error.getCause());
            }
        });
    }

    public void onShowLoaded(Show showC){
        mView.displayShow(showC);
    }
}
