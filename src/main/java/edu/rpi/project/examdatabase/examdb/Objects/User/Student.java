package edu.rpi.project.examdatabase.examdb.Objects.User;

import edu.rpi.project.examdatabase.examdb.Exceptions.PermissionDeniedException;
import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;

/**
 * Student does not have the permission to do the following operation:
 * - Add question
 * - edit question
 * - delete question
 * - add user
 * - delete user
 */
public final class Student extends User {

    public Student(String username, String firstName, String lastName, String email, String password) {
        this.setTime();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.userType = "Student";
        this.password = password;
    }

    public Student clone() {
        return new Student(username, firstName, lastName, email, password);
    }

    @Override
    public void editQuestion(Question editedQuestion) throws PermissionDeniedException {
        throw new PermissionDeniedException(userType, username, "EDIT QUESTION");
    }

    @Override
    public void addQuestion(Question newQuestion) throws PermissionDeniedException {
        throw new PermissionDeniedException(userType, username, "ADD QUESTION");
    }

    @Override
    public void deleteQuestion(String questionID) throws PermissionDeniedException {
        throw new PermissionDeniedException(userType, username, "DELETE QUESTION");
    }

    @Override
    public void addUser(User newUser) throws PermissionDeniedException {
        throw new PermissionDeniedException(userType, username, "ADD USER");
    }

    @Override
    public void deleteUser(String username) throws PermissionDeniedException {
        throw new PermissionDeniedException(userType, username, "DELETE USER");
    }

}
