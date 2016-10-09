package com.messenger.server.controller;

import com.messenger.server.core.SocketsCreator;

import java.io.*;
import java.net.*;

public class Server {

    public void startSrv() throws IOException {

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


//    private IncomingDataSeparator distributor = new IncomingDataSeparator();
//
//    private List<Socket> socketArr = new ArrayList<>();


//    public void startSrv() throws IOException {
//        System.out.println("Welcome to Server side");
//        BufferedReader in;
//        PrintWriter out;
//
//        ServerSocket servers = null;
//
//
//        // create server socket
//        try {
//            servers = new ServerSocket(4444);
//            System.out.print("Waiting for a client...");
//        } catch (IOException e) {
//            System.out.println("Couldn't listen to port 4444");
//            System.exit(-1);
//        }
//
//        while (true) {
//            try {
//                Socket socket = servers.accept();
//                System.out.println("Client connected");
//                System.out.println("Wait for messages");
//                System.out.println(socketArr.toString());
////                socketArr.add(socket);
//                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
//                        .getOutputStream())), true);
//                while (true) {
//                    distributor.processStream(in.readLine(), out);
//                    if (in.readLine().equals("END"))
//                        break;
//                }
//
//            } catch (IOException e) {
//                System.out.println("Can't accept");
//                System.exit(-1);
//            }
//
//        }

//        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
//                .getOutputStream())), true);
//        System.out.println("Wait for messages");
//
//        try {
//            while (true) {
//                distributor.processStream(input = in.readLine(),out);
//                if (input.equals("END"))
//                    break;
//            }
//        } catch (SocketException e) {
//            System.out.println("Connection reset");
//
//        }
//        out.close();
//        in.close();
//        fromclient.close();
//        servers.close();
//    }
}