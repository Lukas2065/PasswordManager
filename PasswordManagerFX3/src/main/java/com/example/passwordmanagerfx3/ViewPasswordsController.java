package com.example.passwordmanagerfx3;

import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewPasswordsController implements Initializable {

    @FXML
    private ListView<String> websiteListView;
    @FXML
    private Button copyPasswordButton;
    @FXML
    private Label messageLabel;
    private ArrayList<String> websites;
    private ArrayList<String> passwords;
    private String currentWebsite;
    private String selectedPassword;
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

    @FXML
    private void copyPassword() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(selectedPassword);
        clipboard.setContent(content);
        messageLabel.setVisible(true);
        messageLabel.setText("Password Saved To Clipboard");
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
        visiblePause.setOnFinished(event -> messageLabel.setVisible(false));
        visiblePause.play();
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
                selectedPassword = passwords.get(websitePos);
                copyPasswordButton.setText(selectedPassword);
            }
        });
    }
}
