package com.messenger.server.core;

import com.messenger.server.Handlers.IncomingDataSeparator;

import java.io.*;
import java.net.Socket;

public class SocketsCreator extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private IncomingDataSeparator distributor;

    {
        distributor = new IncomingDataSeparator();
    }


    public SocketsCreator(Socket s) throws IOException {
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // Включаем автоматическое выталкивание:
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
                .getOutputStream())), true);
        // Если любой из вышеприведенных вызовов приведет к
        // возникновению исключения, то вызывающий отвечает за
        // закрытие сокета. В противном случае, нить
        // закроет его.
        start(); // вызываем run()
    }

    public void run() {
        System.out.println("Client connected");
        try {
            while (true) {
                String str = in.readLine();
                System.out.println("in " + str);
                if (str.equals("END"))
                    break;
                    distributor.processStream(str, out);
//                    System.out.println("out " + str);
            }
            System.out.println("closing...");
        } catch (IOException e) {
            System.err.println("IO Exception");
            System.out.println(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }
}

