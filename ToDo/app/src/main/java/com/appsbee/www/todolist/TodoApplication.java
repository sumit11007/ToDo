package com.appsbee.www.todolist;

import android.app.Application;

/**
 * Created by root on 9/2/16.
 */
public class TodoApplication extends Application {

    public static TodoApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public TodoSharedPref getAppPreferences() {
        return TodoSharedPref.getInstance(this);
    }
}
