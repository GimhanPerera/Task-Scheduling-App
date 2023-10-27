package com.example.test3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test3.MainActivity;
import com.example.test3.Model.Task;
import com.example.test3.R;

import java.util.List;

import kotlin.reflect.KParameter;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    List<Task> taskList;
    Context context;

    public ToDoAdapter(List<Task> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_task_layout,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.toDoCheckbox.setText(taskList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox toDoCheckbox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            toDoCheckbox = itemView.findViewById(R.id.todoCheckbox);
        }
    }



}
