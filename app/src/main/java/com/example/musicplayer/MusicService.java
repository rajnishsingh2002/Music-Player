package com.example.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;

public class MusicService extends Service {

    MediaPlayer mediaPlayer;
    String songPath;
    ArrayList<String> allSongPath;
    int currentIdx=0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        songPath = intent.getStringExtra("SONGPATH");
        String method = intent.getStringExtra("method");
        allSongPath=intent.getStringArrayListExtra("songList");

        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }

        if (method.equals("start")) {
            startMusic();
        } else if (method.equals("pause")) {
            pauseMusic();
        } else if (method.equals("resume")) {
            resumeMusic();
        } else if (method.equals("next")) {
            nextMusic();
        }
        else if (method.equals("previous")) {
            previousSong();
        }else if (method.equals("loopOn")) {
            loopOn();
        }else if (method.equals("loopOff")) {
            loopOff();
        }


        return START_STICKY;
    }

    private void loopOff() {
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(false);
        }
    }

    private void loopOn() {
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true);
        }    }

    public void previousSong() {
        // Decrement the current song index, loop back to the last song if needed
        currentIdx = (currentIdx - 1 + allSongPath.size()) % allSongPath.size();
        songPath = allSongPath.get(currentIdx);
        startMusic(); // Start playing the previous song
    }

    private void nextMusic() {
        // Increment the current song index, loop around if needed
        currentIdx = (currentIdx + 1) % allSongPath.size();
        songPath = allSongPath.get(currentIdx);
        startMusic(); // Start playing the next song
    }

    public void startMusic() {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(songPath);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pauseMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void resumeMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
