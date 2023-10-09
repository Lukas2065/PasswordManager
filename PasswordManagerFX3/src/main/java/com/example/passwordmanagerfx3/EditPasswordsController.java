package com.example.passwordmanagerfx3;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditPasswordsController implements Initializable {

    @FXML
    private ListView<String> websiteListView;
    @FXML
    private Label selectedPassword;
    @FXML
    private Label errorLabel;
    @FXML
    private ArrayList<String> websites;
    @FXML
    private ArrayList<String> passwords;
    @FXML
    private TextField passwordTextField;
    private String currentWebsite;
    private String newPassword;
    private String oldPassword;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String css = this.getClass().getResource("style.css").toExternalForm();

    public void addGeneratedPassword(String securePassword) {
        passwordTextField.setText(securePassword);
    }
    public void saveNewPassword() {
        FileManager fileManager = new FileManager();
        fileManager.readCurrentUserFile();
        fileManager.readPasswordFile();
        fileManager.readWebsiteFile();

        if(!(currentWebsite == null)) {
            errorLabel.setText("");

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Change password");
            alert.setHeaderText("You are about to change " + currentWebsite + " Password.");
            alert.setContentText("Do you want to continue?");

            if(alert.showAndWait().get() == ButtonType.OK) {
                int websitePos = websites.indexOf(currentWebsite);
                oldPassword = passwords.get(websitePos);
                newPassword = passwordTextField.getText();
                passwords.set(websitePos, newPassword);
                fileManager.replacePassword(oldPassword, newPassword);
            }
        } else {
            errorLabel.setText("Please enter a password");
        }

    }

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(css);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        websites = new ArrayList<String>();
        FileManager fileManager = new FileManager();
        fileManager.readCurrentUserFile();
        fileManager.readWebsiteFile();
        fileManager.readPasswordFile();
        websites = fileManager.getWebsites();
        passwords = fileManager.getPasswords();

        websiteListView.getItems().addAll(websites);

        websiteListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentWebsite = websiteListView.getSelectionModel().getSelectedItem();
                int websitePos = websites.indexOf(currentWebsite);
                selectedPassword.setText(passwords.get(websitePos));
            }
        });
    }
}
