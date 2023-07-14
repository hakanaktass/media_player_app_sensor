package com.hakanaktas.mediaplayerproject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    ArrayList<PlaylistClass> playList;
    Context context;
    Activity activity;


    public PlaylistAdapter(Activity activity,ArrayList<PlaylistClass> playList, Context context) {
        this.playList = playList;
        this.context = context;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.playlist_row,parent,false);
        return new PlaylistAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlaylistClass playlistClass=playList.get(position);
        holder.titlePlaylist.setText(playlistClass.getPlayListTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity,PlayListMediaGallery.class);
                intent.putExtra("playlistMedia",playlistClass);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
            }
        });
        holder.deleteButtoninPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return playList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView titlePlaylist;
        Button deleteButtoninPL;

        public ViewHolder(View itemView) {
            super(itemView);
            titlePlaylist =itemView.findViewById(R.id.playlistText);
            deleteButtoninPL =itemView.findViewById(R.id.buttonDeleteFromPlaylist);
        }
    }
}