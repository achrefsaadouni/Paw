package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHandler extends Configs {

    static DbHandler dbHandler ; 
    protected Connection dbconnection;

    public static DbHandler getDBHandler(){
        if (dbHandler==null)
        {
            return dbHandler=new DbHandler();
        }
        return dbHandler;
    }
    
    public Connection getConnection() {
        final String ConnectionString = "jdbc:mysql://" + Configs.dbhost + ":" + Configs.dbport + "/" + Configs.dbname;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }

        try {
            dbconnection = DriverManager.getConnection(ConnectionString, Configs.dbuser, Configs.dbpass);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return dbconnection;
    }

}
