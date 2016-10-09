package com.messenger.server.core;

import com.messenger.server.controller.Server;

import java.io.IOException;


public class Core {
    private Server srv = new Server();

    public void startCore() {
        try {
            srv.startSrv();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
