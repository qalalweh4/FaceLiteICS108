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
    private Pane themeIcon;
    @FXML
    private Label relationStatusArea;
    @FXML
    private Pane leftPane;
    @FXML
    private Pane topPane;
    @FXML
    private Button singleButton;
    @FXML
    private Button marriedButton;
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
    private String resourceAddress = "file:src/main/resources/pictures/";
    private final int imageScale = 180;
    @FXML
    protected void onAddClick() {
        String accountName = nameField.getText();
        if(accountName.isEmpty())
            displayLabel.setText("Account cannot be without words!");
        // Check if the account already exists
        else if (users.containsKey(accountName)) {
            displayLabel.setText("Account already exists!");
        } else {
            clearStatus();
            relationStatusArea.setText(" ");
            // Create a new UserClass instance
            UserClass newUser = new UserClass(accountName);
            // Add the user to the users map
            users.put(accountName, newUser);

            // Update the UI
            nameLabel.setText( accountName);
            refreshImageView(newUser);
            displayLabel.setText("New Profile Created!!");
            updateFriendsArea(newUser);
        }
    }
    @FXML
    protected void onDeleteClick() {
        String accountName = nameField.getText();
        if(accountName.isEmpty())
            displayLabel.setText("Account cannot be deleted without words!");
        // Check if the account exists
        else if (users.containsKey(accountName)) {

            UserClass newUser = new UserClass(accountName);
            // Update the UI
            removingRelationStatus(newUser);
            removeImageDeleteAcc(newUser);
            nameLabel.setText("");
            displayLabel.setText("Profile of " + accountName+ " is deleted ");
            clearStatus();
            removeImageDeleteAcc(newUser);
            // Remove the account from the users map
            users.remove(accountName);

        } else {
            displayLabel.setText("Account not found!");
        }
    }
    @FXML
    protected void onLookUpClick() {
        String accountName = nameField.getText();

        if(accountName.isEmpty())
            displayLabel.setText("Account cannot be lookuped without name!");
        // Check if the account exists
        else if (users.containsKey(accountName)) {

            UserClass user = users.get(accountName);
            updateRelationStatusArea(user);

            // Update the UI with detailed information about the account
            nameLabel.setText(user.getName());
            statusLabel.setText("Status: " + user.getStatus()); // Set status here
            refreshImageView(user);
            updateFriendsArea(user);
            displayLabel.setText("Account found!");
        } else {
            // Update the UI to indicate that the account was not found
            refreshImageView();
            nameLabel.setText(" ");
            statusLabel.setText(""); // Clear the statusLabel when the account is not found
            displayLabel.setText("A profile with the name "+ accountName+ " does not exist");
        }
    }

    @FXML
    protected void onChangeStatusClick() {
        // Get the new status from the statusField
        String newStatus = statusField.getText();

        // Get the currently displayed user and update the status
        String accountName = nameLabel.getText();

        if (accountName.isEmpty()) {
            // No account is displayed, show an error message
            displayLabel.setText("You can't change status when there's no account");
        } else {
            UserClass user = users.get(accountName);
            user.setStatus(newStatus);

            // Update the statusLabel
            statusLabel.setText("Status: " + newStatus);

            // Output progress
            displayLabel.setText("Status Updated");
        }
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
    }public void refreshImageView(UserClass user){
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
    public void removeImageDeleteAcc(UserClass user){
        imageArea.getChildren().clear();
    }
    @FXML
    protected void onAddFriendClick() {
        String accountName = nameLabel.getText();
        UserClass user1 = users.get(accountName);
        String friendName = friendField.getText();
        if(accountName.isEmpty())
            displayLabel.setText("No account with no a real name to be added!");
        // Check if the user is trying to add themselves
        else if (accountName.equals(friendName)) {
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
    public void onRemoveFriend() {
        String accountName = nameLabel.getText();
        UserClass user1 = users.get(accountName);
        String friendName = deleteFriendLabel.getText();

        // Check if the friend account exists
        UserClass user2 = users.get(friendName);
        if(accountName.isEmpty())
            displayLabel.setText("Type a validated user name to remove it!");
        else if (user2 != null) {
            // Remove the specified friend from each other's friends list
            user1.getFriendsList().remove(user2);
            user2.getFriendsList().remove(user1);

            // Update the UI
            updateFriendsArea(user1);
            updateFriendsArea(user2);

            // Output progress
            displayLabel.setText("Friend '" + friendName + "' removed successfully");
        } else {
            displayLabel.setText("Friend account '" + friendName + "' not found!");
        }
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
            displayLabel.setText(user.getName() + " created a profile");
        }
    }
    @FXML
    public void onClickDarkmodeButton() {
        // Set dark mode colors
        String darkBackground = "#000000";  // Dark background color
        String darkText = "#FFFFFF";        // White text color

        // Set dark mode styles
        String darkModeStyle = "-fx-background-color: " + darkBackground + "; -fx-text-fill: " + darkText + ";";

        // Apply dark mode styles to the panes
        ((Pane) nameField.getScene().getRoot()).setStyle(darkModeStyle);

        // Apply dark mode styles to specific elements

        // Set text color for specific labels
        relationStatusArea.setStyle("-fx-text-fill: " + darkText + ";");
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
        relationStatusArea.setStyle("-fx-text-fill: " + lightText + ";");
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
    public void onSingleButton() {
        String accountName = nameLabel.getText();
        UserClass user = users.get(accountName);
        if(accountName.isEmpty())
            displayLabel.setText("No account is inputed to choose a relationship status");
        // Toggle relationship status
        else if ("Single".equals(user.getRelationshipStatus())) {
            // Reset status if it was already Single
            user.setRelationshipStatus("");
            displayLabel.setText("Relationship status reset");
        } else {
            // Update user's relationship status to Single
            user.setRelationshipStatus("Single");
            displayLabel.setText("Relationship status set to Single");
        }

        // Update the relationStatusArea
        updateRelationStatusArea(user);
    }

    @FXML
    public void onMarriedButton() {
        String accountName = nameLabel.getText();
        UserClass user = users.get(accountName);
        if(accountName.isEmpty())
            displayLabel.setText("No account is inputed to choose a relationship status");
        // Toggle relationship status
        else if ("Married".equals(user.getRelationshipStatus())) {
            // Reset status if it was already Married
            user.setRelationshipStatus("");
            displayLabel.setText("Relationship status reset");
        } else {
            // Update user's relationship status to Married
            user.setRelationshipStatus("Married");
            displayLabel.setText("Relationship status set to Married");
        }

        // Update the relationStatusArea
        updateRelationStatusArea(user);
    }


    // Add this method to update the relationStatusArea
    private void updateRelationStatusArea(UserClass user) {
        String relationshipStatus = user.getRelationshipStatus();
        relationStatusArea.setText("Relationship Status: " + relationshipStatus);
    }
    private void removingRelationStatus(UserClass user){
        relationStatusArea.setText("");
    }



}