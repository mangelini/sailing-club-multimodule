package messageManagement.member;

import dao.*;
import entities.Boat;
import entities.Member;
import entities.MembershipFee;
import entities.StorageFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.util.ArrayList;

/**
 * Command that checks whether the member has been sent by an employee
 * a notification to renew his membership fee or not
 */
public class GetMemberMembFeesToPay implements Command {
    @Override
    public Reply execute(Message message) {
        Reply replyMessage = null;
        boolean feeExpired = false;

        try {
            Member member = (Member) message.getUser();

            if (NotifyMembershipFeeDAO.isMemberPresent(member.getID()))
                feeExpired = true;

        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK, feeExpired);
        }

        return replyMessage;
    }
}
