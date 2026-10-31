package org.openjfx.lab2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Loads the users file (same rules as lab 1) and keeps only the valid users.
 */
public class UsersRepository {

    private static final String USERS_FILE = "users.txt";

    private final ArrayList<User> users = new ArrayList<>();

    public UsersRepository() {
        load();
    }

    // users.txt from the working directory, else the copy packaged with the app
    private void load() {
        try (InputStream in = openUsersFile();
             Scanner reader = new Scanner(in)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }

                // username and password are separated by one or more spaces
                String[] parts = trimmed.split("\\s+", 2);
                String name = parts[0];
                String password = parts.length > 1 ? parts[1] : "";

                try {
                    users.add(new User(name, password));
                } catch (IllegalArgumentException e) {
                    System.err.println(line + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Could not read " + USERS_FILE + ": " + e.getMessage());
        }

        Collections.sort(users, (u1, u2) -> u1.getName().compareTo(u2.getName()));
    }

    private InputStream openUsersFile() throws IOException {
        File file = new File(USERS_FILE);
        if (file.exists()) {
            return new FileInputStream(file);
        }
        InputStream packaged = UsersRepository.class.getResourceAsStream(USERS_FILE);
        if (packaged == null) {
            throw new IOException(USERS_FILE + " not found");
        }
        return packaged;
    }

    /**
     * Returns the user with this exact username and password, or null if there is none.
     */
    public User findUser(String name, String password) {
        for (User user : users) {
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
