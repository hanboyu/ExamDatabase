package edu.rpi.project.examdatabase.examdb.dbaccess;

import java.util.Map;

public interface Edit {
    int doEdit(Map<String, String> conditions, Map<String, String> changes);
}
