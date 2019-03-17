
package gstbilling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Rishabh
 */
public class Connect {
    public static final String URL="jdbc:mysql://localhost:"+DbDatabase.getPort()+"/";
    public static final String USER=DbDatabase.getDbuser();
    public static final String PASSWORD=DbDatabase.getDbpass();
     public static final String DATABASE=DbDatabase.getDbname();
      public static Connection connect()throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(URL+DATABASE,USER,PASSWORD);
        return con;
    }
    
   
  
    
}
