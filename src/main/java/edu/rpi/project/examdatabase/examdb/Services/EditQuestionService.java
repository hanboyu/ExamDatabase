package edu.rpi.project.examdatabase.examdb.Services;

import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;
import edu.rpi.project.examdatabase.examdb.Objects.TCPSocket;
import edu.rpi.project.examdatabase.examdb.Objects.User.User;

import java.io.IOException;
import java.util.List;

/**
 * EditQuestionService wraps all services that needs the access to write questions into
 * the database.
 */
public class EditQuestionService {

    private static final String DP_SERVER_ADDR = "localhost";
    private static final int DP_SERVER_PORT = 6969;

    /**
     * Saves the question instance into the database
     *
     * @param question question to be saved; cannot be null
     * @param user     user who saves the question; has to be instructor
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
     *
     * @param questions
     * @param user
     */
    public static boolean parseBulkUploadFile(User user, byte[] b_file) {
        if (user.getUserType() >= 0) { // permission is lowered for testing
            try {
                TCPSocket server_socket = new TCPSocket(DP_SERVER_ADDR, DP_SERVER_PORT);
                int file_length = b_file.length;
                String return_message = server_socket.send("bulk_upload," + file_length);
                assert return_message.equals("ACK");
                return_message = server_socket.send(b_file);
                return true;
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }
}
