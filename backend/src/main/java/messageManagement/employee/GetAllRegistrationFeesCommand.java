package messageManagement.employee;

import dao.RegistrationFeeDAO;
import entities.RegistrationFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.util.ArrayList;

/**
 * Command that returns a list of all registration to race fees present in database
 */
public class GetAllRegistrationFeesCommand implements Command {
    @Override
    public Reply execute(Message message) {
        Reply replyMessage = null;
        ArrayList<RegistrationFee> fees = new ArrayList<>();

        try {
            fees = RegistrationFeeDAO.getAllFees();
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage  = new Reply(ReplyType.OK, fees);
        }

        return replyMessage;
    }
}
