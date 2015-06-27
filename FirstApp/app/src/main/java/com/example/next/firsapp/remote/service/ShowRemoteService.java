package com.example.next.firsapp.remote.service;

import com.example.next.firsapp.model.Episode;
import com.example.next.firsapp.model.Show;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by aluisio on 6/25/15.
 */
public interface ShowRemoteService {
    @Headers({
            "trakt-api-version: 2", //+ ApiConfiguration.getApiVersion(),
            "trakt-api-key: 63932c1e041b9d7a2fe185b95b0a534ef7263ecd012c490a7548481c39ab43fd" //+ ApiConfiguration.getApiKey()

    })

    @GET("/shows/{show}?extended=full,images")
    void getShowDetails(
            @Path("show") String show,
            Callback<Show> callback);

    @Headers({
            "trakt-api-version: 2", //+ ApiConfiguration.getApiVersion(),
            "trakt-api-key: 63932c1e041b9d7a2fe185b95b0a534ef7263ecd012c490a7548481c39ab43fd" //+ ApiConfiguration.getApiKey()

    })

    @GET("/shows/popular?limit=50&extended=full,images")
    void getShowListDetails(
            Callback<List<Show>> callback);
}
