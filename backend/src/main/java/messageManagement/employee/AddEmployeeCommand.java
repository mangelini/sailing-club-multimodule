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
    boolean empAdded = false;

    try {
      Employee clientEmployee = (Employee) message.getUser();
      empAdded = EmployeeDAO.insertEmployee(clientEmployee);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if(empAdded)
        replyMessage = new Reply(ReplyType.OK);
      else
        replyMessage = new Reply(ReplyType.ERROR);
    }

    return replyMessage;
  }
}
