package com.appsbee.www.todolist;

import android.content.Context;
import android.content.Intent;

import com.appsbee.www.todolist.TodoActivity.TodoActivity;

public class ActivityUtil {

    public static void startTodoActivity(Context context) {
        context.startActivity(new Intent(context, TodoActivity.class));
    }
}
