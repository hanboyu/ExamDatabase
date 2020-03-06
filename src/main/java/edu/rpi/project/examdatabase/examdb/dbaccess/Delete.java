package edu.rpi.project.examdatabase.examdb.dbaccess;

import java.util.Map;

public interface Delete {
    int doDelete(Map<String, String> conditions);
}
