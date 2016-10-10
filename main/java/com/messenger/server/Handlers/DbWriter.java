package com.messenger.server.Handlers;

interface DbWriter {

    void writeNewUser(String userId, String userNam, String passw);

}
