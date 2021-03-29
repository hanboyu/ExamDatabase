package edu.rpi.project.examdatabase.examdb.Objects.Question;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.GetSystemUpTime;
import edu.rpi.project.examdatabase.examdb.Objects.Cachable;
import edu.rpi.project.examdatabase.examdb.Objects.QueryObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A <b>QuestionLite</b> object is a lite version of the question object. Its purpose is
 * the reduce the space that is storing in the memory.  QuestionLite only stores the
 * following fields:
 *
 *  - Question serial code
 *  - Question tag(s)
 *  - Question body
 *
 */

public final class QuestionLite implements Comparable<QuestionLite>, QueryObject, Cachable {
    protected final String serial_code;
    protected final List<String> tags;
    protected final String question_body;

    protected long inTimestamp;

    //Constructors
    public QuestionLite(String serial_code, List<String> tags, String question_body) {
        this.serial_code = serial_code;
        this.tags = new ArrayList<>(tags);
        this.question_body = question_body;
        setTime();
    }

    public QuestionLite(QuestionLite q){
        this.serial_code = q.serial_code;
        this.tags = new ArrayList<>(q.tags);
        this.question_body = q.question_body;
        setTime();
    }

    public QuestionLite(Question q){
        this.serial_code = q.serial_code;
        this.tags = new ArrayList<>(q.tags);
        this.question_body = q.question_body;
        setTime();
    }

    // Observers

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
    public @NotNull List<String> getTags() {
        return new ArrayList<>(tags);
    }

    /**
     * Question body observer
     * @return a string of the question body
     */
    public String getQuestionBody() {
        return question_body;
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

    /**
     * Compare the serial code of the two question.
     */
    @Override
    public int compareTo(@NotNull QuestionLite o) {
        return this.serial_code.compareTo(o.serial_code);
    }

    /**
     * Compare if two question is equal. Refer to the class documentation for
     * the definition of equality.
     *
     * @param obj the other object to compare
     * @return true if the two object is equal, and vice versa.
     */
    public boolean equals(Object obj){
        if (this == obj) {   // self check
            return true;
        }
        if (obj == null) {   // check null
            return false;
        }
        if (getClass() != obj.getClass()) {      //check instance
            return false;
        }
        QuestionLite q = (QuestionLite) obj;
        return this.serial_code.equals(q.serial_code);
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


}
