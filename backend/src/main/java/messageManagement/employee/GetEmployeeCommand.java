
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
public class GetEmployeeCommand implements Command {
  @Override
  public synchronized Reply execute(Message message) {
    Reply replyMessage = null;

    try {
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
