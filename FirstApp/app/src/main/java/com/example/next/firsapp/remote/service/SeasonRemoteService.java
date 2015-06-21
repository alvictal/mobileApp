package com.example.next.firsapp.remote.service;

import com.example.next.firsapp.model.Season;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by aluisio on 6/21/15.
 */
public interface SeasonRemoteService {
    @Headers({
            "trakt-api-version: 2", //+ ApiConfiguration.getApiVersion(),
            "trakt-api-key: 63932c1e041b9d7a2fe185b95b0a534ef7263ecd012c490a7548481c39ab43fd" //+ ApiConfiguration.getApiKey()

    })

    @GET("/shows/{show}/seasons/{season}?extended=full,images")
    void getEpisodeDetails(
            @Path("show") String show,
            @Path("season") Long season,
            Callback<Season> callback);
}
