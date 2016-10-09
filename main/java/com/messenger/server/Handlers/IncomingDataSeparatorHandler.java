package com.messenger.server.Handlers;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

class IncomingDataSeparatorHandler {

//    private DatabaseConnector dbConnector = new DatabaseConnector();
    private DbReader reader = new DatabaseWorker();

    {
//        dbConnector.connectDBorDIE();
    }

    void registerUser(String incomingStream, PrintWriter out) {
        String[] s = incomingStream.split(";");
        if (!checkUserId(s[1])) {
//            try {
//                System.out.println("Регистрация пользователя");
////                dbConnector.getStatement().executeUpdate("INSERT INTO users VALUES (" + s[1] + "," + "null,'" + s[2] +
//                        "'," + " null," + "null," + "null,'" + s[3] + "')");
//                out.println("01;" + s[1] + ";0;");
                System.out.println("01;" + s[1] + ";0;");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        } else {
            out.println("01;" + s[1] + ";1;");
            System.out.println("01;" + s[1] + ";1;");
        }
    }

    private Boolean checkUserId(String userid) {
        ResultSet rs = reader.checkForExistingUser(userid);
        try {
            System.out.println("Провека пользователя");
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

