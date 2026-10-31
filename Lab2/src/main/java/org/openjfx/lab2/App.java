package org.openjfx.lab2;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.openjfx.lab2.controller.WelcomeController;

/**
 * Entry point: opens the login window and switches it to the welcome screen
 * after a successful login. Both screens use the same window (Stage).
 */
public class App extends Application {

    private static Stage stage;
    private static UsersRepository repository;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        repository = new UsersRepository();

        stage.setResizable(false);
        // closing the window with X ends the whole program
        stage.setOnCloseRequest(event -> Platform.exit());
        showLogin();
        stage.show();
    }

    public static UsersRepository getRepository() {
        return repository;
    }

    /**
     * Shows the login screen (at startup and after logging out).
     */
    public static void showLogin() throws IOException {
        stage.setTitle("Users Login");
        stage.setScene(new Scene(loadFxml("login.fxml").load()));
    }

    /**
     * Replaces the login screen with the welcome screen (loads a new fxml).
     */
    public static void showWelcome(User user) throws IOException {
        FXMLLoader loader = loadFxml("welcome.fxml");
        Parent root = loader.load();
        WelcomeController controller = loader.getController();
        controller.setUser(user);

        stage.setTitle("Welcome");
        stage.setScene(new Scene(root));
    }

    private static FXMLLoader loadFxml(String name) {
        return new FXMLLoader(App.class.getResource(name));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
