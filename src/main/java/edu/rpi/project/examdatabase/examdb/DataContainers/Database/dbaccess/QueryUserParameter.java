package edu.rpi.project.examdatabase.examdb.DataContainers.Database.dbaccess;

public class QueryUserParameter implements QueryParameters{
   private String username;
   private String password;
   private String permission;
   private String table;

   public QueryUserParameter(String username, String password, String permission) {
      this.username = username;
      this.password = password;
      this.permission = permission;
   }

   @Override
   public String createQueryString() {
      return String.format("SELECT * FROM %s WHERE USERNAME = '%s' AND " +
         "PASSWORD = '%s' AND PERMISSION = '%s'", table, username, password, permission);
   }

   @Override
   public void setQueryTable(String table) {
      this.table = table;
   }
}
