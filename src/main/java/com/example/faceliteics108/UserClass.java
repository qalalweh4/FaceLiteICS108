package com.example.faceliteics108;

import java.util.ArrayList;

public class UserClass {
    private ArrayList<UserClass> friendsList;
    private String name;
    private String Status;
    private String image;

    public UserClass(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<UserClass> getFriendsList() {
        return friendsList;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return Status;
    }

    public void setFriendsList(ArrayList<UserClass> friendsList) {
        this.friendsList = friendsList;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
