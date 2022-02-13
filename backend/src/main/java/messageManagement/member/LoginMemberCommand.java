package messageManagement.member;

import memberManagement.Member;
import memberManagement.MemberDAO;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

/**
 * LoginMemberCommand
 */
public class LoginMemberCommand implements Command {
  @Override
  public Reply execute(Message message) {
    Reply replyMessage = null;
    Member member = null;

    try {
      Member clientMember = (Member) message.getUser();

      member = MemberDAO.searchMember(clientMember.getUsername());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (member != null)
        replyMessage = new Reply(ReplyType.OK, member);
    }

    return replyMessage;
  }
}