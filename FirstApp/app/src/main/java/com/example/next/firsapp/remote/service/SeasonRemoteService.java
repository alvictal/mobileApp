package com.example.next.firsapp.remote.service;

import com.example.next.firsapp.model.Season;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by movile on 27/06/15.
 */
public interface SeasonRemoteService {
    @Headers({
            "trakt-api-version: 2", //+ ApiConfiguration.getApiVersion(),
            "trakt-api-key: 63932c1e041b9d7a2fe185b95b0a534ef7263ecd012c490a7548481c39ab43fd" //+ ApiConfiguration.getApiKey()

    })

    @GET("/shows/{show}/seasons?extended=full,images")
    void getSeasonList(
            @Path("show") String show,
            Callback<List<Season>> callback);
}
