package messageManagement.employee;

import entities.Employee;
import dao.EmployeeDAO;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

public class AddEmployeeCommand implements Command {
  @Override
  public synchronized Reply execute(Message message) {
    Reply replyMessage = null;

    try {
      Employee clientEmployee = (Employee) message.getUser();

      EmployeeDAO.insertEmployee(clientEmployee);
    } catch (Exception e) {
      replyMessage = new Reply(ReplyType.ERROR);
      return replyMessage;
    } finally {
      replyMessage = new Reply(ReplyType.OK);
    }

    return replyMessage;
  }
}
