package edu.rpi.project.examdatabase.examdb.DataContainers.Database.dbaccess;

import edu.rpi.project.examdatabase.examdb.Objects.QueryObject;

import java.util.List;
import java.util.Map;

/**
 * Query interface defines method that implements
 * query algorithms in database
 */
public interface Query {
    List<QueryObject> doQuery(QueryParameters param);
}
