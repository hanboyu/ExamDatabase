package edu.rpi.project.examdatabase.examdb.dbconn;

public interface DBConnection {
    void createTable(String tableName);
    void removeTable(String tableName);
}
