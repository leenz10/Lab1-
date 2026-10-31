package org.openjfx.lab2.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.openjfx.lab2.App;
import org.openjfx.lab2.User;

/**
 * Controller for login.fxml: checks the entered username and password
 * against the users loaded from users.txt.
 */
public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label errorLabel;

    @FXML
    void onLoginClick(ActionEvent event) throws IOException {
        User user = App.getRepository().findUser(username.getText().trim(), password.getText());

        if (user == null) {
            // shown inside the window, not as a pop-up
            errorLabel.setText("user or password do not match");
            return;
        }

        errorLabel.setText("");
        App.showWelcome(user);
    }
}
