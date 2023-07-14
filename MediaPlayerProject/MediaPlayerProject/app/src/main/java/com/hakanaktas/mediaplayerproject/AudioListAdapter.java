package com.hakanaktas.mediaplayerproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AudioListAdapter extends RecyclerView.Adapter<AudioListAdapter.ViewHolder> {

    ArrayList<Audio> songsList;
    Context context;
    Activity activity;

    public AudioListAdapter(Activity activity,ArrayList<Audio> songsList, Context context) {
        this.songsList = songsList;
        this.context = context;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_song,parent,false);
        return new AudioListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AudioListAdapter.ViewHolder holder, int position) {
        Audio songData = songsList.get(position);
        holder.songTitleText.setText(songData.getMusicTitle());



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

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((songsList.size() > 1)){
                MusicPlayer.currentIndex = holder.getAdapterPosition();
                songsList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), songsList.size());

            }else {
                    Toast.makeText(context,"Last music can't be deleted.",Toast.LENGTH_SHORT).show();
                }
            }


        });

        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path=songData.srcPath;
                Intent share=new Intent(Intent.ACTION_SEND);
                share.setType("audio/mp3");
                context.getResources().openRawResource(Integer.parseInt(songData.srcPath));
                share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                activity.startActivity(Intent.createChooser(share,"Share mp3 File"));
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
        TextView songTitleText;
        ImageView songIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            songTitleText = itemView.findViewById(R.id.songTitleText);
            songIcon = itemView.findViewById(R.id.songIcon);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            shareButton = itemView.findViewById(R.id.shareButton);
        }
    }
}
