package com.example.test3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView; // Import TextView

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.test3.EditTaskActivity;
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
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send the coontrol to the edit task activity
                Intent intent = new Intent(context, EditTaskActivity.class);//Go to editTask Fragment
                intent.putExtra("id",task.getId());//Get the ID of the selected item - (taskList.get(position) -> task)
                context.startActivity(intent);

                //((HomeActivity) getActivity()).openeditPage();//Open home again.
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox toDoCheckbox;
        TextView titleTextView; // Declare titleTextView
        TextView dateTextView; // Declare dateTextView
        CardView parentLayout;//For edit part - Storing a Task card

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            toDoCheckbox = itemView.findViewById(R.id.todoCheckbox);
            titleTextView = itemView.findViewById(R.id.titleTextView); // Initialize titleTextView
            dateTextView = itemView.findViewById(R.id.dateTextView); // Initialize dateTextView
            parentLayout = itemView.findViewById(R.id.oneLineTaskLayout);
        }
    }
}