package edu.rpi.project.examdatabase.examdb.HelperFunctions;

import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;

/**
 * A SimilarityObject is essentially Pair< Question, Double >
 * Abstraction Function:
 *     It represents the similarity between the field question
 *     and an external Object from [ 0, 1 ] with 1 being identical
 * Representation Invariant:
 *      similarity must be between [0, 1]
 */
public class SimilarityObject implements Comparable<SimilarityObject> {
    private Question question;
    private Double similarity;

    public SimilarityObject(Question q, Double cv ) {
        question = q;
        similarity = cv;
    }

    public Question getQuestion() {
        return question;
    }

    public Double getSimilarity() {
        return similarity;
    }

    /**
     * This function compares two SimilarityObjects based on the field
     *  similarity. This was determined outside this class.
     * @param s The SimilarityObject to compare this to
     */
    @Override
    public int compareTo( SimilarityObject s ) {
        if( similarity > s.similarity ) {
            return -1;
        } else if( similarity.equals( s.similarity ) ) {
            return 0;
        } else {
            return 1;
        }
    }
}