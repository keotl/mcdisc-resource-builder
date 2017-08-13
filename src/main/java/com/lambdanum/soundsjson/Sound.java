package com.lambdanum.soundsjson;

public class Sound {

    private String name;
    private final boolean stream = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStream() {
        return stream;
    }
}
