package com.example.musicplayer;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClick {

    RecyclerView recylerView;
    List<Song> songList;
    MediaPlayer mediaPlayer;
    ArrayList<String> pathList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_list);

        recylerView = findViewById(R.id.recylerView);
        songList = new ArrayList<>();
        mediaPlayer = new MediaPlayer();
        pathList=new ArrayList<>();

        MusicAdapterRecylerView adapter = new MusicAdapterRecylerView(this, songList);
        recylerView.setLayoutManager(new LinearLayoutManager(this));
        recylerView.setAdapter(adapter);

        mediaPlayer = new MediaPlayer();

        ContentResolver contentResolver = getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor = contentResolver.query(uri, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String songPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                String songName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                String songAuthor = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                String songDuration = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                long albumId = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));


                songList.add(new Song(songName,  songAuthor, songPath));
                pathList.add(songPath);

                Log.i("mainActivity", "song- " + albumId);
            }
            cursor.close();
        }

    }

    @Override
    public void playMusic(String songPath,String songName,String songArtist) {

        Intent intent = new Intent(this, PlayMusicActivity.class);
        intent.putExtra("SONGPATH",songPath);
        intent.putExtra("SONGNAME",songName);
        intent.putExtra("SONGARTIST",songArtist);
        intent.putStringArrayListExtra("pathList",pathList);
        Log.d("dataM","name: "+songName+" artis: "+songArtist+" paths: "+songPath);
        startActivity(intent);

       /* if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        try {
            mediaPlayer.setDataSource(songPath);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/

    }

}