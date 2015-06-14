package com.example.next.firsapp.model.converter;

import com.google.gson.Gson;
import com.example.next.firsapp.model.Episode;
import com.example.next.firsapp.model.Season;
import com.example.next.firsapp.model.Show;

import java.io.Reader;

public class ModelConverter {

    private Gson mGson;

    public ModelConverter() {
        mGson = new Gson();
    }

    public Episode toEpisode(Reader reader) {
        return mGson.fromJson(reader, Episode.class);
    }

    public Show toShow(Reader reader) {
        return null;
    }

    public Season toSeason(Reader reader) {
        return null;
    }

}
