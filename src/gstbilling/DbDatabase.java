/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gstbilling;

/**
 *
 * @author Rishabh
 */
public class DbDatabase {
    public static String port,dbuser,dbpass,dbname;

    public static String getPort() {
        return port;
    }

    /**
     *
     * @param port
     */
    public static void setPort(String port) {
        DbDatabase.port = port;
    }

    public static String getDbuser() {
        return dbuser;
    }

    public static void setDbuser(String dbuser) {
        DbDatabase.dbuser = dbuser;
    }

    public static String getDbpass() {
        return dbpass;
    }

    public static void setDbpass(String dbpass) {
        DbDatabase.dbpass = dbpass;
    }

    public static String getDbname() {
        return dbname;
    }

    public static void setDbname(String dbname) {
        DbDatabase.dbname = dbname;
    }
    
}
