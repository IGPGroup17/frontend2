package com.example.personalprofile.activities.meta;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalprofile.repositories.meta.observer.IObservable;
import com.example.personalprofile.repositories.meta.observer.IRepositoryObserver;

public abstract class ObservingActivity<T> extends AppCompatActivity implements IRepositoryObserver<T> {
}
