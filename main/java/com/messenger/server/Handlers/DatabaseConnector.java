package com.messenger.server.Handlers;

import java.sql.*;

class DatabaseConnector {

    private static final String DB_NAME = "test";

    private static final String CHECK_DB = "SELECT datname FROM pg_database WHERE datistemplate = false;";

    private static final String CREATE_DB = "CREATE DATABASE test";

    private static final String CREATE_TABLE = "CREATE TABLE users (user_id bigint NOT NULL," +
            "device_id bigint," +
            "name character varying NOT NULL," +
            "status boolean," +
            "last_online time without time zone," +
            "image bytea," +
            "passwd character varying NOT NULL)";

    private String user = "postgres";

    private String password = "ujvjcbre";

    private String sqlURL = "jdbc:postgresql://127.0.0.1/";

    private String databaseURL = sqlURL + DB_NAME;

    private Statement statement;

    private Connection connection;


    DatabaseConnector() {
        connectToDatabase();
    }

    /**
     * Connection creator
     */
    private void createConnection(String url) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    private void createStatement() throws SQLException {
        statement = connection.createStatement();
    }

    /**
     * Database creator if does not exist
     */
    private void createDatabase() throws SQLException {
        statement.execute(CREATE_DB);
    }

    /**
     * Recreate with new url
     * Create table if does not exist
     */
    private void createTable() throws SQLException {
        createConnection(databaseURL);
        createStatement();
        statement.execute(CREATE_TABLE);
    }

    /**
     * Check database for existing
     * return false if not find
     */
    private boolean findDatabase() throws SQLException {
        createConnection(sqlURL);
        createStatement();
        ResultSet rs;
        rs = statement.executeQuery(CHECK_DB);
        while (rs.next()) {
            if (rs.getString(1).equals(DB_NAME)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Create database connetion
     */
    private void connectToDatabase() {
        try {
            if (!findDatabase()) {
                createDatabase();
                createTable();
            } else {
                createConnection(databaseURL);
                createStatement();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    /**
     * Return statement
     */
    Statement getStatement() {
        return statement;
    }
}