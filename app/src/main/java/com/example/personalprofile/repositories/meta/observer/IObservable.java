package com.example.personalprofile.repositories.meta.observer;

public interface IObservable<T> {

    void attachObserver(IRepositoryObserver<T> observer);

    void notifyObservers(NotificationContext<T> context);
}
