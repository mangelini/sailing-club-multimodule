package serverManagement;

import messageManagement.Message;
import messageManagement.Reply;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread implements Runnable {
  private static int MAX = 100;
  private static final long SLEEPTIME = 200;

  private Server server;
  private Socket socket;

  /**
   * Constructor for initializing the client of connection
   * and the server which created this thread
   * 
   * @param server
   * @param client
   */
  public ServerThread(final Server server, final Socket client) {
    this.server = server;
    this.socket = client;
  }

  @Override
  public void run() {
    ObjectInputStream is = null;
    ObjectOutputStream os = null;

    // Tries to establish a connection with the source stream
    try {
      is = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    while (true) {
      try {
        Object i = is.readObject();

        if (i instanceof Message) {
          Message message = (Message) i;

          System.out.println("Thread receives message with id " + message.getSerialversionuid() + " from its client%n");
          Thread.sleep(SLEEPTIME);

          if (os == null) {
            os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
          }

          Reply replyMessage = message.getRequestType().execute(message);

          os.writeObject(replyMessage);
          os.flush();
        }
      } catch (Exception e) {
        e.printStackTrace();
        System.exit(0);
      }
    }
  }
}
