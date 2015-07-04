package com.example.next.firsapp.Application;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by movile on 04/07/15.
 */
public class FirsappApplication extends Application {
    public void onCreate(){
        super.onCreate();

        FlowManager.init(this);
    }
}
