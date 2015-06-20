package com.example.next.firsapp.business;

import android.content.Context;

import com.example.next.firsapp.R;

/**
 * Created by movile on 20/06/15.
 */
public class ApiConfiguration {

    private Context context;
    public ApiConfiguration(Context contextS) {
        context = contextS;
    }
    public String getApiVersion() {
        return context.getResources().getString(R.string.api_version);
    }

    public String getApiKey() {
        return context.getResources().getString(R.string.api_key);
    }
}
