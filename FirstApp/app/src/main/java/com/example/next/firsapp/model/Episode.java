package com.example.next.firsapp.model;

import com.google.gson.annotations.SerializedName;

public class Episode {

    private Long season;
    private Long number;
    private String title;
    private MediaIds ids;
    private String overview;
    private Double rating;
    private Long votes;
    @SerializedName("first_aired")
    private String firstAired;
    @SerializedName("available_translations")
    private String[] translations;
    private Images images;

    public Long season() {
        return season;
    }

    public Long number() {
        return number;
    }

    public String title() {
        return title;
    }

    public MediaIds ids() {
        return ids;
    }

    public String overview() {
        return overview;
    }

    public Double rating() {
        return rating;
    }

    public Long votes() {
        return votes;
    }

    public String firstAired() {
        return firstAired;
    }

    public String[] translations() {
        return translations;
    }

    public Images images() {
        return images;
    }

}