package edu.rpi.project.examdatabase.examdb.Objects.User;

import edu.rpi.project.examdatabase.examdb.Exceptions.PermissionDeniedException;
import edu.rpi.project.examdatabase.examdb.HelperFunctions.GetSystemUpTime;
import edu.rpi.project.examdatabase.examdb.Objects.Cachable;
import edu.rpi.project.examdatabase.examdb.Objects.QueryObject;
import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;
import edu.rpi.project.examdatabase.examdb.DataContainers.Database.dbaccess.Query;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * An <b>User</b> object represents a single user in the system. The User object
 * is immutable. This object is used to storing user information and requesting
 * services. Note that not all services are available to all types of user. Some
 * service maybe restricted to certain type of user.
 * <p>
 * User is an abstract object. The concrete objects that extends User will be
 * the specific type of user. If a subclass user type does not have the
 * permission to certain service, the service function will need to be
 * overridden in the subclass. The overridden function will simply throw a
 * access denied exception.
 * <p>
 * The following user information is stored in this object:
 * - username
 * - first name
 * - last name
 * - email
 * - password
 * - user type
 * The following service is available for the user:
 * - search questions
 * - edit question
 * - delete question
 * - add question
 * - add user
 * - delete user
 * <p>
 * Abstraction Function:
 * Each User has an unique username.
 */
public abstract class User implements Cachable, QueryObject, Comparable<User>, Cloneable {
    protected long inTimestamp;

    protected String firstName;
    protected String lastName;
    protected String username;
    protected String email;
    protected int userType;
    protected String password;


    /**
     * Timestamp the object when the instance created
     */
    @Override
    public void setTime(){
        try{
            this.inTimestamp = GetSystemUpTime.getSystemUptime();
        }
        catch (Exception e){
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

    /*
     ***********************************
     *          Public Getters         *
     ***********************************
     */
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public int getUserType() {
        return userType;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public int compareTo(@NotNull User u) {
        return this.username.compareTo(u.username);
    }

    /*
     ***********************************
     *           Producer              *
     ***********************************
     */

    public abstract User clone();

    /*
     ***********************************
     *   Service Functions for user    *
     ***********************************
     */

    /**
     * Search questions that match the given criteria in arguments
     *
     * @param queryStrategy  defines where to search and the specific algorithm; cannot be null
     * @param queryArguments arguments that will be converted to SQL commands. cannot be null
     * @return a list of Question objects. May be empty but not null.
     */
    public List<Question> searchQuestion(Query queryStrategy, Map<String, String> queryArguments) {
        throw new RuntimeException("searchQuestion() not implemented yet.");
    }

    /**
     * Edit a existing question in the database
     *
     * @param editedQuestion edited version of the question
     * @throws PermissionDeniedException when the user does not have the
     *                                   permission to edit question
     */
    public void editQuestion(Question editedQuestion) throws PermissionDeniedException {
        throw new RuntimeException("editQuestion() is not implemented yet");
    }

    /**
     * Delete a question form the database
     *
     * @param questionID ID of the question that is need to be deleted
     * @throws PermissionDeniedException when the user does not have the
     *                                   permission to call this function.
     */
    public void deleteQuestion(String questionID) throws PermissionDeniedException {
        throw new RuntimeException("deleteQuestion() is not implemented yet");
    }

    /**
     * Add a new question to the database
     * @param newQuestion new question that going to be added to the database
     * @throws PermissionDeniedException when the user does not have the
     *                                   permission to add a new question
     */
    public void addQuestion(Question newQuestion) throws PermissionDeniedException {
        throw new RuntimeException("addQuestion() is not implemented yet");
    }

    /**
     * Add a new user to the system
     *
     * @param newUser the new user
     * @throws PermissionDeniedException when the user does not have the
     *                                   permission to call this function
     */
    public void addUser(User newUser) throws PermissionDeniedException {
        throw new RuntimeException("addUser() is not implemented yet");
    }

    /**
     * Delete a existing user from the system
     *
     * @param username username of the user that will be deleted
     * @throws PermissionDeniedException when the user does not have the
     *                                   permission to make the operation
     */
    public void deleteUser(String username) throws PermissionDeniedException {
        throw new RuntimeException("deleteUser() is not implemented yet");
    }
}

