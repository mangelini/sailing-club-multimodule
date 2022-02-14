package serverManagement;

import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import common.DBUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    private static final Integer SPORT = 4444;
    private static final Integer COREPOOL = 5; // number of threads to keep even in idle
    private static final Integer MAXPOOL = 100; // maximum threads running concurrently
    private static final int IDLETIME = 5000;

    private ServerSocket socket;
    private ThreadPoolExecutor threadPool;

    /**
     * Constructor for initiating server socket
     * @throws IOException
     */
    public Server() throws IOException {
        this.socket = new ServerSocket(SPORT);
        // set the production db
        DBUtil.initDB("jdbc:mysql://localhost:3306/sailing-club", "sailing-club");
    }

    private void run() {
        this.threadPool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        while (true){
            try {
                // blocking code that waits for a request of connection by a client
                Socket client = this.socket.accept();

                // Add a new thread to the queue for the newly created
                // connection of client
                this.threadPool.execute(new ServerThread(this, client));
            } catch (Exception e){
                e.printStackTrace();
                break;
            } finally {
              this.threadPool.shutdown();
            }
        }
    }

    /**
     * Getter for thread pool
     * @return Thread pool
     */
    public ThreadPoolExecutor getThreadPool() {
        return this.threadPool;
    }

    /**
     * Close the socket
     */
    public void close(){
        try {
            this.socket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(final String[] v) throws IOException {
        DBUtil.initDB("jdbc:mariadb://localhost:3307/sailing-club-test", "sailing-club-test");
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current absolute path is: " + s);

        try {
            DBUtil.dbConnect();
        } catch (Exception e){
            e.printStackTrace();
        }
        //new Server().run();
    }
}
