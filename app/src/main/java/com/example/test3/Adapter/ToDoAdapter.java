package com.example.test3.Adapter;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView; // Import TextView
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.test3.EditTaskActivity;
import com.example.test3.HomeActivity;
import com.example.test3.HomeFragment;
import com.example.test3.Model.Task;
import com.example.test3.R;
import com.example.test3.listClass;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    List<Task> taskList;
    Context context;
    String[] month= {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    // Get the current date
    Calendar calendar1 = Calendar.getInstance();
    // Get the current year
    int currentYear = calendar1.get(Calendar.YEAR);
    // Get the current month as an integer (0-11, where 0 represents January and 11 represents December)
    int currentMonth = calendar1.get(Calendar.MONTH);
    // Get the current month as an integer (0-11, where 0 represents January and 11 represents December)
    int currentDate = calendar1.get(Calendar.DATE);

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
        holder.todoTitle.setText(task.getTitle()); // Access titleTextView from MyViewHolder - Gimhan //CHANGED - When adding ceperate title

        //set Today

        if((task.getYear() == currentYear) && (task.getMonth() == currentMonth) && (task.getDate() == currentDate)){//If Today
            holder.dateTextView.setText("Today");
            holder.dateTextView.setTextColor(Color.BLACK);
        }
        else{//Other days
            holder.dateTextView.setText(month[(task.getMonth())] + "/" + task.getDate());
            if((task.getYear() < currentYear) || ((task.getYear() == currentYear) && (task.getMonth() < currentMonth)) || ((task.getYear() == currentYear) && (task.getMonth() == currentMonth) && (task.getDate() < currentDate))){
                //If previous task
                int color = Color.parseColor("#DD1818");
                holder.dateTextView.setTextColor(color);
            }
            else{
                holder.dateTextView.setTextColor(Color.BLACK);
            }
        }

        //Set checkbox status
        if(task.getCompletion()){
            holder.todoCheckbox.setChecked(true);
            holder.todoTitle.setPaintFlags(holder.todoTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            holder.todoCheckbox.setChecked(false);
            holder.todoTitle.setPaintFlags(holder.todoTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send the coontrol to the edit task activity
                Intent intent = new Intent(context, EditTaskActivity.class);//Go to editTask Fragment
                intent.putExtra("id",task.getId());//Get the ID of the selected item - (taskList.get(position) -> task)
                context.startActivity(intent);
            }
        });

        //checkbox check event
        holder.todoCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    task.setCompletion(isChecked);
                    if(isChecked){
                        task.setCompletion(true);
                        holder.todoTitle.setPaintFlags(holder.todoTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        /*--------------------------------------
                        try{
                            listClass object=listClass.getInstance();
                            Task temp = object.getTaskList().get(position);
                            object.getTaskList().remove(position); //Remove the item
                            reArrange();//Rearrange
                            Snackbar snackbar = Snackbar.make(relLayout, "Item deleted", Snackbar.LENGTH_SHORT)
                                    .setAction("UNDO", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //Task temp = new Task();
                                            //int nexid = object.getNextId();
                                            object.setNewTask(temp);
                                            reArrange();
                                            Toast.makeText(requireContext(),"DONE: "+temp.getTitle(), Toast.LENGTH_SHORT).show();//For Testing
                                        }
                                    });
                            snackbar.show();
                        }catch (Exception ex){
                            Toast.makeText(requireContext(),"Exception!!", Toast.LENGTH_SHORT).show();
                        }
                        //--------------------------------------*/
                    }
                    else{
                        task.setCompletion(false);
                        holder.todoTitle.setPaintFlags(holder.todoTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    }

            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView todoTitle;//CHANGED - When adding ceperate title
        TextView titleTextView; // Declare titleTextView
        TextView dateTextView; // Declare dateTextView
        CardView parentLayout;//For edit part - Storing a Task card
        CheckBox todoCheckbox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            todoCheckbox = itemView.findViewById(R.id.todoCheckbox);
            todoTitle = itemView.findViewById(R.id.todoTitle);//CHANGED - When adding ceperate title
            titleTextView = itemView.findViewById(R.id.titleTextView); // Initialize titleTextView
            dateTextView = itemView.findViewById(R.id.dateTextView); // Initialize dateTextView
            parentLayout = itemView.findViewById(R.id.oneLineTaskLayout);
        }
    }
}
