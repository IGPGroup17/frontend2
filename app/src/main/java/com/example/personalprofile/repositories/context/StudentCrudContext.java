package com.example.personalprofile.repositories.context;

import com.example.personalprofile.models.requestbody.RequestBodyStudent;
import com.example.personalprofile.repositories.meta.RequestContext;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface StudentCrudContext extends RequestContext {

    @Getter
    @AllArgsConstructor(staticName = "of")
    class Create implements StudentCrudContext {
        private final RequestBodyStudent student;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    class Read implements StudentCrudContext {
        private final String studentId;
    }
}
