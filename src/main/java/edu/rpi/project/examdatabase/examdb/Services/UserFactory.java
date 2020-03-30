package edu.rpi.project.examdatabase.examdb.Services;

import edu.rpi.project.examdatabase.examdb.Exceptions.NullUserTypeException;
import edu.rpi.project.examdatabase.examdb.Exceptions.UnknownUserTypeException;
import edu.rpi.project.examdatabase.examdb.Instructor;
import edu.rpi.project.examdatabase.examdb.Student;
import edu.rpi.project.examdatabase.examdb.TA;
import edu.rpi.project.examdatabase.examdb.User;

/**
 * A User factory that is used to produce all types of user
 */

public class UserFactory {

    /**
     * Generate a User object from the given user information.
     *
     * @param userType  the type of the user
     * @param username  username of the user
     * @param firstName first name of the user
     * @param lastName  last name of the user
     * @param email     email of the user
     * @return a User instance that contains all the information about one user
     * @throws NullUserTypeException    when the userType is a null string
     * @throws UnknownUserTypeException when the user type is not Student, TA, or Instructor
     */
    public User getUser(String userType, String username, String firstName, String lastName, String email)
            throws NullUserTypeException, UnknownUserTypeException {
        if (userType == null) {
            throw new NullUserTypeException();
        } else if (userType.equals("Student")) {
            return new Student(username, firstName, lastName, email);
        } else if (userType.equals("TA")) {
            return new TA(username, firstName, lastName, email);
        } else if (userType.equals("Instructor")) {
            return new Instructor(username, firstName, lastName, email);
        }
        throw new UnknownUserTypeException(userType);
    }
}