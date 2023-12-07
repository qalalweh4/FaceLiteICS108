package com.example.faceliteics108;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.HashMap;

public class HelloController  {
    @FXML
    private RadioButton singleButton;
    @FXML
    private RadioButton marriedButton;
    @FXML
    private Button darkmodeButton;
    @FXML
    private Button lightmodeButton;
    @FXML
    private Button onRemoveFriend;
    @FXML
    private TextField deleteFriendLabel;
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
    private VBox friendsArea;
    @FXML
    private Label displayLabel;
    @FXML
    private Button addButton;
    @FXML
    private TextField nameField;
    @FXML
    private Label welcomeText;
    @FXML
    private Label friendsLabel;

    //to provide constant time complexity for methods like adding deleting and looking up for elements
    // using arraylist here may increase the time because it will use linear search
    private HashMap<String, UserClass> users = new HashMap<>();

    //resource path as variable for ease of editing
    private String resourceAddress = "/Users/qalalweh/Documents/ICS108PROJECT/FaceLiteICS108/src/main/resources/pictures";
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
            updateFriendsArea(newUser);

            clearStatus();
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
            clearStatus();

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
    protected void onChangeStatusClick() {
        // Get the new status from the statusField
        String newStatus = statusField.getText();

        // Get the currently displayed user and update the status
        String accountName = nameLabel.getText();
        UserClass user = users.get(accountName);
        user.setStatus(newStatus);

        // Update the statusLabel
        statusLabel.setText("Status: " + newStatus);

        // Output progress
        displayLabel.setText("Status Updated");
    }
    // Helper method to clear the status
    private void clearStatus() {
        statusField.clear();
        statusLabel.setText("");
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
        String friendName = friendField.getText();

        // Check if the user is trying to add themselves
        if (accountName.equals(friendName)) {
            displayLabel.setText("You can't add yourself as a friend!");
            return; // Exit the method to avoid updating the UI
        }

        UserClass user2 = users.get(friendName);

        // Check if the friend account exists
        if (user2 == null) {
            displayLabel.setText("Friend account not found!");
            return; // Exit the method to avoid updating the UI
        }

        // Check if the friendship already exists
        if (user1.getFriendsList().contains(user2)) {
            displayLabel.setText("You are already friends!");
            return; // Exit the method to avoid updating the UI
        }

        // Add friends
        user1.getFriendsList().add(user2);
        user2.getFriendsList().add(user1);

        // Update the UI
        updateFriendsArea(user1);
    }

    @FXML
    public void onRemoveFriend(){

    }

    //updating friends area
    private void updateFriendsArea(UserClass user) {
        friendsArea.getChildren().clear();

        if (!user.getFriendsList().isEmpty()) {
            for (UserClass friend : user.getFriendsList()) {
                Label friendLabel = new Label(friend.getName());
                friendLabel.setStyle("-fx-text-fill: #FFFFFF");
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

        //create and add new imageview with correct scaling
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
    @FXML
    public void onClickDarkmodeButton() {
        // Set dark mode colors
        String darkBackground = "#000000";  // Dark background color
        String darkText = "#FFFFFF";        // White text color

        // Set dark mode styles
        String darkModeStyle = "-fx-background-color: " + darkBackground + "; -fx-text-fill: " + darkText + ";";

        // Apply dark mode styles to the main pane
        ((Pane) nameField.getScene().getRoot()).setStyle(darkModeStyle);

        // Apply dark mode styles to specific elements
        // Example: nameField.setStyle(darkModeStyle);
        // Add similar lines for other UI elements as needed

        // Set text color for specific labels
        nameLabel.setStyle("-fx-text-fill: " + darkText + ";");
        statusLabel.setStyle("-fx-text-fill: " + darkText + ";");
        displayLabel.setStyle("-fx-text-fill: " + darkText + ";");

        // Set text color for Friends List label (replace "friendsListLabel" with your actual ID)
        friendsLabel.setStyle("-fx-text-fill: " + darkText + ";");

        // Set text color for individual friend labels in Friends Area
        for (Node friendNode : friendsArea.getChildren()) {
            if (friendNode instanceof Label) {
                ((Label) friendNode).setStyle("-fx-text-fill: " + darkText + ";");
            }
        }

        // Output progress
        displayLabel.setText("Dark Mode Activated");
    }

    @FXML
    public void onClickLightModeButton() {
        // Set light mode colors
        String lightBackground = "#CCCCCC";  // Light grey background color
        String lightText = "#000000";        // Black text color

        // Set light mode styles
        String lightModeStyle = "-fx-background-color: " + lightBackground + "; -fx-text-fill: " + lightText + ";";

        // Apply light mode styles to the main pane
        ((Pane) nameField.getScene().getRoot()).setStyle(lightModeStyle);

        // Apply light mode styles to specific elements
        // Example: nameField.setStyle(lightModeStyle);
        // Add similar lines for other UI elements as needed

        // Set text color for specific labels
        nameLabel.setStyle("-fx-text-fill: " + lightText + ";");
        statusLabel.setStyle("-fx-text-fill: " + lightText + ";");
        displayLabel.setStyle("-fx-text-fill: " + lightText + ";");

        // Set text color for Friends List label (replace "friendsListLabel" with your actual ID)
        friendsLabel.setStyle("-fx-text-fill: " + lightText + ";");

        // Set text color for individual friend labels in Friends Area
        for (Node friendNode : friendsArea.getChildren()) {
            if (friendNode instanceof Label) {
                ((Label) friendNode).setStyle("-fx-text-fill: " + lightText + ";");
            }
        }

        // Output progress
        displayLabel.setText("Light Mode Activated");
    }
    @FXML
    public void onSingleButton(){

    }
    @FXML
    public void onMarriedButton(){

    }
}