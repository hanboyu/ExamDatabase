package edu.rpi.project.examdatabase.examdb;

import java.util.List;

/**
 * A <b>Question</b> object represents one multiple choice question. The object
 * is able to store the question, edit the question, and verify answers.
 * The information contains by the Question object includes:
 *  - Question serial code
 *  - Tags
 *  - Question body
 *  - Choices
 *  - Answer
 *  - Answer explanation
 *  - Permission
 *  - Time added to database
 *
 *  Abstraction Function:
 *
 *  Representation Invariant:
 *  All field is not null or empty.
 *  Choices must contains at least two options
 *  Answer can only be one of the choices
 */
public class Question {
    private String serial_code;
    List<String> tags;
    String permission;
    String question_body;

    //Constructors
    public Question(){
        throw new RuntimeException("Question constructor is not implemented yet");
    }
    //Observers
    //Modifiers

}
