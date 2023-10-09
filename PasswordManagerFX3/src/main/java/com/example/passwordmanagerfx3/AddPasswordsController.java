package com.example.passwordmanagerfx3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPasswordsController {

    @FXML
    private TextField passwordField;
    @FXML
    private TextField websiteTextField;
    @FXML
    private Label errorLabel;
    private String website;
    private String password;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String css = this.getClass().getResource("style.css").toExternalForm();

    public void addGeneratedPassword(String securePassword) {
        passwordField.setText(securePassword);
    }

    public void addPassword() {
        FileManager fileManager = new FileManager();

        website = websiteTextField.getText();
        password = passwordField.getText();

        if(checkTextFields()) {

            fileManager.setCurrentWebsite(website);
            fileManager.setCurrentPassword(password);

            fileManager.readCurrentUserFile();

            fileManager.writeToWebsiteFile();
            fileManager.writeToPasswordFile();

            websiteTextField.clear();
            passwordField.clear();
            errorLabel.setText("Successfully added password.");
        }
    }

    public boolean checkTextFields() {
        if(website.equals("") && password.equals("")) {
            errorLabel.setText("Please enter a website and password.");
            return false;
        } else if (website.equals("")) {
            errorLabel.setText("Please enter a website.");
            return false;
        } else if (password.equals("")) {
            errorLabel.setText("Please enter a password.");
            return false;
        }
        return true;
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(css);
        stage.show();
    }
}
