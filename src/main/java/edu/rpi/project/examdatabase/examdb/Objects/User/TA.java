package edu.rpi.project.examdatabase.examdb.Objects.User;

import edu.rpi.project.examdatabase.examdb.Exceptions.PermissionDeniedException;

/**
 * TA does not have permission to do the following operation:
 * - Add user
 * - Delete user
 */
public final class TA extends User {

    public TA(String username, String firstName, String lastName, String email, String password) {
        this.setTime();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = 2;
        this.password = password;
    }

    public TA clone() {
        return new TA(username, firstName, lastName, email, password);
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
