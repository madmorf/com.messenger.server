package com.messenger.server.Handlers;


import java.io.PrintWriter;

public class IncomingDataSeparator {

    private String incomingStream;
    private IncomingDataSeparatorHandler incomingDataSeparatorHandler;

    {
        incomingDataSeparatorHandler = new IncomingDataSeparatorHandler();
    }

    public void processStream(String stream, PrintWriter out) {
        this.incomingStream = stream;
        String key = stream.substring(0, stream.length() - (stream.length() - 2));
        select(key, out);
    }

    private void select(String key, PrintWriter out) {
        switch (key) {
            case "01":
                System.out.println("Регистрация пользователя");
                incomingDataSeparatorHandler.registerUser(incomingStream, out);
                break;
            case "02":
                System.out.println("Подтверждение регистрации");
                break;
            case "03":
                System.out.println("Логин");
                break;
            case "04":
                System.out.println("Лог офф");
                break;
            case "05":
                System.out.println("Обновить список пользователей");
                break;
            case "06":
                System.out.println("Отправить текст");
                break;
            case "07":
                System.out.println("отправить фото");
                break;
            case "08":
                System.out.println("Отправить файл");
                break;
            case "09":
                System.out.println("Отправить стикер");
                break;
            case "10":
                System.out.println("GPS координаты");
                break;
            case "11":
                System.out.println("Отчет о прочтении");
                break;
        }

    }
}
