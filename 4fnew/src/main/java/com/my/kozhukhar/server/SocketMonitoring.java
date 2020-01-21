package com.my.kozhukhar.server;

import com.my.kozhukhar.message.Messages;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class SocketMonitoring implements Runnable {

    private String message;

    private Socket clientSocket;

    private List<SocketMonitoring> allClients;

    private PrintWriter printWriter;

    private boolean isInterrupted;

    private static final Logger LOG = Logger.getLogger(SocketMonitoring.class);

    public SocketMonitoring(Socket clientSocket, List<SocketMonitoring> allClients) {
        this.clientSocket = clientSocket;
        this.allClients = allClients;
        initPrintWriter();
    }

    private void initPrintWriter() {
        try {
            printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        while (!clientSocket.isClosed()) {
            // TODO : Monitoring User information/state
        }
        isInterrupted = true;
        LOG.info(Messages.USER_WAS_DISCONNECTED);
    }

    public void sendMessage() {
        if (!clientSocket.isClosed() && message != null) {
            printWriter.println(message);
            message = null;
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessageForAll(String message) {
        allClients.forEach(client -> client.setMessage(message));
    }

    public boolean isInterrupted() {
        return isInterrupted;
    }
}
