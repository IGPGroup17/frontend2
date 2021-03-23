package com.example.personalprofile.repositories.meta;

import android.app.Activity;

import com.example.personalprofile.activities.meta.ObserverActivity;
import com.example.personalprofile.repositories.meta.observer.IObservable;
import com.example.personalprofile.repositories.meta.observer.IRepositoryObserver;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;
import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;

// M = observer data type
public abstract class AbstractRepository<V extends RequestContext, M> implements IObservable<M> {

    protected final Set<IRepositoryObserver<M>> observers = new HashSet<>();

    protected static final Gson GSON = new Gson();

    public abstract void sendRequest(ObserverActivity<M> activity, V context);

    @Override
    public void attachObserver(IRepositoryObserver<M> observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(NotificationContext<M> context) {
        for (IRepositoryObserver<M> observer : observers) {
            observer.onNotification(context);
        }
    }
}
