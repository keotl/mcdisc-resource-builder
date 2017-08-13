package com.lambdanum.soundsjson;

import java.util.List;

public class Entry {

    private String category;
    private List<Sound> sounds;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Sound> getSounds() {
        return sounds;
    }

    public void setSounds(List<Sound> sounds) {
        this.sounds = sounds;
    }
}
