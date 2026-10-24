import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Reads users from users.txt, keeps the valid ones in an ArrayList,
 * prints every invalid line with its error and finally prints the
 * valid users sorted by username.
 */
public class UsersApp {

    private static final String INPUT_FILE = "users.txt";

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();

        try (Scanner reader = new Scanner(openInput(args))) {
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
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return;
        }

        Collections.sort(users, (u1, u2) -> u1.getName().compareTo(u2.getName()));

        for (User user : users) {
            System.out.println(user);
        }
    }

    // input file from the command line, else users.txt, else standard input (redirection)
    private static InputStream openInput(String[] args) throws FileNotFoundException {
        if (args.length > 0) {
            return new FileInputStream(args[0]);
        }
        File file = new File(INPUT_FILE);
        if (file.exists()) {
            return new FileInputStream(file);
        }
        return System.in;
    }
}
