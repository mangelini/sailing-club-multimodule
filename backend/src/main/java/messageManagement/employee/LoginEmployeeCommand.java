package messageManagement.employee;

import employeeManagement.Employee;
import employeeManagement.EmployeeDAO;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

/**
 * LoginEmployeeCommand
 */
public class LoginEmployeeCommand implements Command {
  @Override
  public Reply execute(Message message) {
    Reply replyMessage = null;

    try {
      // TODO better way to handle generics
      Employee clientEmployee = (Employee) message.getUser();
      Employee employee = EmployeeDAO.searchEmployee(clientEmployee.getUsername());

      if (employee != null) {
        replyMessage = new Reply(ReplyType.OK, employee);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return replyMessage;
  }
}
