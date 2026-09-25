package ma.youcode.lineperm.database;

import java.sql.*;

public class DBConnection {
    
    private Connection connection;
    private static DBConnection instance;
    private static final String URL = "jdbc:sqlite:auditDB.db";

    private DBConnection(){
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance(){
        if (instance == null) {
            instance = new DBConnection();
        }

        return instance ;
    }

    public Connection getConnection(){
        return connection ;
    }
}
