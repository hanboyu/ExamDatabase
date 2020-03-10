package edu.rpi.project.examdatabase.examdb.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class InsertToSQL implements Insert{

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
                    insert.append("'").append(fields.get(fieldName)).append("'").append(",");
                } else {
                    insert.append("'").append(fields.get(fieldName)).append("'");
                }
                i ++;
            }
            insert.append(");");
            String insertStatement = insert.toString();
            System.out.println(insertStatement);
            Statement statement = con.createStatement();
            statement.execute(insertStatement);
            // Unidentified bug. Connection cannot be closed. possibly from JDBC driver.
//            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] argv) {
        Map<String, String> test = new HashMap<>();
        test.put("field1", "abc");
        test.put("field2", "def");
        test.put("field3", "ghj");
        InsertToSQL test_instance = new InsertToSQL();
        test_instance.doInsert(test, "test_table");
    }
}
