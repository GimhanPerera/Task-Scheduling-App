package com.example.test3;


import com.example.test3.Model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class userClass {
    private static List<User> userDetails = new ArrayList<User>();

    private  static int nextId = 5;
    private static userClass instance = new userClass();
    private static int loggedInUserId = -1; // Initialize to a default value


    private userClass() {
        addUserList();
    }

    private void addUserList() {
        User u0 = new User(1,"Naduni","Rabel", "nad@gmail.com", "Abc123@123");
        User u1 = new User(2,"Samadhi","Thara", "thara@gmail.com", "Def123@123");
        User u2 = new User(3,"Bimindu","Dhashmika", "bim@gmail.com", "Ghi123@123");
        User u3 = new User(4,"Gimhan","Perera", "gim@gmail.com", "Jkl123@123");

        userDetails.addAll(Arrays.asList(new User[]{u0,u1,u2,u3}));
    }

    public List<User> getList() {
        return userDetails;
    }

    public static userClass getInstance(){
        return instance;
    }
    public static List<User> getUserDetails() {
        return userDetails;
    }

    public static void setTaskList(List<User> userDetails) {
        userClass.userDetails = userDetails;
    }
    public static void setNewUser(User NewUser) {
        userClass.userDetails.add(NewUser);
        nextId++;
    }

    public static User getUserById(int logedOne){
        User log = null;
        for (User user : userDetails) {
            if (logedOne == user.getId()) {
                log = user;
                break;
            }
        }
        return log;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        userClass.nextId = nextId;
    }

    public static int getLoggedInUserId() {
        return loggedInUserId;
    }

    public static void setLoggedInUserId(int userId) {
        loggedInUserId = userId;
    }
}
