package messageManagement.member;

import common.Constants;
import dao.MembershipFeeDAO;
import dao.NotifyMembershipFeeDAO;
import entities.Member;
import entities.MembershipFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.sql.Timestamp;

public class PayMembershipFeeCommand implements Command {
    @Override
    public Reply execute(Message message) {
        Reply replyMessage = null;
        MembershipFee membershipFee;

        try {
            Member member = (Member) message.getUser();

            membershipFee = new MembershipFee(member, new Timestamp(System.currentTimeMillis()),
                    Constants.MEMBERSHIP_FEE, (String)message.getNewObject());

            MembershipFeeDAO.insertMembershipFee(membershipFee);

            NotifyMembershipFeeDAO.deleteNotification(member.getID());
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK);
        }
        return replyMessage;
    }
}
