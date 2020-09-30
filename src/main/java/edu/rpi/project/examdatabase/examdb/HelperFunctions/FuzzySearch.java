package edu.rpi.project.examdatabase.examdb.HelperFunctions;

import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;

/**
 * StringComparison will contain all methods necessary to compare
 *  a single String with a Question and convey a degree of similarity
 *  between the two.
 */
public class FuzzySearch {
    /**
     * This function calculates the degree of similarity between the
     *  input keyword and a given Question
     *
     * @param keyword The string to compare with given question
     * @param question The question to compare keyword with
     * @return A value between 0 and 1 inclusive which indicates the degree
     *  of similarity of the arguments, with 1 being the most similar
     */
    public static Double Similarity( String keyword, Question question ) {
        throw new RuntimeException( "Similarity not yet implemented" );
    }
}
