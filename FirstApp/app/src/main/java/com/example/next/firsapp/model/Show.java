package com.example.next.firsapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Show implements Serializable {

    private String title;
    private Long year;
    private MediaIds ids;
    private String overview;
    @SerializedName("first_aired")
    private String firstAired;
    private Long runtime;
    private String network;
    private String country;
    private String trailer;
    private String status;
    private Double rating;
    private Long votes;
    private String language;
    private String[] genres;
    @SerializedName("aired_episodes")
    private Long airedEpisodes;
    private Images images;

    public String title() {
        return title;
    }

    public Long year() {
        return year;
    }

    public MediaIds ids() {
        return ids;
    }

    public String overview() {
        return overview;
    }

    public String firstAired() {
        return firstAired;
    }

    public Long runtime() {
        return runtime;
    }

    public String network() {
        return network;
    }

    public String country() {
        return country;
    }

    public String trailer() {
        return trailer;
    }

    public String status() {
        return status;
    }

    public Double rating() {
        return rating;
    }

    public Long votes() {
        return votes;
    }

    public String language() {
        return language;
    }

    public String[] genres() {
        return genres;
    }

    public Long airedEpisodes() {
        return airedEpisodes;
    }

    public Images images() {
        return images;
    }

}
