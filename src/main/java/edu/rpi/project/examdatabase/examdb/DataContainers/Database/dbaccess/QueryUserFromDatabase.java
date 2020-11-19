package edu.rpi.project.examdatabase.examdb.DataContainers.Database.dbaccess;

import edu.rpi.project.examdatabase.examdb.Exceptions.UnknownUserTypeException;
import edu.rpi.project.examdatabase.examdb.Objects.QueryObject;
import edu.rpi.project.examdatabase.examdb.Objects.Question.Question;
import edu.rpi.project.examdatabase.examdb.init;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.rpi.project.examdatabase.examdb.Objects.User.*;

public class QueryUserFromDatabase<E> implements Query<E>{
    @Override
    public List<E> doQuery(QueryParameters param) {
        User user = null;
        List<E> users = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection(init.DB_ADDRESS, init.DB_USER, init.DB_PSWD);
            Statement statement = con.createStatement();
            String query = param.createQueryString();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            UserFactory userFactory = UserFactory.getInstance();
            while (rs.next()) {
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                int id = rs.getInt("ID");
                String firstname = rs.getString("FIRSTNAME");
                String lastname = rs.getString("LASTNAME");
                int permission = rs.getInt("PERMISSION");
                user = userFactory.getUser(permission, username, firstname, lastname, null, password);
                users.add((E)user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnknownUserTypeException e) {
            return null;
        }
        return users;
    }

    public static void main(String[] args) {
        Query<User> testQuery = new QueryUserFromDatabase<>();
//        Map<String, String> testArgs= new HashMap<>();
//        testArgs.put("USERNAME", "admin");
//        testArgs.put("PASSWORD", "admin1");
//        testArgs.put("PERMISSION", "0");
        QueryParameters qp = new QueryUserParameter("admin", "admin1", "0");
        qp.setQueryTable("users");
        testQuery.doQuery(qp);
    }
}
