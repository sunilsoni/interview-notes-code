package com.interview.notes.code.year.y2023.july23.test1;

import java.sql.*;

//Write java program to fetch records from Database Product table which has two columns - (id and name)?
public class FetchRecords {

    public static void main(String[] args) {

        String url = "jdbc:sqlite:my_database";
        String username = "username";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Product");

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching data from database: " + e.getMessage());
        }
    }
}
