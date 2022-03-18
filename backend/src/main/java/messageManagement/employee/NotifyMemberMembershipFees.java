package messageManagement.employee;

import dao.NotifyMembershipFeeDAO;
import entities.Member;
import entities.NotifyMembershipFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

/**
 * Command that adds a record to NotifyMembershipFee table of the member which needs
 * to renew his membership fee
 */
public class NotifyMemberMembershipFees implements Command {
    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;

        try {
            Member memberToNotify = (Member) message.getNewObject();

            NotifyMembershipFeeDAO.insertNotifyMember(new NotifyMembershipFee(memberToNotify, true));
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK);
        }

        return replyMessage;
    }
}
