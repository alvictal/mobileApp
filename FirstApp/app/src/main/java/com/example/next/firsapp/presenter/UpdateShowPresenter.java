package com.example.next.firsapp.presenter;

import android.content.Context;

import com.example.next.firsapp.R;
import com.example.next.firsapp.model.ShowUpdate;
import com.example.next.firsapp.remote.service.UpdateRemoteService;


import retrofit.RestAdapter;

/**
 * Created by movile on 28/06/15.
 */
public class UpdateShowPresenter {
    private Context mContext;
    private RestAdapter mAdapter;


    public UpdateShowPresenter(Context contextP) {
        mContext = contextP;
    }

    public ShowUpdate onUpdateRetrofit() {
        mAdapter = new RestAdapter.Builder().setEndpoint(mContext.getResources().getString(R.string.api_url_updates)).build();
        UpdateRemoteService service = mAdapter.create(UpdateRemoteService.class);
        return service.getLatest();
    }
}
