package edu.rpi.project.examdatabase.examdb.Exceptions;

public class PermissionDeniedException extends Exception {
    public PermissionDeniedException(String errorMessage) {
        super("PERMISSION DENIED: " + errorMessage);
    }
}
