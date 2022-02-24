package messageManagement.member;

import dao.MembershipFeeDAO;
import dao.NotifyMembershipFeeDAO;
import dao.NotifyStorageFeeDAO;
import dao.StorageFeeDAO;
import entities.Member;
import entities.MembershipFee;
import entities.StorageFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

public class PayMembershipFeeCommand implements Command {
    @Override
    public Reply execute(Message message) {
        Reply replyMessage = null;

        try {
            Member member = (Member) message.getUser();
            MembershipFee membershipFee = (MembershipFee) message.getNewObject();

            // creates new MembershipFee if it isn't already present in db
            // otherwise updates that record
            if (MembershipFeeDAO.searchMembershipFeeByMember(member.getID()) == null)
                MembershipFeeDAO.insertMembershipFee(membershipFee);
            else {
                MembershipFeeDAO.updateMembershipFee(membershipFee.getID());

                // delete notification from NotifyStorageFee
                NotifyMembershipFeeDAO.deleteNotification(membershipFee.getID());
            }

        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK);
        }
        return replyMessage;
    }
}
