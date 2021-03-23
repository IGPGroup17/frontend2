package com.example.personalprofile.repositories.context;

import com.example.personalprofile.models.Event;
import com.example.personalprofile.models.requestbody.RequestBodyEvent;
import com.example.personalprofile.repositories.meta.RequestContext;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface EventModificationContext extends RequestContext {

    @Getter
    @AllArgsConstructor(staticName = "of")
    class Create implements EventModificationContext {
        private final RequestBodyEvent event;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    class Read implements EventModificationContext {
        private final String eventId;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    class Delete implements EventModificationContext {
        private final String eventId;
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    class Update implements EventModificationContext {
        private final Event event;
    }
}
