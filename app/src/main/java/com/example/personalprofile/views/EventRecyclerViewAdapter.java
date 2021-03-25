package com.example.personalprofile.views;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.personalprofile.AppUser;
import com.example.personalprofile.R;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.menu.EventPopupMenu;
import com.example.personalprofile.models.Event;
import com.example.personalprofile.repositories.StudentRepository;
import com.example.personalprofile.repositories.meta.RepositoryConstants;
import com.example.personalprofile.util.JSONArrayUtil;
import com.example.personalprofile.util.TimeUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.Getter;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {

    private final List<Event> events;

    private final Map<Integer, String> cardPositionToEventIdMap;

    private final Activity activity;

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

    public EventRecyclerViewAdapter(Activity activity, List<Event> events) {
        this.activity = activity;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(listener -> onClick(listener, position));
        Event event = events.get(position);

        Log.d("event", new GsonBuilder().setPrettyPrinting().create().toJson(event));

        if (hasUserLikedEvent(event.getEventId())) {
            holder.getCardView().setCardBackgroundColor(Color.GREEN);
        }

        cardPositionToEventIdMap.put(position, event.getEventId());

        String goingText = event.getGoingUsersIDs().size() + " Going";
        String interestedText = event.getInterestedUsersIDs().size() + " Interested";

        holder.getEventName().setText(event.getName());
        holder.getEventDescription().setText(event.getDescription());
        holder.getDateTime().setText(TimeUtil.toTime(event.getScheduledTime()));

        holder.getGoingCount().setText(goingText);
        holder.getInterestedCount().setText(interestedText);
    }

    private void onClick(View view, int position) {
        String id = cardPositionToEventIdMap.get(position);
        EventPopupMenu popupMenu = new EventPopupMenu(activity, view, id);
        popupMenu.show();
        Log.d("id", id);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean hasUserLikedEvent(String eventId) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        VolleyQueue.getInstance(activity.getApplicationContext()).addRequest(new JsonObjectRequest(Request.Method.GET, RepositoryConstants.STUDENT_ENDPOINT + AppUser.getInstance().getGoogleId(), null, response -> {
            try {
                List<String> list = JSONArrayUtil.toList(response.getJSONArray("likedEvents"), String.class);
                Log.d("list", String.join(", ", list));
                atomicBoolean.set(list.contains(eventId));
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }, error -> {}));
        return atomicBoolean.get();
    }
}
