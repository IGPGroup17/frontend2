package com.example.personalprofile.views;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.personalprofile.AppUser;
import com.example.personalprofile.R;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.models.Event;
import com.example.personalprofile.repositories.meta.RepositoryConstants;
import com.example.personalprofile.util.JSONArrayUtil;
import com.example.personalprofile.util.TimeUtil;
import com.google.gson.GsonBuilder;

import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.Getter;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {

    private final List<Event> events;

    private final Map<Integer, Event> cardPositionToEventIdMap;

    private final Activity activity;

    private final OnClick onClick;

    @FunctionalInterface
    public interface OnClick {

        OnClick EMPTY = ((view, event) -> {});
        void onClick(View view, Event event);
    }

    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;

        private final TextView tags;

        private final TextView eventName;

        private final TextView eventDescription;

        private final TextView dateTime;

        private final TextView goingCount;

        private final TextView interestedCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cardView = itemView.findViewById(R.id.carView);
            this.eventName = itemView.findViewById(R.id.name);
            this.tags = itemView.findViewById(R.id.event_tags);
            this.eventDescription = itemView.findViewById(R.id.description);
            this.dateTime = itemView.findViewById(R.id.date);
            this.goingCount = itemView.findViewById(R.id.going_count);
            this.interestedCount = itemView.findViewById(R.id.interested_count);
        }
    }

    public EventRecyclerViewAdapter(Activity activity, List<Event> events, OnClick onClick) {
        this.activity = activity;
        this.events = events;
        this.onClick = onClick;
        this.cardPositionToEventIdMap = initCardPosMap(events);
    }

    private Map<Integer, Event> initCardPosMap(List<Event> events) {
        Map<Integer, Event> map = new HashMap<>();
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            map.put(i, event);
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
        holder.itemView.setOnClickListener(listener -> onClick.onClick(listener, cardPositionToEventIdMap.get(position)));
        Event event = events.get(position);

        Log.d("event", new GsonBuilder().setPrettyPrinting().create().toJson(event));

        if (hasUserLikedEvent(event.getEventId())) {
            holder.getCardView().setCardBackgroundColor(Color.GREEN);
        }

        cardPositionToEventIdMap.put(position, event);

        String goingText = event.getGoingUsersIDs().size() + " Going";
        String interestedText = event.getInterestedUsersIDs().size() + " Interested";
        String tagsText = buildTagText(event);

        holder.getTags().setText(tagsText);

        holder.getEventName().setText(event.getName());
        holder.getEventDescription().setText(event.getDescription());
        holder.getDateTime().setText(TimeUtil.toTime(event.getScheduledTime()));

        holder.getGoingCount().setText(goingText);
        holder.getInterestedCount().setText(interestedText);
    }

    private String buildTagText(Event event) {
        StringBuilder builder = new StringBuilder();
        if (event.isAlcoholFree())
            builder.append("Alcohol-Free");
        if (event.isInPerson())
            builder.append("In-Person");
        if (event.isVirtual())
            builder.append("Virtual");

        return builder.toString();
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    private boolean hasUserLikedEvent(String eventId) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        VolleyQueue.getInstance(activity.getApplicationContext()).addRequest(new JsonObjectRequest(Request.Method.GET, RepositoryConstants.STUDENT_ENDPOINT + AppUser.getInstance().getGoogleId(), null, response -> {
            try {
                List<String> list = JSONArrayUtil.toList(response.getJSONArray("likedEvents"), String.class);
                atomicBoolean.set(list.contains(eventId));
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }, error -> {}));
        return atomicBoolean.get();
    }
}
