package com.myplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.myplayer.adapter.SongListAdapter;
import com.myplayer.model.Song;
import com.myplayer.presenter.PlayerIntractor;
import com.myplayer.presenter.ViewUpdate;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewUpdate {

    Button fetchSong;
    private PlayerIntractor playerIntractor;
    private RecyclerView songlist_recyclerview;
    public SongListAdapter songListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songlist_recyclerview = (RecyclerView) findViewById(R.id.id_songlist_recycler_view);
        playerIntractor = new PlayerIntractor(this);


        songlist_recyclerview.setLayoutManager(new GridLayoutManager(this,2));




    }

    @Override
    protected void onResume() {
        super.onResume();
        playerIntractor.getSongList();

    }

    @Override
    public void songListUpdate(List<Song> songs) {

        Log.d("_D", "songListUpdate: " + songs.size());
        songListAdapter = new SongListAdapter(songs,MainActivity.this);
        songlist_recyclerview.setAdapter(songListAdapter);
    }
}
