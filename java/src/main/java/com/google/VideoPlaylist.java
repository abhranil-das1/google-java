package com.google;

import java.util.ArrayList;
import java.util.HashMap;

/** A class used to represent a Playlist */
class VideoPlaylist {
    private String playlistName;
    private ArrayList<Video> playlistVideos;
    private HashMap<String, VideoPlaylist> videoPlayList;

    VideoPlaylist(String playlistName){
        this.playlistName=playlistName;
        this.playlistVideos=new ArrayList<>();
        this.videoPlayList = new HashMap<>();
        this.videoPlayList.put(playlistName,this);
}

    public HashMap<String, VideoPlaylist> getVideoPlayList() {
        return videoPlayList;
    }

    public void setVideoPlayList(HashMap<String, VideoPlaylist> videoPlayList) {
        this.videoPlayList = videoPlayList;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public ArrayList<Video> getPlaylistVideos() {
        return playlistVideos;
    }

    public void setPlaylistVideos(ArrayList<Video> playlistVideos) {
        this.playlistVideos = playlistVideos;
    }

    public void addVideo(Video video){
        this.playlistVideos.add(video);
    }
}
