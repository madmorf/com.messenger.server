package com.messenger.server.core;

import com.messenger.server.Handlers.DbWorker.DbChecker;

import java.io.IOException;


public class Core {

    private DbChecker checker = new DbChecker();

    private Server srv = new Server();

    public void startCore() {
        try {
            srv.startSrv();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
