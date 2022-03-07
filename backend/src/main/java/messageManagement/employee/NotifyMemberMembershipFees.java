package messageManagement.employee;

import dao.NotifyMembershipFeeDAO;
import entities.Member;
import entities.MembershipFee;
import entities.NotifyMembershipFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

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
