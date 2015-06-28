package com.example.next.firsapp.remote.service;

import com.example.next.firsapp.model.ShowUpdate;

import retrofit.http.GET;

/**
 * Created by movile on 28/06/15.
 */
public interface UpdateRemoteService {

    @GET("/latestUpdate.json")
    ShowUpdate getLatest();
}
