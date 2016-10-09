package com.messenger.server.Handlers;

import java.sql.ResultSet;

interface DbReader {

    ResultSet checkForExistingUser(String s);
}
