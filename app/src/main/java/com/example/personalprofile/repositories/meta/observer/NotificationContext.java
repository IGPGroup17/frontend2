package com.example.personalprofile.repositories.meta.observer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class NotificationContext<T> {

    private final String message;

    private final T data;

    public static <T> NotificationContext<T> of(String message, T data) {
        return new NotificationContext<>(message, data);
    }

    public static <T> NotificationContext<T> of(T data) {
        return new NotificationContext<T>("", data);
    }


    public NotificationContext(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
