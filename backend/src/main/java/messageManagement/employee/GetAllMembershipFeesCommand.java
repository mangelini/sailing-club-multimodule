package messageManagement.employee;

import dao.MembershipFeeDAO;
import entities.MembershipFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.util.ArrayList;

/**
 * Command that returns all membership fees present in database
 */
public class GetAllMembershipFeesCommand implements Command {
    @Override
    public Reply execute(Message message) {
        Reply replyMessage = null;
        ArrayList<MembershipFee> fees = new ArrayList<>();

        try {
            fees = MembershipFeeDAO.getAllFees();
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage  = new Reply(ReplyType.OK, fees);
        }

        return replyMessage;
    }
}
