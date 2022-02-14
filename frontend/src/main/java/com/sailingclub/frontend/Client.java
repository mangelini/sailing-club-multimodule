package com.sailingclub.frontend;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static final Integer SPORT = 4444;
    private static final String SHOST = "localhost";
    Socket client;

    /**
     * Close the connection to server
     */
    public void closeConnection(){
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            client = new Socket(SHOST, SPORT);
            ObjectInputStream is = null;
            /*ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());*/


            Helpers.setOutputStream(new ObjectOutputStream(client.getOutputStream()));

            while (true) {
                if (Helpers.getInputStream() == null){
                    is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
                    Helpers.setInputStream(is);
                }

                App.startFrontend();
                System.out.println("Closing socket");
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(final String[] v) {
        new Client().run();
    }
}
