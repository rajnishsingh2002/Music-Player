package com.example.musicplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MusicAdapterRecylerView extends RecyclerView.Adapter<MusicAdapterRecylerView.MusicViewHolder> {


    List<Song> songList;
    ItemClick itemClick;


    public MusicAdapterRecylerView(ItemClick itemClick, List<Song> songList) {
        this.itemClick = itemClick;
        this.songList = songList;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item_recyler_view, parent, false);


        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {

        Song song = songList.get(position);
        holder.musicName.setText(song.getSongName());
//        holder.musicTitle.setText(song.getSongTitle());
        holder.musicAuther.setText(song.getSongAuthor());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.playMusic(song.getSongPath(),song.getSongName(),song.getSongAuthor());
            }
        });


    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder {

        TextView musicName, musicTitle, musicAuther;
        ImageView song_image;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            musicAuther = itemView.findViewById(R.id.musicAuther);
//            musicTitle = itemView.findViewById(R.id.musicTitle);
            musicName = itemView.findViewById(R.id.musicName);
//            song_image = itemView.findViewById(R.id.song_image);
        }
    }
}
