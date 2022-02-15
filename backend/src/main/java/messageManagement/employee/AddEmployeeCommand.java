package messageManagement.employee;


// TODO This class still needs to be implemented

import entities.Employee;
import dao.EmployeeDAO;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

public class AddEmployeeCommand implements Command {
  @Override
  public Reply execute(Message message) {
    Reply replyMessage = null;

    try {
      Employee clientEmployee = (Employee) message.getUser();
      EmployeeDAO.insertEmployee(clientEmployee);

      replyMessage = new Reply(ReplyType.OK);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return replyMessage;
  }
}
