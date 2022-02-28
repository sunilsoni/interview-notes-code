package com.interview.notes.code.misc.IKMTest;

import java.sql.*;

public class TestDatabase {


    public static boolean testDatabase(String databaseURL, String user, String password) throws SQLException {
        Connection con = DriverManager.getConnection(databaseURL, user, password);
        String query = "SELECT 1 / 1 = 1";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        int value = 0;
        if (rs.first()) {
            value = rs.getInt(1);
        }
         return value == 1;
    }
}