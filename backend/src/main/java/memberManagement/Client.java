package memberManagement;

import employeeManagement.Employee;
import messageManagement.Message;
import messageManagement.MessageType;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
  private static final Integer SPORT = 4444;
  private static final String SHOST = "localhost";

  public void run() {
    try {
      Socket client = new Socket(SHOST, SPORT);

      ObjectInputStream is = null;
      ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());

      while (true) {
        Employee employeeToAdd = new Employee();
        employeeToAdd.setUsername("empLinux");
        employeeToAdd.setPassword("pass123");

        Message message = new Message<Employee>(employeeToAdd, MessageType.ADD_EMPLOYEE, "");
        os.writeObject(message);
        os.flush();

        if (is == null) {
          is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
        }

        Object o = is.readObject();
        if (o instanceof Reply) {
          Reply reply = (Reply) o;
          System.out.println("Client received " + reply.getResponseCode() + " from server");

          if (reply.getResponseCode() == ReplyType.OK) {
            break;
          }
        }
      }

      client.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(final String[] v) {
    new Client().run();
  }
}
