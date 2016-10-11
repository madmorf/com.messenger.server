package com.messenger.server.Handlers.DbWorker;

public interface DbWriter {

    void writeNewUser(String userId, String userNam, String passw);

}
