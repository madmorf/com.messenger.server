package com.messenger.server.Handlers.DbWorker;

import java.sql.ResultSet;

public interface DbReader {

    ResultSet checkForExistingUser(String userId);
}
