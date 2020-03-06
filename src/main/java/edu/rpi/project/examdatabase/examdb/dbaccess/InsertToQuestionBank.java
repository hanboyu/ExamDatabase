package edu.rpi.project.examdatabase.examdb.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class InsertToQuestionBank implements Insert{

    @Override
    public int doInsert(Map<String, String> fields, String tablename) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "Xuran123#");
            StringBuilder insert = new StringBuilder("INSERT INTO %s (");
            insert = new StringBuilder(String.format(insert.toString(), tablename));
            int i = 0;
            for (String fieldName: fields.keySet()) {
                if (i != fields.size() - 1) {
                    insert.append(fieldName).append(",");
                } else {
                    insert.append(fieldName);
                }
                i ++;
            }
            i = 0;
            insert.append(") VALUES (");
            for (String fieldName: fields.keySet()) {
                if (i != fields.size() - 1) {
                    insert.append(fields.get(fieldName)).append(",");
                } else {
                    insert.append(fields.get(fieldName));
                }
                i ++;
            }
            insert.append(");");
            String insertStatement = insert.toString();
            System.out.println(insertStatement);
            Statement statement = con.createStatement();
            statement.execute(insertStatement);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] argv) {
        Map<String, String> test = new HashMap<>();
        test.put("field1", "value1");
        test.put("field2", "value2");
        test.put("field3", "value3");
        InsertToQuestionBank test_instance = new InsertToQuestionBank();
        test_instance.doInsert(test, "test_table");
    }
}
