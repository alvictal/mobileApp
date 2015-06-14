package com.example.next.firsapp.model;

public enum ShowStatus {

    RETURNING("returning series"), PRODUCTION("in production"), CANCELED("canceled"), ENDED("ended");

    private String mStatus;

    ShowStatus(String status) {
        mStatus = status;
    }

    public String status() {
        return mStatus;
    }

}
