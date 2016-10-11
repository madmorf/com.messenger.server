package com.messenger.server.Handlers.DbWorker;


import java.sql.*;

public class DbChecker {

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

    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public DbChecker(){
        checkDatabase();
    }

    private void createDatabase() throws SQLException {
        databaseConnector.getStatement(sqlURL, user, password).execute(CREATE_DB);
        databaseConnector.closeConnection();
    }

    private void createTable() throws SQLException {
        databaseConnector.getStatement(databaseURL, user, password).execute(CREATE_TABLE);
        databaseConnector.closeConnection();
    }

    private boolean findDatabase() throws SQLException {
        ResultSet rs;
        rs = databaseConnector.getStatement(sqlURL, user, password).executeQuery(CHECK_DB);
        while (rs.next()) {
            if (rs.getString(1).equals(DB_NAME)) {
                return true;
            }
        }
        return false;
    }

    private void checkDatabase() {
        try {
            if (!findDatabase()) {
                createDatabase();
                createTable();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
