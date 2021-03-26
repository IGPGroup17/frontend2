package com.example.personalprofile.activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.personalprofile.AppUser;
import com.example.personalprofile.R;
import com.example.personalprofile.activities.meta.ObservingActivity;
import com.example.personalprofile.models.Review;
import com.example.personalprofile.repositories.AllReviewRepository;
import com.example.personalprofile.repositories.context.ReviewModificationContext;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;
import com.example.personalprofile.views.ReviewRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReadReviewsActivity extends ObservingActivity<List<Review>> {

    private final List<Review> currentReviews = new ArrayList<>();

    private RecyclerView recyclerView;

    private ReviewRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_my_reviews);

        recyclerView = findViewById(R.id.review_recycler_view);
        adapter = new ReviewRecyclerViewAdapter(currentReviews);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        findViewById(R.id.homebutton).setOnClickListener(listener -> onClickHomeButton()); // home button
        findViewById(R.id.chatbutton).setOnClickListener(listener -> onClickChatButton());
        findViewById(R.id.personalprofile).setOnClickListener(listener -> onClickPersonalProfileButton());

        AllReviewRepository.getInstance().sendRequest(this, ReviewModificationContext.ReadAll.of(AppUser.getInstance().getCurrentReviewOrganiserId()));
    }

    public void onClickHomeButton() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

    public void onClickChatButton() {
        Intent intent = new Intent(this, EventChatActivity.class);
        startActivity(intent);
    }

    public void onClickPersonalProfileButton() {
        Intent intent = new Intent(this, OrganiserProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNotification(NotificationContext<List<Review>> notificationContext) {
        currentReviews.clear();
        currentReviews.addAll(notificationContext.getData());

        adapter.notifyDataSetChanged();
    }
}