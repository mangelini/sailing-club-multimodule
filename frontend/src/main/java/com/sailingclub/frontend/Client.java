package com.sailingclub.frontend;

import messageManagement.Message;
import messageManagement.MessageType;
import messageManagement.Reply;
import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Client side application entry point
 */
public class Client {
    private static final Integer SPORT = 4444;
    private static final String SHOST = "localhost";
    Socket client = null;

    public void run() {
        try {
            client = new Socket(SHOST, SPORT);

            Helpers.setOutputStream(new ObjectOutputStream(client.getOutputStream()));

            Helpers.getOutputStream().writeObject(new Message(MessageType.CONNECTION_ESTABLISHED));

            if (Helpers.getInputStream() == null){
                Helpers.setInputStream(new ObjectInputStream(new BufferedInputStream(client.getInputStream())));
            }

            Object o = Helpers.getInputStream().readObject();

            if(o instanceof Reply reply){
                System.out.println(reply.getResponseCode());
            }

            App.startFrontend();
            Helpers.getInputStream().close();
            Helpers.getOutputStream().close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(final String[] v) {
        new Client().run();
    }
}
