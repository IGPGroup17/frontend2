package com.example.personalprofile.repositories.context;

import com.example.personalprofile.models.Review;
import com.example.personalprofile.repositories.meta.RequestContext;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface ReviewCrudContext extends RequestContext {

    @Getter
    @AllArgsConstructor(staticName = "of")
    class Create implements ReviewCrudContext {
        private final Review review;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    class Read implements ReviewCrudContext {
        private final String reviewId;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    class Update implements ReviewCrudContext {
        private final Review review;
    }


    @Getter
    @AllArgsConstructor(staticName = "of")
    class Delete implements ReviewCrudContext {
        private final String reviewId;
    }


}
