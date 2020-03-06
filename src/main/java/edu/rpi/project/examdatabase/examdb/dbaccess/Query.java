package edu.rpi.project.examdatabase.examdb.dbaccess;

import edu.rpi.project.examdatabase.examdb.QueryObject;

import java.util.List;
import java.util.Map;

/**
 * Query interface defines method that implements
 * query algorithms in database
 */
public interface Query {
    List<QueryObject> doQuery(Map<String, String> arguments);
}
