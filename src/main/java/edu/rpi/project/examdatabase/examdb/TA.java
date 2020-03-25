package edu.rpi.project.examdatabase.examdb;

import java.util.Map;

/**
 * TA class holds all attributes of a TA user and defines
 * the TA level operation.
 */
public final class TA extends User{
    protected String password;

    public TA(String username, String password, String firstName, String lastName, long id, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.email = email;
    }


    /**
     * Save a question to the database.
     * @param question
     * @return
     */
    public int addQuestion(Question question) {
        throw new RuntimeException("deleteQuestion() not implemented yet");
    }

    /* Public getters */
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
