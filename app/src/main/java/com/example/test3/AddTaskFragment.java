package com.example.test3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test3.Model.Task;

public class AddTaskFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);

        Button btn_add = view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Add new task
                listClass object = listClass.getInstance();
                EditText title= view.findViewById(R.id.addTask_Title);
                EditText description= view.findViewById(R.id.addTask_Description);
                Task newTask = new Task(object.getNextId(),title.getText().toString(),description.getText().toString());
                object.setNewTask(newTask);
            }
        });




        return view;
    }
}

