package edu.rpi.project.examdatabase.examdb.DataContainers.Database.dbconn;

public interface DBConnection {
    void createTable(String tableName);
    void removeTable(String tableName);
}
