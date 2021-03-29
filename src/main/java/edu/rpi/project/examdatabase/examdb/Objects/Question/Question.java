package edu.rpi.project.examdatabase.examdb.Objects.Question;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.GetSystemUpTime;
import edu.rpi.project.examdatabase.examdb.Objects.Cachable;
import edu.rpi.project.examdatabase.examdb.Objects.QueryObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A <b>Question</b> object represents one multiple choice question with single
 * answer. The Question Object is immutable. The object is able to store the
 * question, and verify answers.
 * The information contains by the Question object includes:
 *  - Question serial code
 *  - Tags
 *  - Question body
 *  - Question body image(s)
 *  - Choices
 *  - Choice image (one image per choice)
 *  - Answer
 *  - Answer explanation
 *  - Permission
 *  - Time added to database
 *  Permission is represented by a integer with 0 being the lowest permission level.
 *      0 - Visitor
 *      1 - Student
 *      2 - TA
 *      3 - Instructor
 *
 *  Abstraction Function:
 *  Each question has an unique serial code, and each question is unique from
 *  other questions. If two questions has the same serial code, then the two
 *  questions are equal to each other. The equls() method only compares the
 *  serial code of the two questions. Other methods, isSameQuestion() and
 *  trueEqual(), are provided to check other fields in the Question object.
 *  Check method documentation for method detail.
 *
 *  Representation Invariant:
 *  All field can not be null.
 *  All field except tags can not be empty.
 *  Choices must contains at least two options.
 *  Answer can only be one of the choices.
 */
public final class Question implements Comparable<Question>, QueryObject, Cachable {
    protected final String serial_code;
    protected final List<String> tags;
    protected final String class_code;
    protected final int permission;
    protected final String question_body;
    protected final List<String> question_images_addr;
    protected final List<String> choices;
    protected final List<String> choice_images_addr;
    protected final String answer;

    protected long inTimestamp;

    //Constructors
    public Question(String serial_code, List<String> tags, String class_code,
                    int permission, String question_body,
                    List<String> question_images_addr, List<String> choices,
                    List<String> choice_images_addr, String answer) {
        this.serial_code = serial_code;
        this.tags = new ArrayList<>(tags);
        this.class_code = class_code;
        this.permission = permission;
        this.question_body = question_body;
        this.question_images_addr = new ArrayList<>(question_images_addr);
        this.choices = new ArrayList<>(choices);
        this.choice_images_addr = new ArrayList<>(choice_images_addr);
        this.answer = answer;
    }

    public Question(Question q) {
        this.serial_code = q.serial_code;
        this.tags = new ArrayList<>(q.tags);
        this.class_code = q.class_code;
        this.permission = q.permission;
        this.question_body = q.question_body;
        this.question_images_addr = new ArrayList<>(q.choice_images_addr);
        this.choices = new ArrayList<>(q.choices);
        this.choice_images_addr = new ArrayList<>(q.choice_images_addr);
        this.answer = q.answer;
        setTime();
    }

    //Observers

    /**
     * Question serial code observer
     *
     * @return Question serial code
     */
    public String getSerialCode() {
        return serial_code;
    }

    /**
     * Question tags observer
     *
     * @return a list of question tags
     */
    @Contract(" -> new")
    public @NotNull List<String> getTags() {
        return new ArrayList<>(tags);
    }

    /**
     * Question permission observer
     *
     * @return a string that represent the permission type of the question
     */
    public int getPermission() {
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
     * Question body images address observer
     * @return a list of image address
     */
    @Contract(" -> new")
    public @NotNull List<String> getQuestion_images_addr() { return new ArrayList<>(question_images_addr); }

    /**
     * Question choice images observer
     * @return a list of address of images in the same order as the choice list.
     */
    @Contract(" -> new")
    public @NotNull List<String> getChoice_images_addr() { return new ArrayList<>(choice_images_addr); }

    /**
     * Question choices observer
     *
     * @return a list of choices.
     */
    @Contract(" -> new")
    public @NotNull List<String> getChoices() {
        return new ArrayList<>(choices);
    }

    /**
     * Question answer observer
     * @return the answer of the question
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Verify if the given answer is the real answer
     *
     * @param given_answer the answer choose by the user
     * @return true if the given answer matches with the answer, vice versa.
     */
    public boolean VerifyAnswer(@NotNull String given_answer) {
        return given_answer.equals(answer);
    }

    /**
     * Compare if two question is equal. Refer to the class documentation for
     * the definition of equality.
     *
     * @param obj the other object to compare
     * @return true if the two object is equal, and vice versa.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {   // self check
            return true;
        }
        if (obj == null) {   // check null
            return false;
        }
        if (getClass() != obj.getClass()) {      //check instance
            return false;
        }
        Question q = (Question) obj;
        return this.serial_code.equals(q.serial_code);
    }

    /**
     * Compare if the two question object have the same question indeed.
     * This function compares the question body, choices, and answer
     *
     * @param q the other question to compare
     * @return true if the two Question object have the same question, vise versa.
     */
    public boolean isSameQuestion(Question q) {
        if (this == q) {   // self check
            return true;
        }
        if (q == null) {   // check null
            return false;
        }
        return question_body.equals(q.question_body) &&
                choices.equals(q.choices) && answer.equals(q.answer);
    }

    /**
     * Compare every fields in question object to check they are equal to each
     * other.
     *
     * @param q the other question object to compare
     * @return true if teh two Question object have the same value for every
     * fields.
     */
    public boolean trueEqual(Question q) {
        if (this == q) {   // self check
            return true;
        }
        if (q == null) {   // check null
            return false;
        }
        return this.serial_code.equals(q.serial_code) && tags.equals(q.tags)
                && class_code.equals(q.class_code) &&
                permission == q.permission &&
                question_body.equals(q.question_body) &&
                choices.equals(q.choices) && answer.equals(q.answer);
    }

    /**
     * Generate a unique hash value for each question
     *
     * @return Unique hash value for each question
     */
    @Override
    public int hashCode() {
        return serial_code.hashCode();
    }

    /**
     * Compare the serial code of the two question.
     */
    @Override
    public int compareTo(@NotNull Question q) {
        return this.serial_code.compareTo(q.serial_code);
    }

    /**
     * Timestamp the object when the instance created
     */
    @Override
    public void setTime() {
        try {
            this.inTimestamp = GetSystemUpTime.getSystemUptime();
        } catch (Exception e) {
            this.inTimestamp = -1;
            e.printStackTrace();
        }
    }

    /**
     * Get the time when the instance is saved into cache
     *
     * @return the timestamp of the is object
     */
    @Override
    public long getTime() {
        return this.inTimestamp;
    }
}
