package edu.rpi.project.examdatabase.examdb;

import javax.validation.constraints.NotNull;

/**
 * Instructor have the permission to access all functions in User class.
 */
public final class Instructor extends User {
    public Instructor(String username, String firstName, String lastName, String email) {
        this.setTime();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = "Instructor";
    }
}
