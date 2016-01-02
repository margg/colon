package pl.edu.agh.to.testerka.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private String hostAddress;
    private String port;
    private String dbName;
    private String username;
    private String password;

    public DBConnection(String hostAddress, String port, String dbName, String username, String password) {
        this.hostAddress = hostAddress;
        this.port = port;
        this.dbName = dbName;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://" + hostAddress + ":" + port + "/" + dbName, username, password);
    }
}
