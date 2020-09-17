package edu.rpi.project.examdatabase.examdb.Objects.User;

import edu.rpi.project.examdatabase.examdb.Exceptions.PermissionDeniedException;
import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;
import org.jetbrains.annotations.NotNull;

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
        this.userType = 1;
        this.password = password;
    }

    public Student(@NotNull Student u) {
        this.setTime();
        this.username = u.username;
        this.firstName = u.firstName;
        this.lastName = u.lastName;
        this.email = u.email;
        this.userType = u.userType;
        this.password = u.password;
    }

    public Student clone() {
        return new Student(this);
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
