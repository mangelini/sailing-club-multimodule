package messageManagement.member;

import entities.Member;
import dao.MemberDAO;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

/**
 * LoginMemberCommand
 */
public class LoginMemberCommand implements Command {
  @Override
  public synchronized Reply execute(Message message) {
    Reply replyMessage = null;
    Member member = null;

    try {
      Member clientMember = (Member) message.getUser();

      member = MemberDAO.logIn(clientMember);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (member != null)
        replyMessage = new Reply(ReplyType.OK, member);
      else
        replyMessage = new Reply(ReplyType.NOT_FOUND);
    }

    return replyMessage;
  }
}
