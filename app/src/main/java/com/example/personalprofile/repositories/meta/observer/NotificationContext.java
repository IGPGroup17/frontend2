package com.example.personalprofile.repositories.meta.observer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class NotificationContext<T> {
    private final T data;
}
