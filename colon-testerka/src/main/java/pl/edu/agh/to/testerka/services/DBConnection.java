package pl.edu.agh.to.testerka.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.serviceImpl.Driver");
        return DriverManager.getConnection("serviceImpl:mysql://localhost:5432/colondb","colon", "colon");
    }
}
