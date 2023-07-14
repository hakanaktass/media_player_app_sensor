package com.hakanaktas.mediaplayerproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayListMediaGalleryAdapter extends RecyclerView.Adapter<PlayListMediaGalleryAdapter.ViewHolder> {
    Context context;
    ArrayList<Audio> songsList;

    public PlayListMediaGalleryAdapter(Context context, ArrayList<Audio> songsList) {
        this.context = context;
        this.songsList = songsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_song,parent,false);
        return new PlayListMediaGalleryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Audio songData = songsList.get(position);
        holder.titleTextView.setText(songData.getMusicTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicPlayer.getInstance().reset();
                MusicPlayer.currentIndex = holder.getAdapterPosition();
                Intent intent = new Intent(context,MusicPlayerActivity.class);
                intent.putExtra("LIST",songsList);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return songsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView shareButton;
        ImageView deleteButton;
        TextView titleTextView;
        ImageView iconImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.songTitleText);
            iconImageView = itemView.findViewById(R.id.songIcon);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            shareButton = itemView.findViewById(R.id.shareButton);
        }
    }
}
