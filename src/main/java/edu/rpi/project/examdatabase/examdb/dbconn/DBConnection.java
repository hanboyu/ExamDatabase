package edu.rpi.project.examdatabase.examdb.dbconn;

import edu.rpi.project.examdatabase.examdb.dbaccess.Query;

public interface DBConnection {
    Query connect(String ip, int port);
}
