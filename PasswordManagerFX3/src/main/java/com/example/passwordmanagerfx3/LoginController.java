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

public class LoginController {

    @FXML
    private TextField textField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    private String[] userName = {"Lukas"};
    private String[] password = {""};
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String givenUsername;
    private String css = this.getClass().getResource("style.css").toExternalForm();



    public void login(ActionEvent event) throws IOException {

        givenUsername = textField.getText();
        String givenPassword = passwordField.getText();

        for(int i=0;i<(userName.length);i++) {
            if(givenUsername.equals(userName[i]) && givenPassword.equals(password[i])) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
                root = loader.load();

                MenuController menuController = loader.getController();
                menuController.welcomeMessage(givenUsername);

                FileManager fileManager = new FileManager();
                fileManager.setCurrentUser(givenUsername);
                fileManager.writeToUserFile();

                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                scene.getStylesheets().add(css);
                stage.show();
            } else {
                errorLabel.setText("Username and password incorrect");
            }
        }
    }




}
