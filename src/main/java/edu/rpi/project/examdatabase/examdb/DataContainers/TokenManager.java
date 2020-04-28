package edu.rpi.project.examdatabase.examdb.DataContainers;

import edu.rpi.project.examdatabase.examdb.Objects.Cachable;
import edu.rpi.project.examdatabase.examdb.Objects.User.User;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import static edu.rpi.project.examdatabase.examdb.HelperFunctions.GetSystemUpTime.getSystemUptime;

/**
 * TokenManager class is a singleton class that can produce hashed tokens and save
 * logged in users' information.
 */
public class TokenManager {

    Map<String, Cachable> loggedInUsers;
    private final static long TOKEN_DURATION = 10 * (24 * 60 * 60 * 1000); // token duration of in milliseconds
    private static final TokenManager instance = new TokenManager();

    private final Map<String, Cachable> token_user_map;

    public static TokenManager getInstance() {
        return instance;
    }

    private TokenManager() {
        token_user_map = new Hashtable<>(300);
    }

    /**
     * Generates a hashed token
     * @return String
     */
    public static String generateToken() {
        long temp = getSystemUptime();
        String token = Long.toHexString(temp);
        return (Integer.toHexString(token.hashCode())).toUpperCase();
    }

    /**
     * Save a token-user pair into the map.
     * Before saving the user to the map, use setTime() to
     * record the time that the entry enters the map.
     * @param token String, cannot be empty or null
     * @param user Cachable, cannot be empty or null
     */
    public synchronized void saveLoggedInToken(String token, Cachable user) {
        if (user instanceof User) {
            user.setTime();
            token_user_map.put(token, user);
        }
    }

    /**
     * Get the user information from the map using the token passed in as argument
     * The method checks if the token has expired;
     *
     * @param token String, cannot be empty or null
     * @return a User instance that corresponds to the token; null if not found or expired
     */
    public synchronized User getLoggedInUser(String token) {
        Cachable user = token_user_map.get(token);
        long current_time = getSystemUptime();
        if (user == null) {
            return null;
        }
        long last_login_time = user.getTime();
        if (current_time - last_login_time > TOKEN_DURATION) {
            removeLoggedOutUser(token);
            return null;
        } else {
            user.setTime();
            token_user_map.replace(token, user);
            return ((User) user).clone();
        }
    }

    /**
     * Remove a user from the map.
     * The method checks if token is in the map and remove the token from the map
     * if it exists.
     *
     * @param token String, cannot be empty or null
     */
    public synchronized void removeLoggedOutUser(String token) {
        token_user_map.remove(token);
    }

    /**
     * Do a sweep on every token in the hash table to check if the token is
     * expired. Delete the expired token.
     */
    public synchronized void RemoveExpiredUser() {
        Set<String> token_set = token_user_map.keySet();
        long current_time = getSystemUptime();

        for (String t : token_set) {
            long last_login_time = (token_user_map.get(t)).getTime();
            if (current_time - last_login_time >= TOKEN_DURATION) {
                removeLoggedOutUser(t);

            }
        }
    }
}
