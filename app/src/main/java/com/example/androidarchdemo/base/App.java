package com.example.androidarchdemo.base;

import android.app.Application;

import com.example.androidarchdemo.di.components.AppComponent;
import com.example.androidarchdemo.di.components.DaggerAppComponent;


public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }
}
