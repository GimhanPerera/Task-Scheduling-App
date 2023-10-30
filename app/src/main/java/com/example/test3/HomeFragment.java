package com.example.test3;

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

import java.util.ArrayList;
import java.util.Arrays;
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
    private RecyclerView.LayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_home,container,false);
        //------------------------------
        this.relLayout= v.findViewById(R.id.relayout);
        listClass object = listClass.getInstance();//get task list
        //fillTaskList();
        //final String TAG = "Task APP";
        //Log.d(TAG,"onCreate: "+taskList.toString());//FOR TESTING
        //Toast.makeText(this,"Task count = "+taskList.size(),Toast.LENGTH_SHORT).show();

        //------------------------------
        taskRecyclerView = v.findViewById(R.id.taskRecyclerView);
        taskRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(requireContext());//May be occur a problem
        taskRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new ToDoAdapter(object.getTaskList(), requireContext());//May be occur a problem
        taskRecyclerView.setAdapter(mAdapter);

        reArrange();//Arrange the order

        //taskRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        //------------------------------------

        btn_action = v.findViewById(R.id.btn_action);
        btn_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(requireContext(), AddTaskFragment.class);
                //startActivity(intent);
                //HomeActivity.openAddPage();
                ((HomeActivity) getActivity()).openAddPage();
                //Bundle result = new Bundle();
                //result.putString("df1","Hello");
                //getParentFragmentManager().setFragmentResult("dataForm1",result);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(taskRecyclerView);

        return v;
    }


    private void fillTaskList() {
        Task t0 = new Task(1,"Do homework","Nothing",2023,10,28);
        Task t1 = new Task(2,"Do Assignment","Nothing",2023,10,26);
        Task t2 = new Task(3,"Say hay","Nothing",2023,10,26);
        taskList.addAll(Arrays.asList(new Task[]{t0,t1,t2}));
    }

    public void reArrange(){
        Toast.makeText(requireContext(),"reArrange in HmFr", Toast.LENGTH_SHORT).show();
        listClass object=listClass.getInstance();
        object.reArrangeBydate();
        mAdapter.notifyDataSetChanged();
        //mAdapter = new ToDoAdapter(object.getTaskList(), requireContext());//May be occur a problem
    }

    //For delete items
    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            listClass object=listClass.getInstance();
            Task temp = object.getTaskList().get(viewHolder.getLayoutPosition());
            object.getTaskList().remove(viewHolder.getLayoutPosition()); //Remove the item
            reArrange();//Rearrange
            Snackbar snackbar = Snackbar.make(relLayout, "Item deleted", Snackbar.LENGTH_SHORT)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Task temp = new Task();
                            int nexid = object.getNextId();
                            object.setNewTask(temp);
                            reArrange();
                            Toast.makeText(requireContext(),"DONE: "+temp.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                    });
            snackbar.show();


            //taskList.remove(viewHolder.getLayoutPosition());//0,1,2

            //mAdapter.notifyDataSetChanged();
        }
    };
}