package com.myplayer.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song {

    @SerializedName("songName")
    @Expose
    private String songName;
    @SerializedName("thumImage")
    @Expose
    private String thumImage;
    @SerializedName("url")
    @Expose
    private String url;

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getThumImage() {
        return thumImage;
    }

    public void setThumImage(String thumImage) {
        this.thumImage = thumImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}