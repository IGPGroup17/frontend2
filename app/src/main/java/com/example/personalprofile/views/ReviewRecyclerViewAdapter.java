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
import com.example.personalprofile.menu.EventPopupMenu;
import com.example.personalprofile.models.Event;
import com.example.personalprofile.models.Review;
import com.example.personalprofile.repositories.meta.RepositoryConstants;
import com.example.personalprofile.util.JSONArrayUtil;
import com.example.personalprofile.util.TimeUtil;
import com.google.gson.GsonBuilder;

import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.Getter;

public class ReviewRecyclerViewAdapter extends RecyclerView.Adapter<ReviewRecyclerViewAdapter.ViewHolder> {

    private final List<Review> reviews;

    @Getter
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;

        private final TextView rating;

        private final TextView description;

        private final TextView name;

        private final TextView creationDate;

        private final TextView reviewerName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cardView = itemView.findViewById(R.id.carView);
            this.name = itemView.findViewById(R.id.review_name);
            this.rating = itemView.findViewById(R.id.review_rating);
            this.description = itemView.findViewById(R.id.review_description);
            this.reviewerName = itemView.findViewById(R.id.review_reviewername);
            this.creationDate = itemView.findViewById(R.id.review_creationdate);
        }
    }

    public ReviewRecyclerViewAdapter(List<Review> reviews) {
        this.reviews = reviews;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_review_adapter_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review event = reviews.get(position);

        Log.d("review", new GsonBuilder().setPrettyPrinting().create().toJson(event));

        holder.getName().setText(event.getEventName());
        holder.getRating().setText(toRating(event.getRating()));
        holder.getDescription().setText(event.getDescription());
        holder.getCreationDate().setText(event.getCreationDate());
        holder.getReviewerName().setText(event.getReviewerName());
    }

    private String toRating(int rating) {
        return rating + " / 5";
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }
}
