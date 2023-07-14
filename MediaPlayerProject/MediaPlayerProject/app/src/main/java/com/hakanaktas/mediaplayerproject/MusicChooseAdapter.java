package com.hakanaktas.mediaplayerproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hakanaktas.mediaplayerproject.databinding.ActivityMusicChooseAdapterBinding;
import com.hakanaktas.mediaplayerproject.databinding.ActivityPlayListsBinding;

import java.util.ArrayList;

public class MusicChooseAdapter extends RecyclerView.Adapter<MusicChooseAdapter.ViewHolder> {

    ArrayList<Audio> songsList;
    ArrayList<Audio> choosenSongs=new ArrayList<>();
    Context context;
    String title;
    Button finishButton;
    Activity activity;
    public ActivityPlayListsBinding binding;
    public MusicChooseAdapter(ActivityPlayListsBinding binding, ArrayList<Audio> songsList, Context context, String title) {
        this.songsList = songsList;
        this.context = context;
        this.title=title;
        this.activity = activity;
        this.binding=binding;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.choose_music,parent,false);

        return new MusicChooseAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Audio songData=songsList.get(position);
        holder.chooseTitleText.setText(songData.getMusicTitle());


        holder.addListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            choosenSongs.add(songData);
            }
        });

        binding.createPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlaylistClass playlistClass= new PlaylistClass(choosenSongs,title);
                Intent intent=new Intent(context,CreatePlaylist.class);
                intent.putExtra("choosenmusics",playlistClass);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return songsList.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView chooseIcon;
        TextView chooseTitleText;
        Button addListButton,finishButton;

        public ViewHolder(View itemView) {
            super(itemView);
            chooseIcon=itemView.findViewById(R.id.chooseIcon);
            chooseTitleText=itemView.findViewById(R.id.chooseTitleText);
            addListButton=itemView.findViewById(R.id.addListButton);
        }
    }
}