package com.hakanaktas.mediaplayerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.hakanaktas.mediaplayerproject.databinding.ActivityCreatePlaylistBinding;
import com.hakanaktas.mediaplayerproject.databinding.ActivityPlayListMediaGalleryBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayListMediaGallery extends AppCompatActivity {
    public ActivityPlayListMediaGalleryBinding binding;
    ArrayList<Audio> songsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayListMediaGalleryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        PlaylistClass playlistClass = (PlaylistClass) getIntent().getSerializableExtra("playlistMedia");
        songsList = playlistClass.songsList;

        binding.playlistmediaView.setLayoutManager(new LinearLayoutManager(this));
        binding.playlistmediaView.setAdapter(new PlayListMediaGalleryAdapter(getApplicationContext(),songsList));
    }
}