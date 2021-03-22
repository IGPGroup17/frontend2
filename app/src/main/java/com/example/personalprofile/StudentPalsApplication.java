package com.example.personalprofile;

import android.app.Application;

import com.example.personalprofile.http.VolleyQueue;

public class StudentPalsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        VolleyQueue.getInstance().destroy();
    }

    private void init() {
        VolleyQueue.init(this);
    }
}
