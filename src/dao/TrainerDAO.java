package dao;

import java.sql.*;
import java.util.*;

import models.*;


public class TrainerDAO {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public TrainerDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertTrainer(Trainer trainer) throws SQLException {
        String sql = "INSERT INTO Trainer (name, subjectOne, subjectTwo) VALUES (?, ?, ?)";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, trainer.getName());
        statement.setString(2, trainer.getSubjectOne());
        statement.setString(3, trainer.getSubjectTwo());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Trainer> listAllTrainers() throws SQLException {
        List<Trainer> listTrainers = new ArrayList<>();
        String sql = "SELECT * FROM Trainer";
        connect();
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String subjectOne = resultSet.getString("subjectOne");
            String subjectTwo = resultSet.getString("subjectTwo");
            Trainer trainer = new Trainer(name, subjectOne, subjectTwo);
            listTrainers.add(trainer);
        }
        resultSet.close();
        statement.close();
        disconnect();
        return listTrainers;
    }

    public boolean deleteTrainer(Trainer trainer) throws SQLException {
        String sql = "DELETE FROM Trainer where name = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, trainer.getName());
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateTrainer(Trainer trainer) throws SQLException {
        String sql = "UPDATE Trainer SET subjectOne = ?, subjectTwo = ?";
        sql += " WHERE name = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, trainer.getSubjectOne());
        statement.setString(2, trainer.getSubjectTwo());
        statement.setString(3, trainer.getName());
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public Trainer getTrainer(String n) throws SQLException {
        Trainer trainer = null;
        String sql = "SELECT * FROM Trainer WHERE name = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, n);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String subjectOne = resultSet.getString("subjectOne");
            String subjectTwo = resultSet.getString("subjectTwo");
            trainer = new Trainer(name, subjectOne, subjectTwo);
        }
        resultSet.close();
        statement.close();
        return trainer;
    }

}
