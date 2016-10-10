package com.messenger.server.Handlers;


import java.sql.ResultSet;
import java.sql.SQLException;

class DbWorker extends DatabaseConnector implements DbReader,DbWriter {

    private static final String CHECK_ID = "SELECT user_id FROM users WHERE user_id = ";

    private static final String REGISTER_USER = "";

    @Override
    public ResultSet checkForExistingUser(String userId) {
        ResultSet rs = null;
        try {
            rs = getStatement().executeQuery(CHECK_ID + userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public void writeNewUser(String userId, String userName, String passw) {
        try {
            getStatement().executeUpdate("INSERT INTO users VALUES ('" + userId + "',null,'" + userName +
                            "',null,null,null,'" + passw + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
