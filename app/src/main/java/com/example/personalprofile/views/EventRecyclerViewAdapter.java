package com.example.personalprofile.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalprofile.R;
import com.example.personalprofile.models.Event;

import java.util.List;

import lombok.Getter;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {

    private final List<Event> events;


    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView eventView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.eventView = itemView.findViewById(R.id.text);
        }
    }

    public EventRecyclerViewAdapter(List<Event> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_liked_events, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getEventView().setText(events.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
