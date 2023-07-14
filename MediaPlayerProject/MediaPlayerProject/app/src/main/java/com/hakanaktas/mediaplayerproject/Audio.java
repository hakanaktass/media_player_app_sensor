package com.hakanaktas.mediaplayerproject;

import java.io.Serializable;

public class Audio implements Serializable {
    String srcPath;
    String musicTitle;
    String duration;
    int imagePath;
    String artistTitle;

    public Audio(String srcPath, String musicTitle, String duration, int imagePath, String artistTitle) {
        this.srcPath = srcPath;
        this.musicTitle = musicTitle;
        this.duration = duration;
        this.imagePath = imagePath;
        this.artistTitle = artistTitle;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

    public String getArtistTitle() {
        return artistTitle;
    }

    public void setArtistTitle(String artistTitle) {
        this.artistTitle = artistTitle;
    }
}
