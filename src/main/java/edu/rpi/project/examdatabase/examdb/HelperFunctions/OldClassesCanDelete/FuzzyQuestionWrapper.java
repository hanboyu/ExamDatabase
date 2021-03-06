package edu.rpi.project.examdatabase.examdb.HelperFunctions.OldClassesCanDelete;

import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;

public class FuzzyQuestionWrapper {
    /**
     * This function calculates the degree of similarity between the
     *  input keyword and a given Question object
     *
     * @param keyword The string to compare with given question
     * @param question The question to compare keyword with
     * @return A value between 0 and 1 inclusive which indicates the degree
     *  of similarity of the arguments, with 1 being the most similar
     */
    public static double similarity( String keyword, Question question ) {
        return FuzzySearch.similarity( keyword, question.getQuestionBody(), new WeightAverage() );
    }
}
