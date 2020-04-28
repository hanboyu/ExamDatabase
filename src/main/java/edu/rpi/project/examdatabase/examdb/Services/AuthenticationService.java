package edu.rpi.project.examdatabase.examdb.Services;

import edu.rpi.project.examdatabase.examdb.Objects.QueryObject;
import edu.rpi.project.examdatabase.examdb.DataContainers.TokenManager;
import edu.rpi.project.examdatabase.examdb.Objects.User.User;
import edu.rpi.project.examdatabase.examdb.DataContainers.Database.dbaccess.Query;
import edu.rpi.project.examdatabase.examdb.DataContainers.Database.dbaccess.QueryUserFromDatabase;
import edu.rpi.project.examdatabase.examdb.Objects.User.UserFactory;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * All services related to user login/logout will be handled by this class.
 * Login methods:
 * - login by username and password
 * - login by Central Authentication Service (CAS)
 * - login by previously saved token
 */
public class AuthenticationService {

    static long TOKEN_DURATION = 10 * 24 * 60 * 60 * 1000;// 10 days

    /**
     * Use username and password to login to the system.
     *
     * @param username a string with letters and numbers only
     * @param password a string contains encrypted password
     * @return a string contains the session token. An empty token indicates
     * login was unsuccessful.
     */
    public static String LoginByPassword(String username, String password) throws NoSuchAlgorithmException {
        String encrypted_password = Encrypt(password);

        // get user's info by username
        Query UserDatabase = new QueryUserFromDatabase();
        Map<String, String> DBRequestArgs = new TreeMap<>();
        DBRequestArgs.put("USERNAME", username);
        List<QueryObject> QueryResults = UserDatabase.doQuery(DBRequestArgs);

        // validate query result
        if (QueryResults.size() < 1) {
            throw new RuntimeException("Multiple users found from username '" + username + "'");
        } else if (QueryResults.size() == 0) {
            return "";
        } else {
            if (!(QueryResults.get(0) instanceof User)) {
                throw new RuntimeException("Query results is not a instance of User");
            }
        }

        User user = (User) QueryResults.get(0);
        if (encrypted_password.equals(user.getPassword())) {
            // password matches
            String session_token = TokenManager.generateToken();
            TokenManager myTokenManager = TokenManager.getInstance(TOKEN_DURATION);
            myTokenManager.saveLoggedInToken(session_token, user);
            return session_token;
        }
        return "";
    }

    /**
     * TBD
     * @return a string contains the session token. An empty token indicates
     *      * login was unsuccessful.
     */
    public static String LoginByCAS(){
        throw new RuntimeException("LoginByCAS() is not implemented yet");
    }

    /**
     * This function will check if the token is still valid, and return the
     * cross bounding user. If the token is invalid or null, a visitor user
     * will be generated and returned.
     *
     * @param token a string that is saved as cookie on user's web browser
     * @return A User instance which the token is assigned to.
     */
    public static User VerifyToken(String token) {
        TokenManager myTokenManager = TokenManager.getInstance(TOKEN_DURATION);
        UserFactory myUserFactory = UserFactory.getInstance();
        User user = null;
        if (!token.isEmpty()) {
            user = myTokenManager.getLoggedInUser(token);
        }
        if (token.isEmpty() || user == null) {
            user = myUserFactory.generateVisitor();
        }
        return user;
    }

    /**
     * Terminate a user session
     * @param token token for the session that will be terminated
     */
    public static void Logout(String token) {
        TokenManager myTokenManager = TokenManager.getInstance(TOKEN_DURATION);
        if (!token.isEmpty()) {
            myTokenManager.removeLoggedOutUser(token);
        }
    }

    /**
     * Encrypt massage using SHA-256
     *
     * @param plaintext the massage that needs to be encrypted
     * @return the ciphertext
     * @throws NoSuchAlgorithmException when the encryption method can not be find
     */
    private static String Encrypt(String plaintext) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        byte[] hash = md.digest(plaintext.getBytes(StandardCharsets.UTF_8));

        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
}
