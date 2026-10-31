# Lab 2 - JavaFX Login

## Run in IntelliJ
1. File -> Open -> choose this folder (the one with pom.xml) -> Open as Project.
2. Wait for Maven to download the dependencies (bottom-right progress bar).
3. Open src/main/java/org/openjfx/lab2/App.java and click the green Run arrow next to main.

Alternative: Maven tool window (right side) -> lab2 -> Plugins -> javafx -> javafx:run.

## Files
- App.java - starts the program, switches from the login screen to the welcome screen.
- User.java - user with validation (from lab 1).
- UsersRepository.java - reads users.txt and keeps the valid users.
- controller/LoginController.java - checks username and password.
- controller/WelcomeController.java - shows the welcome message.
- resources/.../login.fxml, welcome.fxml - the screens (open with Scene Builder).
- resources/.../style.css - colors, card layout and button styles for both screens.
- The welcome screen has a "Log out" button that returns to the login screen.

users.txt is read from the project folder. If it is not there, the copy in resources is used.
