package com.example.test3;

import com.example.test3.Model.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class listClass{
    private static List<Task> taskList = new ArrayList<Task>();
    private  static int nextId = 5;
    private static listClass instance = new listClass();

    private listClass() {
        fillTaskList();
    }

    private void fillTaskList() {
        Task t0 = new Task(1,"Do homework","Nothing");
        Task t1 = new Task(2,"Do Assignment","Nothing");
        Task t2 = new Task(3,"Say hay","Nothing");
        Task t3 = new Task(4,"Say it","Nothing");
        taskList.addAll(Arrays.asList(new Task[]{t0,t1,t2,t3}));
    }

    public static listClass getInstance(){
        return instance;
    }
    public static List<Task> getTaskList() {
        return taskList;
    }

    public static void setTaskList(List<Task> taskList) {
        listClass.taskList = taskList;
    }
    public static void setNewTask(Task NewTask) {
        listClass.taskList.add(NewTask);
        nextId++;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        listClass.nextId = nextId;
    }
}
