package com.example.next.firsapp.model;

import java.io.Serializable;

public class ShowUpdate implements Serializable {

    private String date;
    private String show;
    private String title;
    private String message;

    public String date() {
        return date;
    }

    public String show() {
        return show;
    }

    public String title() {
        return title;
    }

    public String message() {
        return message;
    }

}
