package messageManagement.employee;

import dao.MemberDAO;
import entities.Member;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.util.ArrayList;

/**
 * Command that returns a list of all members present in database
 */
public class GetAllMembersCommand implements Command {
    @Override
    public Reply execute(Message message) {
        Reply replyMessage = null;
        ArrayList<Member> members = new ArrayList<>();

        try {
            members = MemberDAO.getAllMembers();
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK, members);
        }

        return replyMessage;
    }
}
