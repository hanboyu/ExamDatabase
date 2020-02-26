package edu.rpi.project.examdatabase.examdb;

import java.util.List;

/**
 * A <b>Question</b> object represents one multiple choice question with single
 * answer. The Question Object is immutable. The object is able to store the
 * question, and verify answers.
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
 *  Each question has an unique serial code, and each question is unique from
 *  other questions. If two questions has the same serial code, then the two
 *  questions are equal to each other.
 *
 *  Representation Invariant:
 *  All field can not be null.
 *  All field except tags can not be empty.
 *  Choices must contains at least two options.
 *  Answer can only be one of the choices.
 */
public final class Question implements Comparable<Question> {
    private final String serial_code;
    private final List<String> tags;
    private final String permission;
    private final String question_body;
    private final List<String> choices;
    private final String Answer;

    //Constructors
    public Question(){
        throw new RuntimeException("Question constructor is not implemented yet");
    }

    //Observers
    /**
     * Question serial code observer
     * @return Question serial code
     */
    public String getSerialCode() {
        return serial_code;
    }

    /**
     * Question tags observer
     * @return a list of question tags
     */
    public List<String> getTags(){
        return tags;
    }

    /**
     * Question permission observer
     * @return a string that represent the permission type of the question
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Question body observer
     * @return a string of the question body
     */
    public String getQuestionBody() {
        return question_body;
    }

    /**
     * Question choices observer
     * @return a list of choices.
     */
    public List<String> getChoices(){
        return choices;
    }

    /**
     * Question answer observer
     * @return the answer of the question
     */
    public String getAnswer(){
        return Answer;
    }

    /**
     * Verify if the given answer is the real answer
     * @param given_answer the answer choose by the user
     * @return true if the given answer matches with the answer, vice versa.
     */
    public boolean VerifyAnswer(String given_answer){
        //TODO - implement VerifyAnswer
        throw new RuntimeException("VerifyAnswer() is not implemented");
    }

    /**
     * Compare if two question is equal. Refer to the class documentation for
     * the definition of equality.
     * @param obj the other object to compare
     * @return true if the two object is equal, and vice versa.
     */
    @Override
    public boolean equals(Object obj){
        //TODO - implement equals for Question
        throw new RuntimeException("equals() is not implemented");
    }

    /**
     * Generate a unique hash value for each question
     * @return Unique hash value for each question
     */
    @Override
    public int hashCode(){
        return serial_code.hashCode();
    }

    /**
     * Make a copy
     * @return A exact same question object.
     * @throws CloneNotSupportedException
     */
    @Override
    protected Question clone() throws CloneNotSupportedException{
        //TODO - implement clone
        throw new RuntimeException("clone() is implemented");
    }

    /**
     * Compare the serial code of the two question.
     */
    @Override
    public int compareTo(Question question) {
        //TODO - implement compareTo
        throw new RuntimeException("compareTo() is not implemented");
    }
}
