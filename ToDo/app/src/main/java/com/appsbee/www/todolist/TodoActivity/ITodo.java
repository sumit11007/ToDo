package com.appsbee.www.todolist.TodoActivity;

import com.appsbee.www.todolist.IBase;

import java.util.ArrayList;

/**
 * Created by root on 9/2/16.
 */
public interface ITodo extends IBase {
    void updateRecycleView(ArrayList<String> list);

    void deleteData(String deletedtext);
}
