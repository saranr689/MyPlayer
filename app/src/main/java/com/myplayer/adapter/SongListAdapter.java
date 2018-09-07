package com.myplayer.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myplayer.R;
import com.myplayer.model.Song;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.PlayerViewHolder> {
    private final Context context;
    private final List<Song> songs;

    public SongListAdapter(List<Song> songs, Context context) {
        this.context = context;
        this.songs = songs;

    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_itemlist_layout, parent, false);

        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, final int position) {
        holder.songItemNameTv.setText(songs.get(position).getSongName());
        Picasso.get().load(songs.get(position).getThumImage()).placeholder(R.drawable.ic_launcher_background).into(holder.songThumImage);
        holder.songThumImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer mediaPlayer = new MediaPlayer();
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            mediaPlayer.setDataSource(songs.get(position).getUrl());
                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mediaPlayer) {
                                    mediaPlayer.start();
                                    Toast.makeText(context, "Playing", Toast.LENGTH_SHORT).show();

                                }
                            });
                            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                                @Override
                                public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                                    Log.d("_D_SEEKBAR", "onBufferingUpdate: " + i);
                                }
                            });
                            mediaPlayer.prepareAsync();

                            Log.d("player_D", "run: "+mediaPlayer.getDuration());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                };


            }
        });

    }

    @Override
    public int getItemCount() {
        return songs.size();
//        return 12;
    }


    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        ImageView songThumImage;
        TextView songItemNameTv;

        public PlayerViewHolder(View itemView) {
            super(itemView);
            songThumImage = (ImageView) itemView.findViewById(R.id.id_item_song_thumpimage);
            songItemNameTv = (TextView) itemView.findViewById(R.id.id_item_song_name_tv);
        }
    }
}
