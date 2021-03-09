package edu.rpi.project.examdatabase.examdb.DataContainers.RAM;

import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;

import java.util.*;

/**
 * QuestionRAM class load every question from the database when initializing,
 * and stores it on the RAM for use.
 *
 * The QuestionRAM class is a singleton class.
 *
 * QuestionRAM use a vector to store all questions. Questions in the class can
 * be queried using the getAllQuestions method. This method takes advantage of
 * the elements method from vector which returns a enumeration. This is a read
 * only interface. This method provident data access from outside of the class.
 * This method is not an expansive function, so please call the getAllQuestion
 * method every time you need to access all the question to avoid concurrency
 * read and write issue.
 */
public class QuestionRAM {
    private Vector<Question> questions;
    private static QuestionRAM instance = null;

    /**
     * Constructor
     */
    private QuestionRAM(){
        questions = new Vector<>();
    }

    /**
     * Getters
     */

    public static QuestionRAM getInstance(){
        if (instance == null){
            instance = new QuestionRAM();
        }
        return instance;
    }

    /**
     * Query all the questions in the container.
     * @return an enumeration that can iterate through all data.
     */
    public Enumeration<Question> getAllQuestions(){
        return questions.elements();
    }
}
