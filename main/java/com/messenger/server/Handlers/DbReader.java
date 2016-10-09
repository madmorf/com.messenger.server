package com.messenger.server.Handlers;

import java.sql.ResultSet;

public interface DbReader {

    ResultSet checkForExistingUser(String s);
}
