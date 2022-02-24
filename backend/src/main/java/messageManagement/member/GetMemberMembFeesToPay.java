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

public class GetMemberMembFeesToPay implements Command {
    @Override
    public Reply execute(Message message) {
        Reply replyMessage = null;
        MembershipFee fee = null;

        try {
            Member member = (Member) message.getUser();

            MembershipFee membershipFee = MembershipFeeDAO.searchMembershipFeeByMember(member.getID());
            if (NotifyMembershipFeeDAO.isMembershipFeePresent(membershipFee.getID()))
                fee = membershipFee;

        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK, fee);
        }

        return replyMessage;
    }
}
