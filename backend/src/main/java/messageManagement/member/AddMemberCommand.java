package messageManagement.member;

import entities.Member;
import dao.MemberDAO;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

/**
 * AddMemberCommand
 */
public class AddMemberCommand implements Command {
  @Override
  public Reply execute(Message message) {
    Reply replyMessage = null;

    try {
      Member clientMember = (Member) message.getUser();

      MemberDAO.insertMember(clientMember);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      replyMessage = new Reply(ReplyType.OK);
    }

    return replyMessage;
  }
}
