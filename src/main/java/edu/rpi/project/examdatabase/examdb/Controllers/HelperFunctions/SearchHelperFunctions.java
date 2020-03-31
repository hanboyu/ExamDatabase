package edu.rpi.project.examdatabase.examdb.Controllers.HelperFunctions;

public class SearchHelperFunctions {

    /**
     * This function maps the user types into a numerical value.
     *
     * @param userType given user type
     * @return a numerical value that representing the given user type
     */
    public static int getPermissionLevel(String userType) {
        if (userType.equals("Visitor")) {
            return 0;
        } else if (userType.equals("Student")) {
            return 1;
        } else if (userType.equals("TA")) {
            return 2;
        } else if (userType.equals("Instructor")) {
            return 3;
        }
        return 0;
    }
}
