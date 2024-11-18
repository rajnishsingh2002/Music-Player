package com.example.musicplayer;

public class Song {

    String songName;
    String songAuthor;
    String songPath;




    Song(String name, String author, String path ){
        songName=name;
        songAuthor=author;
        songPath=path;

    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public String getSongAuthor() {
        return songAuthor;
    }

    public void setSongAuthor(String songAuthor) {
        this.songAuthor = songAuthor;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }


}
