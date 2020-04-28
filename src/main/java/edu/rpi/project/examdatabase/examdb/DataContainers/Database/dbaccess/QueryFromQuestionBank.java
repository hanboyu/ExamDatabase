package edu.rpi.project.examdatabase.examdb.DataContainers.Database.dbaccess;

import edu.rpi.project.examdatabase.examdb.Objects.QueryObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class QueryFromQuestionBank implements Query{
    @Override
    public List<QueryObject> doQuery(Map<String, String> arguments) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Xuran123#");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
