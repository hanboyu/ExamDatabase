package edu.rpi.project.examdatabase.examdb;

/**
 * All services related to user login/logout will be handled by this class.
 *
 * Login methods:
 *  - login by username and password
 *  - login by Central Authentication Service (CAS)
 *  - login by previously saved token
 */
public class AuthenticationService {

    /**
     * Use username and password to login to the system.
     * @param username a string with letters and numbers only
     * @param password a string contains encrypted password
     * @return a string contains the login token. An empty token indicates
     * login was unsuccessful.
     */
    public static String LoginByPassword(String username, String password) {
        //TODO - implement LoginService()
        //throw new RuntimeException("loginByPassword() is not implemented yet");
        return "";
    }

    /**
     * TBD
     * @return
     */
    public static String LoginByCAS(){
        //TODO - implement LoginByCAS()
        throw new RuntimeException("LoginByCAS() is not implemented yet");
    }

    /**
     * This function will check if the token is still valid
     * @param token a string that is saved as cookie on user's web browser
     * @return A User instance which the token is assigned to.
     * Returns null indicates a invalid token.
     */
    public static User VerifyToken(String token){
        //TODO - implement verifyToken()
        throw new RuntimeException("VerifyToken() is not implemented yet");
    }

    /**
     * Terminate a user session
     * @param token token for the session that will be terminated
     */
    public static void Logout(String token){
        //TODO - implement Logout()
        throw new RuntimeException("Logout() is not implemented yet");
    }

}
