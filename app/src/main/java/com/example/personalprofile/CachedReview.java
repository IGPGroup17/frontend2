package com.example.personalprofile;

import com.example.personalprofile.models.Review;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import lombok.Getter;

public class CachedReview {

    @Getter
    private Review review;

    private static CachedReview instance;

    private CachedReview() {
        this.review = Review.builder().reviewerName(AppUser.getInstance().getStudent().getRealName()).build();
    }

    public static CachedReview getInstance() {
        if (instance == null) {
            instance = new CachedReview();
        }
        return instance;
    }

    public void assignInitialContext(String eventName, String organiserId) {
        review.setEventName(eventName);
        review.setOrganiserId(organiserId);
    }

    public void assignValues(String rating, String description) {
        review.setDescription(description);
        review.setRating(Integer.parseInt(rating));
        Date date = new Date();
        Format format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String formatted = format.format(date);
        review.setCreationDate(formatted);
        review.setLastModificationDate(formatted);
    }

    public void clear() {
        this.review = Review.builder().organiserId(AppUser.getInstance().getStudent().getRealName()).build();
    }
}
