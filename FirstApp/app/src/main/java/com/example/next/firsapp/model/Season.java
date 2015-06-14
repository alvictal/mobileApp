package com.example.next.firsapp.model;

import com.google.gson.annotations.SerializedName;

public class Season {

    private Long number;
    private MediaIds ids;
    private Double rating;
    private Long votes;
    @SerializedName("episode_count")
    private Long episodeCount;
    @SerializedName("aired_episodes")
    private Long airedEpisodes;
    private String overview;
    private Images images;

    public Long number() {
        return number;
    }

    public MediaIds ids() {
        return ids;
    }

    public Double rating() {
        return rating;
    }

    public Long votes() {
        return votes;
    }

    public Long episodeCount() {
        return episodeCount;
    }

    public Long airedEpisodes() {
        return airedEpisodes;
    }

    public String overview() {
        return overview;
    }

    public Images images() {
        return images;
    }

}
