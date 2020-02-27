package edu.rpi.project.examdatabase.examdb;

import java.util.List;

/**
 * All service related to getting questions from database will be handled by
 * this class.
 */
public class ReadQuestionService {

    /**
     * This function requests a single question form the database by the
     * question's id
     * @param user user who sends the request
     * @param id the unique id for each question
     * @throws InvalidQuestionId when the id is not found in the database
     * @return the question
     */
    public static Question GetQuestionById(User user, String id){
        //TODO - implement GetQuestionById()
        throw new RuntimeException("GetQuestionById is not implemented yet");
    }

    /**
     * The function requests for a list of questions from the database by the
     * question's tags
     * @param user user who sends the request
     * @param tags a non-empty list of strings. Each element in the list
     *             represents a tag
     * @throws EmptyTagList when given an empty or null list of tags.
     * @return a list of questions that satisfy the given tags. An empty list
     * indicates no question satisfies the given condition.
     * Return 100 questions maximum.
     */
    public static List<Question> GetQuestionsByTags(User user, List<String> tags){
        //TODO - implement GetQuestionsByTags()
        throw new RuntimeException("GetQuestionsByTags is not implemented yet");
    }

    /**
     * The function requests for a list of questions form the database that
     * contain or related ot the given key word.
     * @param user user who sends the request
     * @param key_word a non-empty word to search
     * @throws EmptyKeyWord when given an empty or null string.
     * @return a list of questions that relates to the given key word. An empty
     * list indicates no question satisfies the given condition.
     * Return 100 questions maximum.
     */
    public static List<Question> GetQuestionsByKeyWord(User user, String key_word){
        //TODO - implement GetQuestionsByKeyWord()
        throw new RuntimeException("GetQuestionsByKeyWord is not implemented yet");
    }
}
