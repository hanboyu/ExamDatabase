package edu.rpi.project.examdatabase.examdb;

import javax.validation.constraints.NotNull;

/**
 * Instructor class holds all attributes of a instructor user and defines
 * the instructor level operation.
 */
public class Instructor extends TA {

    public Instructor(String username, String password, String firstName, String lastName, long id, String email) {
        super(username, password, firstName, lastName, id, email);
    }

    /**
     * Save the new question instance into the database.
     * @param question the question that is to be saved; cannot be null
     * @return
     */
    public int editQuestion(@NotNull Question question) {
        throw new RuntimeException("editQuestion() not implemented yet");
    }

    /**
     * Delete the question with a specified qcode.
     * @param qcode qcode of the question that is to be deleted; cannot be null
     * @throws InvalidQuestionId if qcode doesn't exist
     * @return
     */
    public int deleteQuestion(@NotNull String qcode) {
        throw new RuntimeException("deleteQuestion() not implemented yet");
    }
}
