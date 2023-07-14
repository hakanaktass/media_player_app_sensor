package com.hakanaktas.mediaplayerproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.hakanaktas.mediaplayerproject.databinding.ActivityMediaGalleryBinding;
import com.hakanaktas.mediaplayerproject.databinding.ActivitySignUpBinding;

import java.io.File;
import java.util.ArrayList;

public class MediaGallery extends AppCompatActivity {
    private ActivityMediaGalleryBinding binding;
    private FirebaseAuth auth;
    ArrayList<Audio> songsList ;
    Audio audio ;
    MediaPlayer music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMediaGalleryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        auth = FirebaseAuth.getInstance();
        songsList = (ArrayList<Audio>)getIntent().getSerializableExtra("LIST") ;

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new AudioListAdapter(this,songsList,getApplicationContext()));




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        CheckBox checkBox;
        if(item.getItemId() == R.id.signOut){

            auth.signOut();

            Intent intentToMain = new Intent(MediaGallery.this,MainActivity.class);
            startActivity(intentToMain);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void createPlaylist (View view){
        Intent intent = new Intent(MediaGallery.this,CreatePlaylist.class);
        startActivity(intent);
    }
}


