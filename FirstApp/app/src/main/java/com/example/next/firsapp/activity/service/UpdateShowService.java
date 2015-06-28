package com.example.next.firsapp.activity.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.next.firsapp.model.ShowUpdate;
import com.example.next.firsapp.presenter.UpdateShowPresenter;

/**
 * Created by movile on 28/06/15.
 */
public class UpdateShowService extends IntentService {

    public UpdateShowService(){super(UpdateShowService.class.getName());}

    protected void onHandleIntent(Intent intent){
        ShowUpdate update = new UpdateShowPresenter(this).onUpdateRetrofit();

        Intent mIntent = new Intent("com.example.next.firsapp.action.SHOW_UPDATE");
        mIntent.putExtra("UPDATE_MSG", update);
        sendBroadcast(mIntent);

    }
}
