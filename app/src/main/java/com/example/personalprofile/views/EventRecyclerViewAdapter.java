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
import com.example.personalprofile.models.Event;
import com.example.personalprofile.util.TimeUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {

    private final List<Event> events;

    private final Map<Integer, String> cardPositionToEventIdMap;


    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;

        private final TextView eventName;

        private final TextView eventDescription;

        private final TextView dateTime;

        private final TextView goingCount;

        private final TextView interestedCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cardView = itemView.findViewById(R.id.carView);
            this.eventName = itemView.findViewById(R.id.eventname);
            this.eventDescription = itemView.findViewById(R.id.description);
            this.dateTime = itemView.findViewById(R.id.datetime);
            this.goingCount = itemView.findViewById(R.id.going_count);
            this.interestedCount = itemView.findViewById(R.id.interested_count);
        }
    }

    public EventRecyclerViewAdapter(List<Event> events) {
        this.events = events;
        this.cardPositionToEventIdMap = initCardPosMap(events);
    }

    private Map<Integer, String> initCardPosMap(List<Event> events) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < events.size(); i++) {
            String id = events.get(i).getEventId();
            map.put(i, id);
        }
        return map;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_event_adapter_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(listener -> onClick(position));
        Event event = events.get(position);

        Log.d("event", new GsonBuilder().setPrettyPrinting().create().toJson(event));

        String goingText = event.getGoingUsersIDs().size() + " Going";
        String interestedText = event.getInterestedUsersIDs().size() + " Interested";

        holder.getEventName().setText(event.getName());
        holder.getEventDescription().setText(event.getDescription());
        holder.getDateTime().setText(TimeUtil.toTime(event.getScheduledTime()));

        holder.getGoingCount().setText(goingText);
        holder.getInterestedCount().setText(interestedText);
    }

    private void onClick(int position) {
        String id = cardPositionToEventIdMap.get(position);
        Log.d("id", id);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
