package edu.rpi.project.examdatabase.examdb.Objects.User;

import edu.rpi.project.examdatabase.examdb.Exceptions.UnknownUserTypeException;
import edu.rpi.project.examdatabase.examdb.HelperFunctions.GetSystemUpTime;

/**
 * A User factory that is used to produce all types of user.
 * The UserFactory is a singleton object
 */

public class UserFactory {

    private static final UserFactory instance = new UserFactory();

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
     * @throws UnknownUserTypeException when the user type is not recognized
     */
    public User getUser(int userType, String username, String firstName, String lastName, String email, String password)
            throws UnknownUserTypeException {
        switch (userType) {
            case 0:
                return new Visitor(username, firstName, lastName, email, password);
            case 1:
                return new Student(username, firstName, lastName, email, password);
            case 2:
                return new TA(username, firstName, lastName, email, password);
            case 3:
                return new Instructor(username, firstName, lastName, email, password);
            default:
                throw new UnknownUserTypeException(userType);
        }
    }

    public User generateVisitor() {
        return new Visitor("visitor" + GetSystemUpTime.getSystemUptime(),
                "", "", "", "");
    }
}
