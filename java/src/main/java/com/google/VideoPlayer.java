package com.google;

import java.util.*;
import java.util.stream.Collectors;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
//  private VideoPlaylist playlist;

  private HashMap<String, VideoPlaylist> videoPlaylist;

  private List <Video> videos = new VideoLibrary().getVideos() .stream()
          .sorted(Comparator.comparing(Video :: getTitle))
          .collect(Collectors.toList());

  private Video currentVideo = null;

  private Boolean pause = false;

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
    this.videoPlaylist = new HashMap<>();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {

    System.out.println("Here's a list of all available videos:");
    var videos = videoLibrary.getVideos();
    //videos.stream().sorted((object1, object2) -> object1.getTitle().compareTo(object2.getTitle()));
    videos.sort(Comparator.comparing(Video::getTitle));
    for(Video video: videos){
      String tags = video.getTags().toString();
      System.out.println(video.getTitle() + " (" + video.getVideoId() + ") " + tags.replaceAll(",",""));
    }
  }

  public void playVideo(String videoId) {
    //System.out.println("playVideo needs implementation");
//    ArrayList<String> arr = new ArrayList<String>();
//    if (arr.size() > 0) {
//      System.out.println("Stopping video: " + arr.get(0));
//      arr.remove(0);
//      arr.add(videoLibrary.getVideo(videoId).getTitle());
//    } else {
//      arr.add(videoLibrary.getVideo(videoId).getTitle());
//      System.out.println("Playing video: " + videoLibrary.getVideo(videoId).getTitle());
//    }
    pause = false;
    if(currentVideo == null && videos.stream().anyMatch(video -> video.getVideoId().equals(videoId))){
      currentVideo = videos.stream().filter(video -> video.getVideoId().equals(videoId)).findAny().orElse(null);
      System.out.println("Playing video: " + currentVideo.getTitle());
    } else if(currentVideo != null && !videos.stream().noneMatch(video -> video.getVideoId().equals(videoId))){
      System.out.println("Stopping video: " + currentVideo.getTitle());
      currentVideo = videos.stream().filter(video -> video.getVideoId().equals(videoId)).findAny().orElse(null);
      System.out.println("Playing video: " + currentVideo.getTitle());
    } else{
      System.out.println("Cannot play video: Video does not exist");
    }
  }

  public void stopVideo() {
    //System.out.println("stopVideo needs implementation");
//    String videoId = videoLibrary.getVideos().get(0).getVideoId();
//    Video video = videoLibrary.getVideo(videoId);
//    if (video == null) {
//      System.out.println("Cannot play video: Video does not exist");
//    }
//    else if(arr.size() > 0) {
//      System.out.println("Stopping video: " + arr.get(0));
//      arr.remove(0);
//    }else {
//      System.out.println("Cannot play video: No video is currently playing");
//    }
    if(currentVideo != null){
      System.out.println("Stopping video: " + currentVideo.getTitle());
      currentVideo = null;
    } else{
      System.out.println("Cannot stop video: No video is currently playing");
    }
  }

  public void playRandomVideo() {
    //System.out.println("playRandomVideo needs implementation");
    Random r = new Random();
    int randomVideoIndex = r.nextInt(videos.size());
    pause = false;
    if(currentVideo != null){
      stopVideo();
      playVideo(videos.get(randomVideoIndex).getVideoId());
    } else if(currentVideo == null){
      playVideo(videos.get(randomVideoIndex).getVideoId());
    }
  }

  public void pauseVideo() {
    //System.out.println("pauseVideo needs implementation");
    if(currentVideo != null){
      if(!pause){
        pause = true;
        System.out.println("Pausing video: " + currentVideo.getTitle());
      } else if (pause){
        System.out.println("Video already paused: " + currentVideo.getTitle());
      }
    }else{
      System.out.println("Cannot pause video: No video is currently playing");
    }
  }

  public void continueVideo() {
    //System.out.println("continueVideo needs implementation");
    if(currentVideo != null){
      if(pause){
        System.out.println("Continuing video: " + currentVideo.getTitle());
        pause = false;
      } else if(!pause){
        System.out.println("Cannot continue video: Video is not paused");
      }

    } else{
      System.out.println("Cannot continue video: No video is currently playing");
    }
  }

  public void showPlaying() {
    //System.out.println("showPlaying needs implementation");
    if(currentVideo != null){
      String pauseStatus;
      if(pause){
        pauseStatus = " - PAUSED";
      } else{
        pauseStatus = "";
      }
      System.out.println("Currently playing: " + currentVideo.getTitle() + " (" + currentVideo.getVideoId() + ") [" + currentVideo.getTags().stream().collect(Collectors.joining(" ")) + "]"+ pauseStatus);
    } else{
      System.out.print("No video is currently playing");
    }
  }

  public void createPlaylist(String playlistName) {
    if(this.videoPlaylist.containsKey(playlistName)){
      System.out.println("Cannot create playlist : A playlist with the same name already exists");
      return;
    }
    //System.out.println("createPlaylist needs implementation");
    VideoPlaylist playlist = new VideoPlaylist(playlistName);
    this.videoPlaylist.put(playlistName,playlist);

    System.out.println("Successfully created new playlist: "+ playlistName);
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    //System.out.println("addVideoToPlaylist needs implementation");
    Map<String, Video> videos = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    VideoPlaylist playlist = this.videoPlaylist.get(playlistName);
    for(var video:videoLibrary.getVideos()){
      videos.put(video.getVideoId(), video);
    }

    if(!this.videoPlaylist.containsKey(playlistName)){
      System.out.println("Cannot add video to "+playlistName+ ": Playlist does not exist");
      return;
    }
    else if(!videos.containsKey(videoId)){

      System.out.println("Cannot add video to "+playlistName+ ": Video does not exist");
    }
    else if(playlist.getPlaylistVideos().contains(videos.get(videoId))){
      System.out.println("Cannot add video to "+playlistName+ ": Video already added");
    }
    else{
      playlist.addVideo(videos.get(videoId));
      System.out.println("Added video to "+playlistName+ ": "+ videos.get(videoId).getTitle());
    }
  }

  public void showAllPlaylists() {
    //System.out.println("showAllPlaylists needs implementation");
    if(this.videoPlaylist.isEmpty()){
      System.out.println("No playlists exist yet");
      return;
    }
    System.out.println("Showing all playlists:");
    for(var playlist: this.videoPlaylist.keySet()){
      System.out.println(playlist);
    }
  }

  public void showPlaylist(String playlistName) {
    //System.out.println("showPlaylist needs implementation");
    var selectedPlaylist = this.videoPlaylist.get(playlistName);
    if(selectedPlaylist==null){
      System.out.println("Cannot show playlist "+playlistName+": Playlist does not exist ");
      return;
    }
    if(selectedPlaylist.getPlaylistVideos().isEmpty()){
      System.out.println("Showing playlist: "+playlistName);
      System.out.println("No videos here yet");
    }
    else{
      System.out.println("Showing playlist: "+playlistName);
      selectedPlaylist.getPlaylistVideos().sort(Comparator.comparing(Video::getTitle));
      for(var video:selectedPlaylist.getPlaylistVideos()){
        String tags = video.getTags().toString();
        System.out.println(video.getTitle()+ " ("+video.getVideoId()+") "+tags.replaceAll(",",""));
      }
    }
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    //System.out.println("removeFromPlaylist needs implementation");
    var selectedPlaylist = this.videoPlaylist.get(playlistName);
    Map<String, Video> videos = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    VideoPlaylist videoPlaylist = this.videoPlaylist.get(playlistName);
    for(var video:videoLibrary.getVideos()){
      videos.put(video.getVideoId(),video);
    }
    if(selectedPlaylist==null){
      System.out.println("Cannot remove video from "+playlistName+": Playlist does not exist");
      return;
    }
    else if(!videos.containsKey(videoId)){
      System.out.println("Cannot remove video from "+playlistName+": Video does not exist");
      return;
    }
    else if(!selectedPlaylist.getPlaylistVideos().contains(videos.get(videoId))){
      System.out.println("Cannot remove video from "+playlistName+": Video is not in playlist");
      return;
    }else{
      Video removedVideo = videos.get(videoId);
      selectedPlaylist.getPlaylistVideos().remove(removedVideo);
      System.out.println("Removed video from "+playlistName+": "+removedVideo.getTitle());
      return;
    }
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}