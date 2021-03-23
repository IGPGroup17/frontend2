package com.example.personalprofile.repositories.meta.observer;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface IRepositoryObserver<T> {

    void onNotification(NotificationContext<T> notificationContext);

    @AllArgsConstructor(staticName = "of")
    @Getter
    class NotificationContext<T> {
        T data;
    }
}
