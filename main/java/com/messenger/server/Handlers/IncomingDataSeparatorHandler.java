package com.messenger.server.Handlers;

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

    private void separateInputStream(String incomingStream) {
        String[] s = incomingStream.split(SEPARATOR);
        userId = s[1];
        userName = s[2];
        passw = s[3];
    }

    void registerUser(String incomingStream, PrintWriter out) {
        separateInputStream(incomingStream);
        if (!checkUserId(userId)) {
            writer.writeNewUser(userId, userName, passw);
            out.println(REGISTRATION_KEY + SEPARATOR + userId + SEPARATOR + SUCCESSFUL);
//                System.out.println(REGISTRATION_KEY + SEPARATOR + userId + SEPARATOR + SUCCESSFUL);
        } else {
            out.println(REGISTRATION_KEY + SEPARATOR + userId + SEPARATOR + UNSUCCESSFUL);
//            System.out.println(REGISTRATION_KEY + SEPARATOR + userId + SEPARATOR + UNSUCCESSFUL);
        }
    }

    private Boolean checkUserId(String userid) {
        ResultSet rs = reader.checkForExistingUser(userid);
        try {
//            System.out.println("Провека пользователя");
            while (rs.next()) {
                String s = rs.getString("user_id");
                if (s.equals(userid))
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

