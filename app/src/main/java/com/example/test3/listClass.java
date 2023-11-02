package com.example.test3;

import com.example.test3.Model.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class listClass{
    private static List<Task> taskList = new ArrayList<Task>();
    private  static int nextId = 5;
    private static listClass instance = new listClass();

    private listClass() {
        fillTaskList();
    }

    private void fillTaskList() {
        Task t0 = new Task(1,"Do homework","Nothing",2023,9,28);
        Task t1 = new Task(2,"Do Assignment","Nothing",2023,11,26);
        Task t2 = new Task(3,"Say hay","Nothing",2023,9,30);
        Task t3 = new Task(4,"Say it","Nothing",2023,9,26);
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
    public Task getTaskById(int taskId) {
        for (Task task : taskList) {
            if (task.getId() == taskId) {
                return task;
            }
        }
        return null; // Return null if the task with the given ID is not found
    }
    public Task setTaskById(int taskId,String title, String Desc, int year, int month, int date) {
        for (Task task : taskList) {
            if (task.getId() == taskId) {
                task.setTitle(title);
                task.setDescription(Desc);
                task.setYear(year);
                task.setMonth(month);
                task.setDate(date);
            }
        }
        return null; // Return null if the task with the given ID is not found
    }

    public void reArrangeBydate(){
        Collections.sort(taskList, Task.TaskDateAscendingComparator);
    }

    public void reArrangeByChecked(){
        List<Task> tempList = new ArrayList<Task>();
        for (Task task : taskList) {
            if(task.getCompletion()){
                tempList.add(task);
            }
        }
        for (Task task : taskList) {
            if(task.getCompletion()){
                tempList.remove(task);
            }
        }
        taskList.addAll(tempList);
    }
}

