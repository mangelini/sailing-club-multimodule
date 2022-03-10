package messageManagement.employee;

import dao.EmployeeDAO;
import entities.Employee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.util.ArrayList;

public class GetAllEmployeesCommand implements Command {
    @Override
    public Reply execute(Message message) {
        Reply replyMessage = null;
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            employees = EmployeeDAO.getAllEmployees();
        } catch (Exception e){
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK, employees);
        }

        return replyMessage;
    }
}
