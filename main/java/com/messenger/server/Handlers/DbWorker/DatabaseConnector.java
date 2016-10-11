package com.messenger.server.Handlers.DbWorker;

import java.sql.*;

class DatabaseConnector {

    private static final String DB_NAME = "test";

    private String user = "postgres";

    private String password = "ujvjcbre";

    private String sqlURL = "jdbc:postgresql://127.0.0.1/";

    private Statement statement;

    private Connection connection;


    private void createConnection(String url, String user, String passwd) throws SQLException {
        connection = DriverManager.getConnection(url, user, passwd);
    }

    private void createConnection() throws SQLException {
        connection = DriverManager.getConnection(sqlURL + DB_NAME, user, password);
    }

    Statement getStatement(String url, String user, String passwd) {
        try {
            createConnection(url, user, passwd);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    Statement getStatement() {
        try {
            createConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}