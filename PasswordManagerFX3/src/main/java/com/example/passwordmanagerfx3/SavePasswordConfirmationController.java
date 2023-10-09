package com.example.passwordmanagerfx3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SavePasswordConfirmationController {

    @FXML
    private Label passwordLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String css = this.getClass().getResource("style.css").toExternalForm();
    private String currentPassword;

    public void getCurrentPassword(String password) {
        currentPassword = password;
        passwordLabel.setText("Current Password: " + currentPassword);
    }
    @FXML
    private void createNewWebsite(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addPasswords.fxml"));
        root = loader.load();

        AddPasswordsController addPasswordsController = loader.getController();
        addPasswordsController.addGeneratedPassword(currentPassword);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(css);
        stage.show();
    }

    @FXML
    private void addToExisting(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editPasswords.fxml"));
        root = loader.load();

        EditPasswordsController editPasswordsController = loader.getController();
        editPasswordsController.addGeneratedPassword(currentPassword);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(css);
        stage.show();
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("generatePasswords.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(css);
        stage.show();
    }
}
