package com.example.next.firsapp;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by movile on 28/06/15.
 */
public class FirsappDebugApplication extends Application {
    public void onCreate() {
        super.onCreate();


        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
