package edu.rpi.project.examdatabase.examdb;

import edu.rpi.project.examdatabase.examdb.Exceptions.PermissionDeniedException;

import java.util.List;

public final class Student extends User {

    public Student(String firstName, String lastName, String username, String email) {
        this.setTime();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
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

    @Override
    public void addUser(User newUser) throws PermissionDeniedException {
        throw new PermissionDeniedException("Student '" + this.username + "' is trying to ADD USER");
    }

}
