package edu.rpi.project.examdatabase.examdb;

import java.util.HashSet;
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
    private static final TokenManager instance = null;

    private final Map<String, User> token_user_map;
    private final Map<String, Long> token_createTime_map;

    public static TokenManager getInstance() {
        if (instance == null) {
            return new TokenManager();
        }
        return instance;
    }

    private TokenManager() {
        token_user_map = new Hashtable<>(300);
        token_createTime_map = new Hashtable<>(300);
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
        token_user_map.put(token, (User) user);
        token_createTime_map.put(token, getSystemUptime());
    }

    /**
     * Get the user information from the map using the token passed in as argument
     * The method checks if the token has expired;
     * @param token String, cannot be empty or null
     * @return a User instance that corresponds to the token; null if not found or expired
     */
    public User getLoggedInUser(String token) {
        long last_login_time = token_createTime_map.getOrDefault(token, (long) -1);
        long current_time = getSystemUptime();
        if (last_login_time == -1) {
            return null;
        } else if (current_time - last_login_time > TOKEN_DURATION) {
            removeLoggedOutUser(token);
            return null;
        } else {
            token_createTime_map.replace(token, current_time);
            return token_user_map.getOrDefault(token, null);
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
        token_createTime_map.remove(token);
    }

    /**
     * Do a sweep on every token in the hash table to check if the token is
     * expired. Delete the expired token.
     */
    public synchronized void RemoveExpiredUser() {
        Set<String> token_set = new HashSet<>(token_user_map.size());
        token_set.addAll(token_user_map.keySet());
        token_set.addAll(token_createTime_map.keySet());
        long current_time = getSystemUptime();

        for (String t : token_set) {
            if (token_user_map.containsKey(t) != token_createTime_map.containsKey(t)) {
                removeLoggedOutUser(t);
            } else {
                long last_login_time = token_createTime_map.getOrDefault(t, (long) -1);
                if (current_time - last_login_time >= TOKEN_DURATION) {
                    removeLoggedOutUser(t);
                }
            }
        }
    }
}
