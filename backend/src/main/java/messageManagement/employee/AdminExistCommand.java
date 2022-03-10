package messageManagement.employee;

import dao.EmployeeDAO;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

public class AdminExistCommand implements Command {
    @Override
    public Reply execute(Message message) {
        Reply replyMessage = null;
        boolean adminExist = false;

        try {
            adminExist = EmployeeDAO.adminAlreadyExist();
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK, adminExist);
        }

        return replyMessage;
    }
}
