package org.openjfx.lab2.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.openjfx.lab2.App;
import org.openjfx.lab2.User;

/**
 * Controller for welcome.fxml: greets the user who logged in
 * and lets them log out back to the login screen.
 */
public class WelcomeController {

    @FXML
    private Label welcomeLabel;

    public void setUser(User user) {
        welcomeLabel.setText("Logged in as " + user.getName());
    }

    @FXML
    void onLogoutClick(ActionEvent event) throws IOException {
        App.showLogin();
    }
}
