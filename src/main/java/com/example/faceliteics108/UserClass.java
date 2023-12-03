package com.example.faceliteics108;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class UserClass {
    private ArrayList<UserClass> friendsList;
    private String name;
    private String status;
    private String imageID;

    public UserClass(String name) {
        this.name = name;
        this.friendsList = new ArrayList<>();
        this.imageID = "NO-IMAGE.png";
    }

    public String getName() {
        return name;
    }

    public ArrayList<UserClass> getFriendsList() {
        return friendsList;
    }

    public String getImage() {
        return imageID;
    }

    public String getStatus() {
        return status;
    }

    public void setFriendsList(ArrayList<UserClass> friendsList) {
        this.friendsList = friendsList;
    }

    public void setImage(String image) {
        this.imageID = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
