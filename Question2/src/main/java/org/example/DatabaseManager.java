package org.example;// DatabaseManager.java
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/practice_schema";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String TABLE_NAME = "Sumit_Data"; // Table name to be created

    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
    }

    public static void createTable() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "date VARCHAR(255)," +
                    "month VARCHAR(255)," +
                    "team VARCHAR(255)," +
                    "panelName VARCHAR(255)," +
                    "round VARCHAR(255)," +
                    "skill VARCHAR(255)," +
                    "time VARCHAR(255)," +
                    "candidateCurrentLoc VARCHAR(255)," +
                    "candidatePreferredLoc VARCHAR(255)," +
                    "candidateName VARCHAR(255)" +
                    ")";

            statement.executeUpdate(createTableQuery);

            System.out.println("Table '" + TABLE_NAME + "' created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(List<DataModel> data) {
        data.parallelStream().forEach(record -> {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "INSERT INTO " + TABLE_NAME + " (date, month, team, panelName, round, skill, time, " +
                                 "candidateCurrentLoc, candidatePreferredLoc, candidateName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

                preparedStatement.setString(1, record.getDate());
                preparedStatement.setString(2, record.getMonth());
                preparedStatement.setString(3, record.getTeam());
                preparedStatement.setString(4, record.getPanelName());
                preparedStatement.setString(5, record.getRound());
                preparedStatement.setString(6, record.getSkill());
                preparedStatement.setString(7, record.getTime());
                preparedStatement.setString(8, record.getCandidateCurrentLoc());
                preparedStatement.setString(9, record.getCandidatePreferredLoc());
                preparedStatement.setString(10, record.getCandidateName());

                // Add other parameters as needed, ensuring to check for null values

                // Execute the query
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

