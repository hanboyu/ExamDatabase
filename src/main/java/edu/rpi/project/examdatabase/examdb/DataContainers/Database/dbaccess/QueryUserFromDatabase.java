package edu.rpi.project.examdatabase.examdb.DataContainers.Database.dbaccess;

import edu.rpi.project.examdatabase.examdb.Objects.QueryObject;
import edu.rpi.project.examdatabase.examdb.init;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryUserFromDatabase implements Query{
    @Override
    public List<QueryObject> doQuery(Map<String, String> arguments) {
        try {
            Connection con = DriverManager.getConnection(init.DB_ADDRESS, init.DB_USER, init.DB_PSWD);
            Statement statement = con.createStatement();
            StringBuilder queryString = new StringBuilder();
            queryString.append("SELECT * FROM users WHERE ");
            int i = 0;
            for (String field: arguments.keySet()) {
                if (i != arguments.size() - 1) {
                    queryString.append(field).append("=").append("'").append(arguments.get(field)).append("'").append(" AND ");
                } else {
                    queryString.append(field).append("=").append("'").append(arguments.get(field)).append("'");
                }
                i += 1;
            }
            String query = queryString.toString();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                int id = rs.getInt("ID");
                String firstname = rs.getString("FIRSTNAME");
                String lastname = rs.getString("LASTNAME");
                int permission = rs.getInt("PERMISSION");
                System.out.println(username);
                System.out.println(password);
                System.out.println(id);
                System.out.println(firstname);
                System.out.println(lastname);
                System.out.println(permission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Query testQuery = new QueryUserFromDatabase();
        Map<String, String> testArgs= new HashMap<>();
        testArgs.put("USERNAME", "admin");
        testArgs.put("PASSWORD", "admin1");
        testArgs.put("PERMISSION", "0");
        testQuery.doQuery(testArgs);
    }
}
