package edu.rpi.project.examdatabase.examdb.DataContainers.Database.dbaccess;

import edu.rpi.project.examdatabase.examdb.init;

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
            Connection con = DriverManager.getConnection(init.DB_ADDRESS, init.DB_USER, init.DB_PSWD);
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
        test.put("ID", "001");
        test.put("USERNAME", "admin");
        test.put("PASSWORD", "admin1");
        test.put("EMAIL", "xur2@rpi.edu");
        test.put("PERMISSION", "0");
        test.put("FIRSTNAME", "Ran");
        test.put("LASTNAME", "Xu");
        test.put("TOKEN", "");
        InsertToSQL test_instance = new InsertToSQL();
        test_instance.doInsert(test, "users");
    }
}
