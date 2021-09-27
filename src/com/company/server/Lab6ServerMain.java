package com.company.server;

import com.company.server.processing.collection_manage.CollectionManagement;
import com.company.server.recognition.CommandInvoker;
import com.company.server.connection.ConnectionManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Lab6ServerMain {
    //TODO: Скрипт, наверное, должен обрабатываться на клиенте
    private static final int DEFAULT_PORT = 8145;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileName = System.getenv("INPUT_FILE_PATH");
        if(fileName == null) {
            System.out.println("Переменная среды \"INPUT_FILE_PATH\" пустая. Методы load и save не будут работать(");
        }
        else {
            File file = new File(fileName);
            try {
                file.toPath().toRealPath();
                if (fileName.matches("[/\\\\]dev.*")) {
                    System.out.println("Не могу исполнить данный файл");
                    System.exit(1);
                }
                    //if (fileName.matches("[/\\\\]dev.*")) {
            } catch (IOException e) {
                System.out.println("Не хулигань-_-");
            }
        }

        int port;
        try {
            port = Integer.valueOf(System.getenv("L6_SERVER_PORT"));
        } catch (NumberFormatException nfe) {
            port = DEFAULT_PORT;
            System.out.println("Установлено значение порта по умолчанию: " + port);
        }
        ConnectionManager connectionManager = new ConnectionManager(port);
        CommandInvoker commandInvoker = new CommandInvoker(new CollectionManagement(new PrintStream(connectionManager.getReplier())));
        commandInvoker.execute("load -path " + fileName);
        System.out.println("Готов начать работу, уважаемый пекарь");
        connectionManager.start(commandInvoker);
    }
}
