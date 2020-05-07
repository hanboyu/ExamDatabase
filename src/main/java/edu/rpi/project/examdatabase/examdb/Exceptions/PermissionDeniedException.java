package edu.rpi.project.examdatabase.examdb.Exceptions;

public class PermissionDeniedException extends Exception {
    public PermissionDeniedException(int userType, String username, String operationName) {
        super(ConvertUserType(userType) + " '" + username + "' does NOT have the permission to " + operationName);
    }

    private static String ConvertUserType(int t) {
        switch (t) {
            case 0:
                return "visitor";
            case 1:
                return "student";
            case 2:
                return "TA";
            case 3:
                return "instructor";
            default:
                return "unknown user type " + t;
        }
    }
}
