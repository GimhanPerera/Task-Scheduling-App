package com.example.test3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test3.Adapter.ToDoAdapter;
import com.example.test3.Model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {

    private TextView a;
    private FloatingActionButton btn_action;

    View v;
    List<Task> taskList=new ArrayList<Task>();
    private RecyclerView taskRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RelativeLayout relLayout;
    private TextView homePageDate;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_home,container,false);
        // IM/2020/049 - Gimhan
        this.relLayout= v.findViewById(R.id.relayout);
        listClass object = listClass.getInstance();//get task list

        taskRecyclerView = v.findViewById(R.id.taskRecyclerView);
        taskRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(requireContext());
        taskRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new ToDoAdapter(object.getTaskList(), requireContext());
        taskRecyclerView.setAdapter(mAdapter);

        //Displaying the current date in the home page
        //Bimindu
        homePageDate = v.findViewById(R.id.txt_homePageDate);
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);

        // Mapping the retrieved value to the corresponding month
        String monthName = "";
        switch (month) {
            case Calendar.JANUARY:
                monthName = "January";
                break;
            case Calendar.FEBRUARY:
                monthName = "February";
                break;
            case Calendar.MARCH:
                monthName = "March";
                break;
            case Calendar.APRIL:
                monthName = "April";
                break;
            case Calendar.MAY:
                monthName = "May";
                break;
            case Calendar.JUNE:
                monthName = "June";
                break;
            case Calendar.JULY:
                monthName = "July";
                break;
            case Calendar.AUGUST:
                monthName = "August";
                break;
            case Calendar.SEPTEMBER:
                monthName = "September";
                break;
            case Calendar.OCTOBER:
                monthName = "October";
                break;
            case Calendar.NOVEMBER:
                monthName = "November";
                break;
            case Calendar.DECEMBER:
                monthName = "December";
                break;
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        // Mapping the retrieved value to the corresponding day of the week
        String dayName = "";
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                dayName = "SUN";
                break;
            case Calendar.MONDAY:
                dayName = "MON";
                break;
            case Calendar.TUESDAY:
                dayName = "TUE";
                break;
            case Calendar.WEDNESDAY:
                dayName = "WED";
                break;
            case Calendar.THURSDAY:
                dayName = "THU";
                break;
            case Calendar.FRIDAY:
                dayName = "FRI";
                break;
            case Calendar.SATURDAY:
                dayName = "SAT";
                break;
        }
        int toDay = calendar.get(Calendar.DAY_OF_MONTH);//Getting current datte
        String display_date = dayName+", "+toDay+" "+monthName ;//Display string
        homePageDate.setText(display_date);

        reArrange();//Arrange the order

        btn_action = v.findViewById(R.id.btn_action);
        btn_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Open new task page
                ((HomeActivity) getActivity()).openAddPage();
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(taskRecyclerView);

        return v;
    }

    public void reArrange(){
        // IM/2020/049 - Gimhan
        //Rearrange the task list
        //Toast.makeText(requireContext(),"reArrange in HmFr", Toast.LENGTH_SHORT).show();
        listClass object=listClass.getInstance();
        object.reArrangeBydate();
        object.reArrangeByChecked();
        mAdapter.notifyDataSetChanged();
        //Toast.makeText(requireContext(),"Test: notifyDataSetChanged", Toast.LENGTH_SHORT).show();//For Testing
        //mAdapter = new ToDoAdapter(object.getTaskList(), requireContext());//May be occur a problem
        // IM/2020/049 - Gimhan
    }

    // IM/2020/049 - Gimhan
    //For delete items
    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            try{//Delete the item by swipping
                listClass object=listClass.getInstance();
                Task temp = object.getTaskList().get(viewHolder.getLayoutPosition());
                object.getTaskList().remove(viewHolder.getLayoutPosition()); //Remove the item
                reArrange();//Rearrange
                Snackbar snackbar = Snackbar.make(relLayout, "Item deleted", Snackbar.LENGTH_SHORT)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                object.setNewTask(temp);
                                reArrange();
                                //Toast.makeText(requireContext(),"DONE: "+temp.getTitle(), Toast.LENGTH_SHORT).show();//For Testing
                            }
                        });
                snackbar.show();
            }catch (Exception ex){
                Toast.makeText(requireContext(),"Exception!!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}// IM/2020/049 - Gimhan