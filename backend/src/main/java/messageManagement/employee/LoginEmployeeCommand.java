package messageManagement.employee;

import entities.Employee;
import dao.EmployeeDAO;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

/**
 * LoginEmployeeCommand
 */
public class LoginEmployeeCommand implements Command {
  @Override
  public synchronized Reply execute(Message message) {
    Reply replyMessage = null;
    Employee employee = null;

    try {
      // TODO better way to handle generics
      Employee clientEmployee = (Employee) message.getUser();
      employee = EmployeeDAO.logIn(clientEmployee);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (employee != null) {
        replyMessage = new Reply(ReplyType.OK, employee);
      } else {
        replyMessage = new Reply(ReplyType.NOT_FOUND);
      }
    }

    return replyMessage;
  }
}
