package com.messenger.server.core;

import java.io.*;
import java.net.*;

class Server {

    void startSrv() throws IOException {

        ServerSocket s = new ServerSocket(4444);
        System.out.println("Server Started");

        try {
            while (true) {
                // Блокируется до возникновения нового соединения:
                Socket socket = s.accept();
                try {
                    new SocketsCreator(socket);
                } catch (IOException e) {
                    System.out.println(e);
                    // Если завершится неудачей, закрывается сокет,
                    // в противном случае, нить закроет его:
                    socket.close();
                }
            }
        } finally {
            s.close();
        }
    }


}