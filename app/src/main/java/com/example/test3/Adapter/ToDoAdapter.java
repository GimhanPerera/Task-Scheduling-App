package com.example.test3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView; // Import TextView

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test3.Model.Task;
import com.example.test3.R;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    List<Task> taskList;
    Context context;
    String[] month= {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    public ToDoAdapter(List<Task> taskList, Context context) {
        this.taskList = taskList;//Our Task List
        this.context = context; //Where this need to show
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_task_layout, parent, false);//In here, We create a card.

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Task task = taskList.get(position);
        //holder.titleTextView.setText(task.getTitle()); // Access titleTextView from MyViewHolder - Bimidu
        holder.toDoCheckbox.setText(task.getTitle()); // Access titleTextView from MyViewHolder - Gimhan
        holder.dateTextView.setText(month[(task.getMonth())] + "/" + task.getDate()); // Access dateTextView from MyViewHolder
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox toDoCheckbox;
        TextView titleTextView; // Declare titleTextView
        TextView dateTextView; // Declare dateTextView

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            toDoCheckbox = itemView.findViewById(R.id.todoCheckbox);
            titleTextView = itemView.findViewById(R.id.titleTextView); // Initialize titleTextView
            dateTextView = itemView.findViewById(R.id.dateTextView); // Initialize dateTextView
        }
    }
}