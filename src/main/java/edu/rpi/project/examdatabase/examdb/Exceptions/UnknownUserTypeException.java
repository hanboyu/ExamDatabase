package edu.rpi.project.examdatabase.examdb.Exceptions;

public class UnknownUserTypeException extends Exception {
    public UnknownUserTypeException(int givenUserType) {
        super("Unknown user type '" + givenUserType + "'");
    }
}
