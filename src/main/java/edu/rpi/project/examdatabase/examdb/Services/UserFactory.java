package edu.rpi.project.examdatabase.examdb.Services;

import edu.rpi.project.examdatabase.examdb.*;
import edu.rpi.project.examdatabase.examdb.Exceptions.NullUserTypeException;
import edu.rpi.project.examdatabase.examdb.Exceptions.UnknownUserTypeException;
import edu.rpi.project.examdatabase.examdb.HelperFunctions.GetSystemUpTime;

/**
 * A User factory that is used to produce all types of user.
 * The UserFactory is a singleton object
 */

public class UserFactory {

    private static UserFactory instance = new UserFactory();

    // Constructor
    private UserFactory() {
    }

    public static UserFactory getInstance() {
        return instance;
    }

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
     * @throws UnknownUserTypeException when the user type is not recognized
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
        } else if (userType.equals("Visitor")) {
            return new Visitor(username, firstName, lastName, email);
        }
        throw new UnknownUserTypeException(userType);
    }

    public User generateVisitor() {
        return new Visitor("visitor" + GetSystemUpTime.getSystemUptime(),
                "", "", "");
    }
}
