package com.appsbee.www.todolist.TodoActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsbee.www.todolist.R;

import java.util.List;

/**
 * Created by Wasabeef on 2015/01/03.
 */
public class RecycleTodoAdapter extends RecyclerView.Adapter<RecycleTodoAdapter.ViewHolder> {

  private ITodo mITodo;
  private List<String> mDataSet;

  public RecycleTodoAdapter(ITodo mITodo, List<String> dataSet) {
    this.mITodo = mITodo;
    mDataSet = dataSet;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(mITodo.getContext()).inflate(R.layout.row_todo_list, parent, false);
    return new ViewHolder(v);
  }

  @Override public void onBindViewHolder(ViewHolder holder, final int position) {
    holder.tv_subject.setText(mDataSet.get(position));
    holder.bt_done.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mITodo.deleteData(mDataSet.get(position));
      }
    });
  }

  @Override public int getItemCount() {
    return mDataSet.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_subject;
    private ImageView bt_done;

    public ViewHolder(View itemView) {
      super(itemView);
      tv_subject = (TextView) itemView.findViewById(R.id.tv_subject);
      bt_done = (ImageView) itemView.findViewById(R.id.bt_done);
    }
  }
}
