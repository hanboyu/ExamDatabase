package edu.rpi.project.examdatabase.examdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Initialize the server. Create database and set other constants
 */
public class init {
    public static final String DB_ADDRESS = "jdbc:mysql://localhost:3306/test_server"; // only as for now.
    public static final String DB_USER = "root"; // may change to other user type. root user is not safe
    public static final String DB_PSWD = "Xuran123#"; // only as for now. will change to more complex password.
    public static final long CACHE_TTL = 604800;

    public static void createUserTable() {
        try {
            Connection con = DriverManager.getConnection(DB_ADDRESS, DB_USER, DB_PSWD);
            Statement statement = con.createStatement();
            statement.execute("CREATE TABLE users (ID INT NOT NULL, USERNAME VARCHAR(64) NOT NULL, " +
                    "PASSWORD VARCHAR(255) NOT NULL, " +
                    "PERMISSION INT NOT NULL, FIRSTNAME VARCHAR(255) NOT NULL, " +
                    "LASTNAME VARCHAR(255) NOT NULL, TOKEN VARCHAR(255) NULL, PRIMARY KEY(ID));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createQuestionTable() {
        try {
            Connection con = DriverManager.getConnection(DB_ADDRESS, DB_USER, DB_PSWD);
            Statement statement = con.createStatement();
            statement.execute("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createUserTable();
    }
}
