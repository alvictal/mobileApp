package com.example.next.firsapp.model;

import java.io.Serializable;
import java.util.Map;

public class Images implements Serializable {

    public class ImageSize {
        public static final String FULL = "full";
        public static final String MEDIUM = "medium";
        public static final String THUMB = "thumb";
    }

    private Map<String, String> poster;
    private Map<String, String> logo;
    private Map<String, String> banner;
    private Map<String, String> thumb;
    private Map<String, String> screenshot;
    private Map<String, String> fanart;
    private Map<String, String> clearart;

    public Map<String, String> poster() {
        return poster;
    }

    public Map<String, String> logo() {
        return logo;
    }

    public Map<String, String> banner() {
        return banner;
    }

    public Map<String, String> thumb() {
        return thumb;
    }

    public Map<String, String> screenshot() {
        return screenshot;
    }

    public Map<String, String> fanart() {
        return fanart;
    }

    public Map<String, String> clearart() {
        return clearart;
    }

}
