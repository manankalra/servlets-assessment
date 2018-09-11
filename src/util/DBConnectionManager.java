package util;


import java.sql.*;


// not being used
public class DBConnectionManager {

    private Connection connection;

    public DBConnectionManager(String jdbcURL, String jdbcUsername, String jdbcPassword) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public Connection getConnection() {
        return this.connection;
    }

}