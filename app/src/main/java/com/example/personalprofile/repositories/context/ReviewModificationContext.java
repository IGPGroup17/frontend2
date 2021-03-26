package com.example.personalprofile.repositories.context;

import com.example.personalprofile.models.Review;
import com.example.personalprofile.repositories.meta.RequestContext;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface ReviewModificationContext extends RequestContext {
    @Getter
    @AllArgsConstructor(staticName = "of")
    class Create implements ReviewModificationContext {
        private final Review review;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    class Read implements ReviewModificationContext {
        private final String reviewId;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    class Delete implements ReviewModificationContext {
        private final String reviewId;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    class Update implements ReviewModificationContext {
        private final Review review;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    class ReadAll implements ReviewModificationContext {
        private final String organiserId;
    }
}
