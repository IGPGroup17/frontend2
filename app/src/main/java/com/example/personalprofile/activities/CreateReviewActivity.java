package com.example.personalprofile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.personalprofile.CachedReview;
import com.example.personalprofile.R;
import com.example.personalprofile.activities.meta.ObservingActivity;
import com.example.personalprofile.models.Review;
import com.example.personalprofile.repositories.ReviewModificationRepository;
import com.example.personalprofile.repositories.context.ReviewModificationContext;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;

public class CreateReviewActivity extends ObservingActivity<Review> {

    private EditText description;

    private Spinner ratingSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_review);

        description = findViewById(R.id.activity_review_description);

        ratingSpinner = findViewById(R.id.activity_review_rating);
        ArrayAdapter<String> ratingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[]{"1", "2", "3", "4", "5"});
        ratingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratingSpinner.setAdapter(ratingAdapter);

        findViewById(R.id.activity_review_finish).setOnClickListener(l -> onClickLeaveReviewButton());
    }

    private void onClickLeaveReviewButton() {
        String rating = ratingSpinner.getSelectedItem().toString();
        String desc = description.getText().toString();
        CachedReview.getInstance().assignValues(rating, desc);
        ReviewModificationRepository.getInstance().sendRequest(this, ReviewModificationContext.Create.of(CachedReview.getInstance().getReview()));
    }


    @Override
    public void onNotification(NotificationContext<Review> notificationContext) {
        openHomePage();
    }

    private void openHomePage() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }
}
