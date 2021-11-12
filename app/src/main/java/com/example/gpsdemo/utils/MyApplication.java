package com.example.gpsdemo.utils;

import android.app.Application;

import com.example.gpsdemo.DashboardActivity;


public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(NetworkChangeReceiver.ConnectivityReceiverListener listener) {
        NetworkChangeReceiver.connectivityReceiverListener = listener;
    }
    public void setConnectivityListenerNoty(DashboardActivity listener) {
    }

}