package dao;

import java.sql.*;
import java.util.*;

import models.*;


public class ScheduleDAO {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
    private String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    public ScheduleDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

    public boolean insertPeriod(Period period) throws SQLException {
        int day = period.getDay();
        String toUpdate = days[day];
        String sql = "UPDATE Schedule SET ";
        sql += toUpdate;
        sql += "=? WHERE time = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, period.getTitle());
        statement.setInt(2, period.getStartTime());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public boolean deletePeriod(Period period) throws SQLException {
        String sql = "DELETE FROM Schedule where time = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, period.getDay());
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public Schedule getPeriods(int t) throws SQLException {
        Schedule schedule = null;
        String sql = "SELECT * FROM Schedule WHERE time = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, t);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            List<Period> periods = new ArrayList<>();
            for(int i=0; i<6; i++) {
                Period temp = new Period(resultSet.getString(days[i]), t, t+1, i);
                periods.add(temp);
            }
            schedule = new Schedule(periods);
        }
        resultSet.close();
        statement.close();
        return schedule;
    }

}
