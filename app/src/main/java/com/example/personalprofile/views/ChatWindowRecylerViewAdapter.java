package com.example.personalprofile.views;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalprofile.R;
import com.example.personalprofile.views.models.ChatWindow;
import com.google.gson.GsonBuilder;

import java.util.List;

import lombok.Getter;

public class ChatWindowRecylerViewAdapter extends RecyclerView.Adapter<ChatWindowRecylerViewAdapter.ViewHolder> {

    private final List<ChatWindow> chatWindows;

    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;

        private final TextView eventName;

        private final TextView lastMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cardView = itemView.findViewById(R.id.carView);
            this.eventName = itemView.findViewById(R.id.chat_name);
            this.lastMessage = itemView.findViewById(R.id.chat_lastmessage);
        }
    }

    public ChatWindowRecylerViewAdapter(List<ChatWindow> events) {
        this.chatWindows = events;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_chat_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatWindow event = chatWindows.get(position);

        Log.d("review", new GsonBuilder().setPrettyPrinting().create().toJson(event));

        holder.getEventName().setText(event.getName());
        holder.getLastMessage().setText(event.getLastMessage());
    }

    @Override
    public int getItemCount() {
        return chatWindows.size();
    }
}
