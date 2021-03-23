package com.example.personalprofile.repositories.context;

import com.example.personalprofile.repositories.meta.RequestContext;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class EventModificationContext implements RequestContext {

    public enum Type {
        CREATE,
        DELETE,
        UPDATE;
    }

    private final Type type;
}
