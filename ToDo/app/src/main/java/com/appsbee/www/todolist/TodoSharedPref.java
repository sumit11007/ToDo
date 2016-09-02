package com.appsbee.www.todolist;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by root on 9/2/16.
 */
public class TodoSharedPref {
    public static TodoSharedPref mTodoSharedPref;
    public SharedPreferences mSharedPreference;

    private TodoSharedPref(Context context) {
        mSharedPreference = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public static synchronized TodoSharedPref getInstance(Context context) {
        if (mTodoSharedPref == null) {
            mTodoSharedPref = new TodoSharedPref(context);
        }
        return mTodoSharedPref;
    }
}
