package com.hakanaktas.mediaplayerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hakanaktas.mediaplayerproject.databinding.ActivityMediaGalleryBinding;
import com.hakanaktas.mediaplayerproject.databinding.ActivityPlayListsBinding;

import java.util.ArrayList;

public class PlayLists extends AppCompatActivity {
    public ActivityPlayListsBinding binding;
    ArrayList<Audio> songsList=new ArrayList<>();
    Audio audio ;
    MediaPlayer music;
    Button createPlaylistButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayListsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        music = MediaPlayer.create(PlayLists.this,R.raw.blindidlights);
        audio = new Audio(Integer.toString(R.raw.blindidlights),"Blindid Lights",Integer.toString(music.getDuration()),R.drawable.weekndalbum,"The Weeknd" );
        songsList.add(audio);

        music = MediaPlayer.create(PlayLists.this,R.raw.daylight);
        audio = new Audio(Integer.toString(R.raw.daylight),"Daylight", Integer.toString(music.getDuration()),R.drawable.daylightalbum,"Joji" );
        songsList.add(audio);

        music = MediaPlayer.create(PlayLists.this,R.raw.song2);
        audio = new Audio(Integer.toString(R.raw.song2),"Song 2", Integer.toString(music.getDuration()),R.drawable.blur,"Blur" );
        songsList.add(audio);

        music = MediaPlayer.create(PlayLists.this,R.raw.thelessiknow);
        audio = new Audio(Integer.toString(R.raw.thelessiknow),"The Less I Know", Integer.toString(music.getDuration()),R.drawable.letithappenalbum,"Tame Impala" );
        songsList.add(audio);

        String playlistTitle = getIntent().getStringExtra("PlaylistName");

        binding.recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView1.setAdapter(new MusicChooseAdapter(binding,songsList,getApplicationContext(),playlistTitle));
    }
}