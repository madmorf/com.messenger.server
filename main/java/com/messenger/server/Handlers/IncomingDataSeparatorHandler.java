package com.messenger.server.Handlers;

import com.messenger.server.Handlers.DbWorker.DbReader;
import com.messenger.server.Handlers.DbWorker.DbWorker;
import com.messenger.server.Handlers.DbWorker.DbWriter;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

class IncomingDataSeparatorHandler {

    private static final String SEPARATOR = ";";

    private static final String REGISTRATION_KEY = "01";

    private static final int SUCCESSFUL = 0;

    private static final int UNSUCCESSFUL = 1;

    private DbReader reader = new DbWorker();

    private DbWriter writer = new DbWorker();

    private String userName;

    private String userId;

    private String passw;

    /**
     * Separate input stream
     * Extract phone number (userId),username, password
     */
    private void extractRegistrationData(String incomingStream) {
        String[] s = incomingStream.split(SEPARATOR);
        userId = s[1];
        userName = s[2];
        passw = s[3];
    }

    /**
     * Write new user to database
     */
    void registerUser(String incomingStream, PrintWriter out) {
        extractRegistrationData(incomingStream);
        if (!checkUserId(userId)) {
            writer.writeNewUser(userId, userName, passw);
            out.println(REGISTRATION_KEY + SEPARATOR + userId + SEPARATOR + SUCCESSFUL);
        } else {
            out.println(REGISTRATION_KEY + SEPARATOR + userId + SEPARATOR + UNSUCCESSFUL);
        }
    }

    /**
     * Check database for existing user
     * Return true\false
     */
    private Boolean checkUserId(String userId) {
        ResultSet rs = reader.checkForExistingUser(userId);
        try {
            while (rs.next()) {
                String s = rs.getString("user_id");
                if (s.equals(userId))
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

