package edu.rpi.project.examdatabase.examdb;

import edu.rpi.project.examdatabase.examdb.HelperFunctions.GetSystemUpTime;
import edu.rpi.project.examdatabase.examdb.dbaccess.Query;

import java.util.List;
import java.util.Map;

public abstract class User implements Cachable, QueryObject{
    protected long inTimestamp;

    protected String firstName;
    protected String lastName;
    protected String username;
    protected long id;
    protected String email;

    /**
     * Set the time when the instance is saved into cache
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
     * @return
     */
    @Override
    public long getTime() {
        return this.inTimestamp;
    }

    /*
        Public getters
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

    public long getId() {
        return id;
    }

    /**
     *  Search questions that match the given criteria in arguments
     * @param queryStrategy defines where to search and the specific algorithm; cannot be null
     * @param queryArguments arguments that will be converted to SQL commands. cannot be null
     * @return a list of Question objects. May be empty but not null.
     */
    public List<Question> searchQuestion(Query queryStrategy, Map<String, String> queryArguments) {
        throw new RuntimeException("searchQuestion() not implemented yet.");
    }
}
