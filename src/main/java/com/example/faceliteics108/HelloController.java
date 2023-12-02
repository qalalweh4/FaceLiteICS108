package com.example.faceliteics108;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.util.HashMap;

public class HelloController  {
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

            displayLabel.setText("Account found!");
        } else {
            // Update the UI to indicate that the account was not found
            nameLabel.setText(" ");
            displayLabel.setText("A profile with the name "+ accountName+ " does not exist");
        }
    }
    @FXML
    protected void onChangeStatusClick(){

    }
    @FXML
    protected void onChangePictureClick(){

    }
    @FXML
    protected void onAddFriendClick() {
    }



}