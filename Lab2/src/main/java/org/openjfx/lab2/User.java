package org.openjfx.lab2;

import java.util.regex.Pattern;

/**
 * A system user with a validated username (email) and password.
 * An instance can only be created when both fields are valid.
 */
public class User {

    private static final int MAX_USERNAME_LENGTH = 50;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 12;

    // part1: letters, digits and . _ % + -
    // part2 (after @): starts with a letter/digit, then letters, digits, '-' or '.'
    // part3 (after the last '.'): at least two letters
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9][A-Za-z0-9.-]*\\.[A-Za-z]{2,}$");

    // allowed: letters, digits and the symbols on the number row: !@#$%^&*()_+
    private static final Pattern PASSWORD_ALLOWED = Pattern.compile("^[A-Za-z0-9!@#$%^&*()_+]+$");
    private static final Pattern HAS_LETTER = Pattern.compile("[A-Za-z]");
    private static final Pattern HAS_DIGIT = Pattern.compile("[0-9]");
    private static final Pattern HAS_SYMBOL = Pattern.compile("[!@#$%^&*()_+]");

    private final String name;
    private final String password;

    /**
     * Creates a new user.
     *
     * @throws IllegalArgumentException with a matching error message if the
     *                                  username or password is invalid
     */
    public User(String name, String password) {
        validateUsername(name);
        validatePassword(password);
        this.name = name;
        this.password = password;
    }

    private static void validateUsername(String name) {
        if (name == null || !EMAIL_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException("Please enter a valid Email as username");
        }
        if (name.length() > MAX_USERNAME_LENGTH) {
            throw new IllegalArgumentException("Username is too long, try something shorter");
        }
    }

    private static void validatePassword(String password) {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("Your password is too short, add more characters");
        }
        if (password.length() > MAX_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("Your password is too long, try a shorter one");
        }
        if (!PASSWORD_ALLOWED.matcher(password).matches()
                || !HAS_LETTER.matcher(password).find()
                || !HAS_DIGIT.matcher(password).find()
                || !HAS_SYMBOL.matcher(password).find()) {
            throw new IllegalArgumentException("Please enter a valid password");
        }
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return name + " " + password;
    }
}
