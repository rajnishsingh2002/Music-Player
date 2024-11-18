package com.example.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PlayMusicActivity extends AppCompatActivity {

    TextView song_name, song_artist;
    ImageView play_pause,next_song,previousSong,repeatMusic;
    boolean isPlaying = false;
    boolean isLoopSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playe_music);

        // Initialize views
        song_artist = findViewById(R.id.song_artist);
        song_name = findViewById(R.id.song_name);
        play_pause = findViewById(R.id.pauseMusic);
        next_song = findViewById(R.id.farword);
        previousSong = findViewById(R.id.backSong);
        repeatMusic = findViewById(R.id.repeatMusic);


        // Get song details from intent
        Intent intent = getIntent();
        String songName = intent.getStringExtra("SONGNAME");
        String songArtist = intent.getStringExtra("SONGARTIST");
        String songPath = intent.getStringExtra("SONGPATH");
        ArrayList<String> pathList=intent.getStringArrayListExtra("pathList");

        // Set song details
        song_name.setText(songName);
        song_artist.setText(songArtist);
        song_name.setSelected(true); // Enable marquee for song name

        // Start music service
        Intent playIntent = new Intent(PlayMusicActivity.this, MusicService.class);
        playIntent.putExtra("SONGPATH", songPath);
        playIntent.putExtra("method", "start");
        startService(playIntent);

        // for play/pause button
        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    // Pause music
                    isPlaying = false;
                    Intent playIntent = new Intent(PlayMusicActivity.this, MusicService.class);
                    playIntent.putExtra("method", "resume");
                    startService(playIntent);
                    play_pause.setImageResource(R.drawable.play_icon);

                } else {
                    // Resume music
                    isPlaying = true;
                    Intent playIntent = new Intent(PlayMusicActivity.this, MusicService.class);
                    playIntent.putExtra("method", "pause");
                    startService(playIntent);
                    play_pause.setImageResource(R.drawable.pause_icon);
                }

            }
        });

        //for playing next somg
        next_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent = new Intent(PlayMusicActivity.this, MusicService.class);
              playIntent.putStringArrayListExtra("songList",pathList );
                playIntent.putExtra("method", "next");
                startService(playIntent);
            }
        });

        //for previous song
        previousSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playMusic=new Intent(PlayMusicActivity.this, MusicService.class);
                playMusic.putStringArrayListExtra("songList",pathList );
                playMusic.putExtra("method","previous");
                startService(playMusic);
            }
        });


        //for looping same song
        repeatMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoopSet) {
                    // Pause music
                    isLoopSet = false;
                    Intent playIntent = new Intent(PlayMusicActivity.this, MusicService.class);

                    playIntent.putExtra("method", "loopOff");
                    startService(playIntent);
                    Toast.makeText(PlayMusicActivity.this, "looping off", Toast.LENGTH_SHORT).show();


                } else {
                    // Resume music
                    isLoopSet = true;
                    Intent playIntent = new Intent(PlayMusicActivity.this, MusicService.class);
                    playIntent.putExtra("method", "loopOn");
                    startService(playIntent);
                    Toast.makeText(PlayMusicActivity.this, "looping on", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
