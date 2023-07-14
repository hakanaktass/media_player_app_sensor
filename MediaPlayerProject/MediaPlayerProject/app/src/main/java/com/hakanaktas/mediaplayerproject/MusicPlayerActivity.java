package com.hakanaktas.mediaplayerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;

import com.hakanaktas.mediaplayerproject.databinding.ActivityMusicPlayerBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity {
    private ActivityMusicPlayerBinding binding;
    ArrayList<Audio> songsList;
    Audio currentSong;
    MediaPlayer musicPlayer = MusicPlayer.getInstance();
    MediaPlayer m;
    int x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMusicPlayerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.songTitle.setSelected(true);

        IntentFilter intentFilter = new IntentFilter("com.hakanaktas.mediaplayerproject.SEND_MESSAGE");
        AudioManagerReceiver audioManagerReceiver = new AudioManagerReceiver();
        registerReceiver(audioManagerReceiver,intentFilter);

        songsList = (ArrayList<Audio>)getIntent().getSerializableExtra("LIST");
        setResourcesWithMusic();

        MusicPlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(musicPlayer != null){
                    binding.seekBar.setProgress(musicPlayer.getCurrentPosition());
                    binding.currentTime.setText(converToMMSS(musicPlayer.getCurrentPosition()+""));
                    if (musicPlayer.isPlaying()){
                        binding.pausePlay.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
                        binding.musicIcon.setRotation(x++);
                    }else
                        binding.pausePlay.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
                        binding.musicIcon.setRotation(0);

                }
                new Handler().postDelayed(this,100);

            }
        });

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(musicPlayer!=null && b){
                    musicPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    void setResourcesWithMusic(){
        currentSong = songsList.get(MusicPlayer.currentIndex);
        binding.songTitle.setText(currentSong.getMusicTitle());
        binding.artistTitle.setText(currentSong.getArtistTitle());
        binding.totalTime.setText(converToMMSS(currentSong.getDuration()));

        binding.pausePlay.setOnClickListener(view -> pausePlay());
        binding.next.setOnClickListener(view -> playNextSong());
        binding.previous.setOnClickListener(view -> playPreviousSong());
        playMusic();

    }

    private void playMusic(){
        musicPlayer.reset();
        binding.musicIcon.setImageResource(currentSong.imagePath);
        musicPlayer = MediaPlayer.create(this,Integer.parseInt(currentSong.getSrcPath()));

        musicPlayer.start();
        binding.seekBar.setProgress(0);
        binding.seekBar.setMax(musicPlayer.getDuration());


    }

    private void playNextSong(){
        if(MusicPlayer.currentIndex == songsList.size()-1)
            return;
        MusicPlayer.currentIndex +=1;
        musicPlayer.reset();
        setResourcesWithMusic();

    }

    private void playPreviousSong(){
        if(MusicPlayer.currentIndex == 0)
            return;
        MusicPlayer.currentIndex -=1;
        musicPlayer.reset();
        setResourcesWithMusic();

    }

    private void pausePlay(){
        if(musicPlayer.isPlaying())
            musicPlayer.pause();
        else
            musicPlayer.start();

    }

    public static String converToMMSS(String duration){
        Long millis = Long.parseLong(duration);
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis) %TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) %TimeUnit.MINUTES.toSeconds(1));
    }
}