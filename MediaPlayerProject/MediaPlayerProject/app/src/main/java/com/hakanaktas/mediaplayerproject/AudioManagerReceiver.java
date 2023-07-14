package com.hakanaktas.mediaplayerproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;

public class AudioManagerReceiver extends BroadcastReceiver {

    private AudioManager audioManager;

    public void onReceive(Context context, Intent intent) {



        audioManager = (AudioManager) context.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        if (intent.getExtras().getInt("Durum")==1 ){
            audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
            Toast.makeText(context.getApplicationContext(), "(Hareketsiz ve akıllı telefon cepte)", Toast.LENGTH_SHORT).show();

        } else if (intent.getExtras().getInt("Durum")==2 ){
            audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
            Toast.makeText(context.getApplicationContext(), "(Hareketsiz ve akıllı telefon masada)", Toast.LENGTH_SHORT).show();

        }
        else if (intent.getExtras().getInt("Durum")==3){
            audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
            Toast.makeText(context.getApplicationContext(), "(Hareketli ve akıllı telefon cepte)", Toast.LENGTH_SHORT).show();
        }

    }
}
