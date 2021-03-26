package com.example.personalprofile.repositories.context;

import com.example.personalprofile.models.requestbody.RequestBodyStudent;
import com.example.personalprofile.repositories.meta.RequestContext;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface StudentModificationContext extends RequestContext {

    @Getter
    @AllArgsConstructor(staticName = "of")
    class Create implements StudentModificationContext {
        private final RequestBodyStudent student;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    class Read implements StudentModificationContext {
        private final String studentId;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    class Delete implements StudentModificationContext {
        private final String studentId;
    }
}
