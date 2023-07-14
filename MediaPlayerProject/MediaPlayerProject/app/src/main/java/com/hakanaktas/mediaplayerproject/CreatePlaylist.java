package com.hakanaktas.mediaplayerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hakanaktas.mediaplayerproject.databinding.ActivityCreatePlaylistBinding;
import com.hakanaktas.mediaplayerproject.databinding.ActivityMediaGalleryBinding;

import java.util.ArrayList;

public class CreatePlaylist extends AppCompatActivity {
    private ActivityCreatePlaylistBinding binding;
    ArrayList<PlaylistClass> playList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreatePlaylistBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        if(getIntent().hasExtra("choosenmusics") == true){
            PlaylistClass playlistClass= (PlaylistClass) getIntent().getSerializableExtra("choosenmusics");
            playList.add(playlistClass);
        }


        if(playList != null && playList.size()!=0){
            binding.playlistView.setLayoutManager(new LinearLayoutManager(this));
            binding.playlistView.setAdapter(new PlaylistAdapter(this,playList,getApplicationContext()));
        }

        binding.createPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playlistName = binding.playlistName.getText().toString();
                Intent intent=new Intent(CreatePlaylist.this,PlayLists.class);
                intent.putExtra("PlaylistName",playlistName);
                startActivity(intent);
            }
        });
    }
}