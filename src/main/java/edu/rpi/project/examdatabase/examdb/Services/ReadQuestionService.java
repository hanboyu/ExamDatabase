package edu.rpi.project.examdatabase.examdb.Services;

import edu.rpi.project.examdatabase.examdb.DataContainers.Database.dbaccess.Query;
import edu.rpi.project.examdatabase.examdb.DataContainers.Database.dbaccess.QueryQuestionFromDatabase;
import edu.rpi.project.examdatabase.examdb.Objects.QueryObject;
import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;
import edu.rpi.project.examdatabase.examdb.Objects.User.User;

import java.util.*;

/**
 * All service related to getting questions from database will be handled by
 * this class.
 */
public class ReadQuestionService {

    /**
     * This function requests a single question form the database by the
     * question's id
     *
     * @param user user who sends the request
     * @param id   the unique id for each question
     * @return the question
     */
    public static Question GetQuestionById(User user, String id) {
        // construct query arguments
        Map<String, String> query_argus = new TreeMap<>();
        query_argus.put("id", id);

        List<Question> res = QueryQuestion(user, query_argus);

        if (res.isEmpty()) {     // no result
            return null;
        }

        if (res.size() > 1) {    // more than one results
            // something is wrong. log this.
            return null;
        }

        return res.get(0);

    }

    /**
     * The function requests for a list of questions from the database by the
     * question's tags
     * @param user user who sends the request
     * @param tags a non-empty list of strings. Each element in the list
     *             represents a tag
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
     * @return a list of questions that relates to the given key word. An empty
     * list indicates no question satisfies the given condition.
     * Return 100 questions maximum.
     */
    public static List<Question> GetQuestionsByKeyWord(User user, String key_word) {
        // Get all the questions from the database
        Query query = new QueryQuestionFromDatabase();
        //List<Question> questions = query.doQuery(null);
        List<Question> questions = new LinkedList<>();

        // Create a PQ to help with ordering them from most --> least similar
        PriorityQueue<QueryObject> similarity_queue = new PriorityQueue<>();

        for( Question question : questions ) {

        }

        return null;

    }

    private static List<Question> QueryQuestion(User user, Map<String, String> query_argus) {
        // construct query strategies
        List<Query> query_strategies = new LinkedList<>();

        // !!! THE ORDER OF THE STRATEGIES IN THE LIST MATTERS !!!
        // THE STRATEGIES SHOULD BE ORDERED FROM THE FASTEST ACCESS TIME TO THE SLOWEST ACCESS TIME.
        query_strategies.add(new QueryQuestionFromDatabase());

        Iterator<Query> query_itr = query_strategies.iterator();
        List<Question> res;
        do {
            res = user.searchQuestion(query_itr.next(), query_argus);
        } while (query_itr.hasNext() && res.isEmpty());

        return res;
    }
}