package com.lambdanum.resourcePackExtractor;

public class Disc {

    private int id;
    private String minecraftId;
    private String name;
    private String soundId;
    private String url;
    private String artist;
    private boolean released;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMinecraftId() {
        return minecraftId;
    }

    public void setMinecraftId(String minecraftId) {
        this.minecraftId = minecraftId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoundId() {
        return soundId;
    }

    public void setSoundId(String soundId) {
        this.soundId = soundId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public boolean isReleased() {
        return released;
    }

    public void setReleased(boolean released) {
        this.released = released;
    }
}
