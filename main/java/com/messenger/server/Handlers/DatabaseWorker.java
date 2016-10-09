package com.messenger.server.Handlers;


import java.sql.ResultSet;
import java.sql.SQLException;

class DatabaseWorker extends DatabaseConnector implements DbReader,DbWriter {
    private static final String CHECK_ID = "SELECT user_id FROM users WHERE user_id = ";

    public DatabaseWorker(){
        super();
    }
    @Override
    public ResultSet checkForExistingUser(String s) {

        ResultSet rs = null;
        try {
            rs = getStatement().executeQuery(CHECK_ID + s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public void writeNewUser(String s) {

    }
}
