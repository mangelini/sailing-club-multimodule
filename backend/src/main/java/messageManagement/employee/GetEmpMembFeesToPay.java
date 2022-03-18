package messageManagement.employee;

import dao.*;
import entities.Member;
import entities.MembershipFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.util.ArrayList;

/**
 * Command that returns a list of members that needs to renew their membership fee
 */
public class GetEmpMembFeesToPay implements Command {
    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;
        ArrayList<Member> members = new ArrayList<>();

        try {
            // search for all members and find the ones with Membership Fees expired
            ArrayList<Member> allMembers = MemberDAO.getAllMembers();

            for (Member m : allMembers) {
                MembershipFee membershipFee = MembershipFeeDAO.searchNotExpiredMembershipFeeByMember(m.getID());

                if (membershipFee == null && !NotifyMembershipFeeDAO.notificationAlreadySent(m.getID())){
                    members.add(m);
                }
            }
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK, members);
        }

        return replyMessage;
    }
}
