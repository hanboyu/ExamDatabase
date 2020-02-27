package edu.rpi.project.examdatabase.examdb;

import java.util.Map;

/**
 *  TokenManager class is a singleton class that can produce hashed tokens and save
 *  logged in users' information.
 */
public class TokenManager {

    Map<String, Cachable> loggedInUsers;
    private long ttl; // duration of time that each token is valid for
    private static TokenManager instance = null;

    public static TokenManager getInstance(long ttl) {
        if (instance == null) {
            return new TokenManager(ttl);
        }
        return instance;
    }

    private TokenManager(long ttl) {
        this.ttl = ttl;
    }

    /**
     * Generates a hashed token
     * @return String
     */
    public static String generateToken() {
        throw new RuntimeException("GenerateToken() is not implemented yet");
    }

    /**
     * Save a token-user pair into the map.
     * Before saving the user to the map, use setTime() to
     * record the time that the entry enters the map.
     * @param token String, cannot be empty or null
     * @param user Cachable, cannot be empty or null
     */
    public synchronized void saveLoggedInToken(String token, Cachable user) {
        throw new RuntimeException("saveLoggedInToken() is not implemented yet");
    }

    /**
     * Get the user information from the map using the token passed in as argument
     * The method checks if the token has expired;
     * @param token String, cannot be empty or null
     * @return a User instance that corresponds to the token; null if not found or expired
     */
    public User getLoggedInUser(String token) {
        throw new RuntimeException("getLoggedInUser() is not implemented yet");
    }

    /**
     * Remove a user from the map.
     * The method checks if token is in the map and remove the token from the map
     * if it exists.
     * @param token String, cannot be empty or null
     */
    public synchronized void removeLoggedOutUser(String token) {
        throw new RuntimeException("removeLoggedOutUser() is not implemented yet");
    }
}
