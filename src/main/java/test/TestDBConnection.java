package test;

import java.sql.Connection;
import java.sql.SQLException;

import util.DBConnection;

public class TestDBConnection {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBConnection.getConnection();
        if (connection != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Connection failed!");
        }
    }
}
