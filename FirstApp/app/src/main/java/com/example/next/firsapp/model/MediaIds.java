package com.example.next.firsapp.model;

import java.io.Serializable;

public class MediaIds implements Serializable {

    private Long trakt;
    private String slug;
    private Long tvdb;
    private String imdb;
    private Long tmdb;
    private Long tvrage;

    public Long trakt() {
        return trakt;
    }

    public String slug() {
        return slug;
    }

    public Long tvdb() {
        return tvdb;
    }

    public String imdb() {
        return imdb;
    }

    public Long tmdb() {
        return tmdb;
    }

    public Long tvrage() {
        return tvrage;
    }

}
