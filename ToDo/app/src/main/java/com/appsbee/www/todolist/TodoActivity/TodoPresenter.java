package com.appsbee.www.todolist.TodoActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.appsbee.www.todolist.DbHelper.TodoListSQLHelper;

import java.util.ArrayList;

/**
 * Created by Ratul Ghosh<tibro4u@gmail.com> on 1/9/16.
 */
public class TodoPresenter {

    private ITodo mITodo;

    private TodoListSQLHelper todoListSQLHelper;
    public TodoPresenter(ITodo mITodo) {
        this.mITodo = mITodo;
    }

    public void insertData(String todoTaskInput) {
        todoListSQLHelper = new TodoListSQLHelper(mITodo.getContext());
        SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.clear();
        values.put(TodoListSQLHelper.COL1_TASK, todoTaskInput);
        sqLiteDatabase.insertWithOnConflict(TodoListSQLHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        updateTodoList();
    }

    public void updateTodoList() {
        todoListSQLHelper = new TodoListSQLHelper(mITodo.getContext());
        SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TodoListSQLHelper.TABLE_NAME,
                new String[]{TodoListSQLHelper._ID, TodoListSQLHelper.COL1_TASK},
                null, null, null, null, null);

        ArrayList<String> list = new ArrayList<>();
        if (cursor.getCount() > 0) {
            list.clear();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                list.add(cursor.getString(1));
                cursor.moveToNext();
            }
        }
        cursor.close();
        mITodo.updateRecycleView(list);
    }

    public void deleteItem(String deletedtext) {
        String deleteTodoItemSql = "DELETE FROM " + TodoListSQLHelper.TABLE_NAME +
                " WHERE " + TodoListSQLHelper.COL1_TASK + " = '" + deletedtext + "'";

        todoListSQLHelper = new TodoListSQLHelper(mITodo.getContext());
        SQLiteDatabase sqlDB = todoListSQLHelper.getWritableDatabase();
        sqlDB.execSQL(deleteTodoItemSql);
        updateTodoList();
    }
}
