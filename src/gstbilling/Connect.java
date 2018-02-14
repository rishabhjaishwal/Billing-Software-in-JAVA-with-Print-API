
package gstbilling;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Rishabh
 */
public class Connect {
    public static final String URL="jdbc:mysql://localhost:3306/";
    public static final String USER="root";
    public static final String PASSWORD="root";
     public static final String DATABASE="gstseven";
      public static Connection connect()throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(URL+DATABASE,USER,PASSWORD);
        return con;
    }
    
}
