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
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GeneratePasswordsController implements Initializable {

    @FXML
    private ToggleButton specialToggle;
    @FXML
    private ToggleButton numbersToggle;
    @FXML
    private Label errorLabel;
    @FXML
    private ToggleButton lowercaseToggle;
    @FXML
    private ToggleButton uppercaseToggle;
    @FXML
    private Label messageLabel;
    @FXML
    private Label lengthLabel;
    @FXML
    private Label passwordEntropy;
    @FXML
    private Slider lengthSlider;
    @FXML
    private Button copyPasswordButton;
    private int currentLength;
    private boolean isUpperCase;
    private boolean isLowerCase;
    private boolean isSpecials;
    private boolean isNumbers;
    private String currentPassword;
    private Double entropy;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String css = this.getClass().getResource("style.css").toExternalForm();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lengthSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                currentLength = (int) lengthSlider.getValue();
                lengthLabel.setText(Integer.toString(currentLength));
            }
        });
    }

    public void savePassword(ActionEvent event) throws IOException {
        if(!(currentPassword == null || currentPassword.equals(""))) {
            errorLabel.setText("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("savePasswordConfirmation.fxml"));
            root = loader.load();

            SavePasswordConfirmationController savePasswordConfirmation = loader.getController();
            savePasswordConfirmation.getCurrentPassword(currentPassword);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(css);
            stage.show();
        } else {
            errorLabel.setText("Please generate a password first.");
        }
    }

    @FXML
    private void copyPassword() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(currentPassword);
        clipboard.setContent(content);
        messageLabel.setVisible(true);
        messageLabel.setText("Password Saved To Clipboard");
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
        visiblePause.setOnFinished(event -> messageLabel.setVisible(false));
        visiblePause.play();
    }

    @FXML
    private void generatePassword() {
        if(isNumbers || isSpecials || isLowerCase || isUpperCase) {
            errorLabel.setText("");
            PasswordGenerator passwordGenerator = new PasswordGenerator();
            currentPassword = passwordGenerator.generatePassword(currentLength,isUpperCase,isLowerCase,isNumbers,isSpecials);
            copyPasswordButton.setText(currentPassword);
            calculateEntropy();
        } else {
            errorLabel.setText("Please select at least one toggle button.");
        }

    }

    private void calculateEntropy() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        entropy = passwordGenerator.calculateEntropy(currentPassword);
        int entropyInt = (int) Math.round(entropy);

        String passwordStrength = "";

        if (entropyInt < 28) {
            passwordStrength = "Very Weak";
        } else if (entropyInt >= 28 && entropyInt <= 35) {
            passwordStrength = "Weak";
        } else if (entropyInt >= 36 && entropyInt <= 59) {
            passwordStrength = "Fairly Secure";
        } else if (entropyInt >= 60 && entropyInt <= 127) {
            passwordStrength = "Strong";
        } else if (entropyInt > 128) {
            passwordStrength = "Very Strong";
        }

        passwordEntropy.setText(entropyInt + " Bits Password is: " + passwordStrength);
    }

    @FXML
    private void toggleButton() {
        isUpperCase = uppercaseToggle.isSelected();
        isLowerCase = lowercaseToggle.isSelected();
        isSpecials = specialToggle.isSelected();
        isNumbers = numbersToggle.isSelected();
    }
    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(css);
        stage.show();
    }

}
