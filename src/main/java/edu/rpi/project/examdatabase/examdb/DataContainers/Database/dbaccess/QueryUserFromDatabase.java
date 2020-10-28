package edu.rpi.project.examdatabase.examdb.DataContainers.Database.dbaccess;

import edu.rpi.project.examdatabase.examdb.Objects.QueryObject;
import edu.rpi.project.examdatabase.examdb.init;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryUserFromDatabase implements Query{
    @Override
    public List<QueryObject> doQuery(QueryParameters param) {
        try {
            Connection con = DriverManager.getConnection(init.DB_ADDRESS, init.DB_USER, init.DB_PSWD);
            Statement statement = con.createStatement();
            String query = param.createQueryString();
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
//        Map<String, String> testArgs= new HashMap<>();
//        testArgs.put("USERNAME", "admin");
//        testArgs.put("PASSWORD", "admin1");
//        testArgs.put("PERMISSION", "0");
        QueryParameters qp = new QueryUserParameter("admin", "admin1", "0");
        qp.setQueryTable("users");
        testQuery.doQuery(qp);
    }
}
