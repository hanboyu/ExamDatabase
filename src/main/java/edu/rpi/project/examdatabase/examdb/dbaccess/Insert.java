package edu.rpi.project.examdatabase.examdb.dbaccess;

import java.util.Map;

public interface Insert {
    int doInsert(Map<String, String> fields);
}
