package com.appsbee.www.todolist.TodoActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;

import com.appsbee.www.todolist.BaseActivity;
import com.appsbee.www.todolist.R;
import com.appsbee.www.todolist.RecylerViewAnimator.SlideInLeftAnimationAdapter;
import com.appsbee.www.todolist.RecylerViewAnimator.SlideInLeftAnimator;

import java.util.ArrayList;
/**
 * Created by root on 9/2/16.
 */
public class TodoActivity extends BaseActivity implements ITodo {


    private RecyclerView recyclerView;
    private TodoPresenter todoPresenter;
    private FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialog();
            }
        });
        todoPresenter = new TodoPresenter(this);
        todoPresenter.updateTodoList();
    }



    private void addDialog() {
        AlertDialog.Builder todoTaskBuilder = new AlertDialog.Builder(this);
        todoTaskBuilder.setTitle("Add Todo Task Item");
        todoTaskBuilder.setMessage("describe the Todo task...");
        final EditText todoET = new EditText(this);
        todoTaskBuilder.setView(todoET);
        todoTaskBuilder.setPositiveButton("Add Task", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String todoTaskInput = todoET.getText().toString();
                todoPresenter.insertData(todoTaskInput);
            }
        });

        todoTaskBuilder.setNegativeButton("Cancel", null);
        todoTaskBuilder.create().show();
    }


    @Override
    public void updateRecycleView(ArrayList<String> list) {
        recyclerView = (RecyclerView) findViewById(R.id.rv_todolist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new SlideInLeftAnimator());
        final RecycleTodoAdapter adapter = new RecycleTodoAdapter(this, list);
        SlideInLeftAnimationAdapter alphaAdapter = new SlideInLeftAnimationAdapter(adapter);
        alphaAdapter.setFirstOnly(false);
        alphaAdapter.setDuration(500);
        alphaAdapter.setInterpolator(new OvershootInterpolator(.5f));
        recyclerView.setAdapter(alphaAdapter);
    }

    @Override
    public void deleteData(String deletedtext) {
        todoPresenter.deleteItem(deletedtext);
    }
}