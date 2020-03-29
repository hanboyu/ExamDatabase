package edu.rpi.project.examdatabase.examdb;

import edu.rpi.project.examdatabase.examdb.Exceptions.PermissionDeniedException;

import java.util.Map;

/**
 * TA class holds all attributes of a TA user and defines
 * the TA level operation.
 */
public final class TA extends User {

    public TA(String username, String firstName, String lastName, String email) {
        this.setTime();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public void editQuestion(Question editedQuestion) throws PermissionDeniedException {
        throw new PermissionDeniedException("Student '" + this.username + "' is trying to EDIT QUESTION");
    }

    @Override
    public void addQuestion(Question newQuestion) throws PermissionDeniedException {
        throw new PermissionDeniedException("Student '" + this.username + "' is trying to ADD QUESTION");
    }

    @Override
    public void deleteQuestion(String questionID) throws PermissionDeniedException {
        throw new PermissionDeniedException("Student '" + this.username + "' is trying to DELETE QUESTION");
    }
}
