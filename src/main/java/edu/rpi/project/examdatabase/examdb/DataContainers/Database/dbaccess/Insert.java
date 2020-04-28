package edu.rpi.project.examdatabase.examdb.DataContainers.Database.dbaccess;

import java.util.Map;

public interface Insert {
    int doInsert(Map<String, String> fields, String tablename);
}
