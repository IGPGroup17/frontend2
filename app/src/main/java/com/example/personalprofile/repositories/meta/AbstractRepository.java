package com.example.personalprofile.repositories.meta;

import android.app.Activity;

import com.example.personalprofile.repositories.meta.observer.IObservable;
import com.example.personalprofile.repositories.meta.observer.IRepositoryObserver;

import java.util.HashSet;
import java.util.Set;

// M = observer data type
public abstract class AbstractRepository<T extends Activity, V extends RequestContext, M> implements IObservable<M> {

    protected final Set<IRepositoryObserver<M>> observers = new HashSet<>();

    public abstract void sendRequest(T activity, V context);

    @Override
    public void attachObserver(IRepositoryObserver<M> observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(IRepositoryObserver.NotificationContext<M> context) {
        for (IRepositoryObserver<M> observer : observers) {
            observer.onNotification(context);
        }
    }
}
