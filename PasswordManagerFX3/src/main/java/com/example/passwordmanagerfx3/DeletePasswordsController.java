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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeletePasswordsController implements Initializable {

    @FXML
    private ListView<String> websiteListView;
    @FXML
    private Label currentPasswordLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private ArrayList<String> websites;
    @FXML
    private ArrayList<String> passwords;
    private String currentWebsite;
    private String passwordToBeDeleted;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String css = this.getClass().getResource("style.css").toExternalForm();

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(css);
        stage.show();
    }

    public void deleteButton(ActionEvent event) throws IOException {
        FileManager fileManager = new FileManager();
        fileManager.readCurrentUserFile();
        fileManager.readPasswordFile();
        fileManager.readWebsiteFile();

        if(!(currentWebsite == null)) {
            errorLabel.setText("");

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Password");
            alert.setHeaderText("You are about to delete " + currentWebsite + " Password.");
            alert.setContentText("Do you want to continue?");

            if(alert.showAndWait().get() == ButtonType.OK) {
                int websitePos = websites.indexOf(currentWebsite);
                passwordToBeDeleted = passwords.get(websitePos);
                passwords.remove(websitePos);
                websites.remove(websitePos);
                fileManager.deletePassword(passwordToBeDeleted);
                fileManager.deleteWebsite(currentWebsite);

                switchToMenu(event);
            }
        } else {
            errorLabel.setText("Please select a website to delete a password");
        }
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
                currentPasswordLabel.setText(passwords.get(websitePos));
            }
        });
    }
}
