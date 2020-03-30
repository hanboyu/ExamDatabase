package edu.rpi.project.examdatabase.examdb.Exceptions;

public class PermissionDeniedException extends Exception {
    public PermissionDeniedException(String userType, String username, String operationName) {
        super(userType + " '" + username + "' does NOT have the permission to " + operationName);
    }
}
