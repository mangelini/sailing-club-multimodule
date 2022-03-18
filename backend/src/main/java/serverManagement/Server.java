package serverManagement;

import common.DBUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Entry point of backend application
 */
public class Server {
    private static final Integer SPORT = 4444;
    private static final Integer COREPOOL = 5; // number of threads to keep even in idle
    private static final Integer MAXPOOL = 100; // maximum threads running concurrently
    private static final int IDLETIME = 5000;

    private final ServerSocket socket;

    /**
     * Constructor for initiating server socket
     */
    public Server() throws IOException {
        this.socket = new ServerSocket(SPORT);
        // set the production db
        DBUtil.initDB("jdbc:mysql://localhost:3306/sailing-club", "com.mysql.cj.jdbc.Driver");
    }

    private void run() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        while (true){
            try {
                // blocking code that waits for a request of connection by a client
                Socket client = this.socket.accept();

                // Add a new thread to the queue for the newly created
                // connection of client
                threadPool.execute(new ServerThread(this, client));
            } catch (Exception ignored){
            }
        }
    }

    public static void main(final String[] v) throws IOException {
        new Server().run();
    }
}
