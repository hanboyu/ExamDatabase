package edu.rpi.project.examdatabase.examdb.Services;

import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;
import edu.rpi.project.examdatabase.examdb.Objects.User.User;

import java.util.List;

/**
 * EditQuestionService wraps all services that needs the access to write questions into
 * the database.
 */
public class EditQuestionService {
    /**
     * Saves the question instance into the database
     * @param question question to be saved; cannot be null
     * @param user user who saves the question; has to be instructor
     * @throws PermissionDeniedException when the user is not instructor
     */
    public static void editQuestion(Question question, User user) {
        throw new RuntimeException("editQuestion() not implemented yet");
    }

    /**
     * Delete a question from the database
     * @param qcode qcode that corresponds to the question to be deleted
     * @param user user who deletes the question; has to be instructor
     * @throws PermissionDeniedException when the user is not instructor
     */
    public static void deleteQuestion(String qcode, User user) {
        throw new RuntimeException("deleteQuestion() not implemented yet");
    }

    /**
     * Add a question to the database
     * @param question question to be added; cannot be null
     * @param user user who adds the question; has to be instructor or TA
     * @throws PermissionDeniedException when the user is not instructor or TA
     */
    public static void addQuestion(Question question, User user) {
        throw new RuntimeException("addQuestion() not implemented yet");
    }

    /**
     * Add multiple questions to the database
     * @param questions
     * @param user
     */
    public static void bulkUpload(List<Question> questions, User user) {
        throw new RuntimeException("bulkUpload() not implemented yet");
    }
}
