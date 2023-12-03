package com.example.faceliteics108;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.HashMap;

public class HelloController  {
    @FXML
    private Label friendLabel;
    @FXML
    private Button deleteButton;
    @FXML
    private Button lookupButton;
    @FXML
    private TextField statusField;
    @FXML
    private TextField imageField;
    @FXML
    private TextField friendField;
    @FXML
    private Button statusButton;
    @FXML
    private Button imageButton;
    @FXML
    private Button friendButton;
    @FXML
    private Pane imageArea;
    @FXML
    private Label nameLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Pane friendsArea;
    @FXML
    private Label displayLabel;
    @FXML
    private Button addButton;
    @FXML
    private TextField nameField;
    @FXML
    private Label welcomeText;


    private HashMap<String, UserClass> users = new HashMap<>();
    //resource path as variable for ease of editing
    private String resourceAddress = "file:\\C:\\Users\\OMEN\\IdeaProjects\\projects\\FaceLiteICS108\\src\\main\\resources\\";

    private int imageScale = 240;
    @FXML
    protected void onAddClick() {
        String accountName = nameField.getText();

        // Check if the account already exists
        if (users.containsKey(accountName)) {
            displayLabel.setText("Account already exists!");
        } else {
            // Create a new UserClass instance
            UserClass newUser = new UserClass(accountName);

            // Add the user to the users map
            users.put(accountName, newUser);

            // Update the UI
            nameLabel.setText( accountName);
            refreshImageView(newUser);
            displayLabel.setText("New Profile Created");
        }
    }
    @FXML
    protected void onDeleteClick() {
        String accountName = nameField.getText();

        // Check if the account exists
        if (users.containsKey(accountName)) {
            // Remove the account from the users map
            users.remove(accountName);

            // Update the UI
            nameLabel.setText("");
            displayLabel.setText("Profile of " + accountName+ " is deleted ");
        } else {
            displayLabel.setText("Account not found!");
        }
    }
    @FXML
    protected void onLookUpClick() {
        String accountName = nameField.getText();

        // Check if the account exists
        if (users.containsKey(accountName)) {
            UserClass user = users.get(accountName);

            // Update the UI with detailed information about the account
            nameLabel.setText(user.getName());
            refreshImageView(user);
            updateFriendsArea(user);
            displayLabel.setText("Account found!");
        } else {
            // Update the UI to indicate that the account was not found
            refreshImageView();
            nameLabel.setText(" ");
            displayLabel.setText("A profile with the name "+ accountName+ " does not exist");
        }
    }
    @FXML
    protected void onChangeStatusClick(){

    }
    @FXML
    protected void onChangePictureClick(){
        //get image name from textField
        String imageName = imageField.getText();

        //get the currently displayed user and assign its object
        String accountName = nameLabel.getText();
        UserClass user = users.get(accountName);

        //set the image in the user's data field
        //& create imageView for the name to display it
        user.setImage(imageName);

        ImageView imageView = new ImageView(resourceAddress + imageName);
        imageView.setFitHeight(imageScale);
        imageView.setFitWidth(imageScale);
        imageArea.getChildren().add(imageView);

        //output progress
        displayLabel.setText("Picture Updated");



    }
    @FXML
    protected void onAddFriendClick() {
        String accountName = nameLabel.getText();
        UserClass user1 = users.get(accountName);
        UserClass user2 = users.get(friendField.getText());

        user1.getFriendsList().add(user2);
        user2.getFriendsList().add(user1);

        // Update the UI

        updateFriendsArea(user1);
    }

    private void updateFriendsArea(UserClass user) {
        friendsArea.getChildren().clear();

        if (!user.getFriendsList().isEmpty()) {
            friendLabel.setText("Friends of " + user.getName() + ":");

            for (UserClass friend : user.getFriendsList()) {
                Label friendLabel = new Label(friend.getName());
                friendsArea.getChildren().add(friendLabel);
            }
        } else {
            displayLabel.setText(user.getName() + " has no friends.");
        }
    }

    //imageView refresher
    public void refreshImageView(UserClass user){
        //clear all items from imageArea pane
        imageArea.getChildren().clear();

        //create and add new imageview with correct scalling
        ImageView displayImageView = new ImageView(resourceAddress + user.getImage());
        displayImageView.setFitHeight(imageScale);
        displayImageView.setFitWidth(imageScale);
        //display
        imageArea.getChildren().add(displayImageView);

    }
    //override of refreshImageView with no parameter
    public void refreshImageView(){
        imageArea.getChildren().clear();
        ImageView displayImageView = new ImageView(resourceAddress + "");
        displayImageView.setFitHeight(imageScale);
        displayImageView.setFitWidth(imageScale);
        imageArea.getChildren().add(displayImageView);

    }
}