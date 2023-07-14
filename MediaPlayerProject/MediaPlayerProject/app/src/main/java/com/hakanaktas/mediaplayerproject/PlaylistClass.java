package com.hakanaktas.mediaplayerproject;

import java.io.Serializable;
import java.util.ArrayList;

public class PlaylistClass implements Serializable {
    ArrayList<Audio> songsList;
    String playListTitle;


    public PlaylistClass(ArrayList<Audio> songsList, String playListTitle) {
        this.songsList = songsList;
        this.playListTitle = playListTitle;
    }


    public ArrayList<Audio> getSongsList() {
        return songsList;
    }

    public void setSongsList(ArrayList<Audio> songsList) {
        this.songsList = songsList;
    }

    public String getPlayListTitle() {
        return playListTitle;
    }

    public void setPlayListTitle(String playListTitle) {
        this.playListTitle = playListTitle;
    }

}
