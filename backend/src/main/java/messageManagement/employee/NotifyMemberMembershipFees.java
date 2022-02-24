package messageManagement.employee;

import dao.NotifyMembershipFeeDAO;
import entities.MembershipFee;
import entities.NotifyMembershipFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

public class NotifyMemberMembershipFees implements Command {
    @Override
    public Reply execute(Message message) {
        Reply replyMessage = null;

        try {
            MembershipFee feeToSend = (MembershipFee) message.getNewObject();
            // do not send notification if it was previously sent
            if (!NotifyMembershipFeeDAO.notificationAlreadySent(feeToSend.getID()))
                NotifyMembershipFeeDAO.insertNotifyMembershipFee(new NotifyMembershipFee(feeToSend, true));
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK);
        }

        return replyMessage;
    }
}
