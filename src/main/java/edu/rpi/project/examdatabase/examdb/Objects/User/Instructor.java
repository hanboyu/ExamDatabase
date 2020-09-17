package edu.rpi.project.examdatabase.examdb.Objects.User;

import org.jetbrains.annotations.NotNull;

/**
 * Instructor have the permission to access all functions in User class.
 */
public final class Instructor extends User {
    public Instructor(String username, String firstName, String lastName, String email, String password) {
        this.setTime();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = 3;
        this.password = password;
    }

    public Instructor(@NotNull Instructor u) {
        this.setTime();
        this.username = u.username;
        this.firstName = u.firstName;
        this.lastName = u.lastName;
        this.email = u.email;
        this.userType = u.userType;
        this.password = u.password;
    }

    public Instructor clone() {
        return new Instructor(this);
    }
}
