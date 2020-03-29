package edu.rpi.project.examdatabase.examdb;

import javax.validation.constraints.NotNull;

/**
 * Instructor class holds all attributes of a instructor user and defines
 * the instructor level operation.
 */
public class Instructor extends User {
    public Instructor(String username, String firstName, String lastName, String email) {
        this.setTime();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
