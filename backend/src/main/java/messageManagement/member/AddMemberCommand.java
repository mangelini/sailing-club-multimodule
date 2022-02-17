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
    boolean memAdded = false;

    try {
      Member clientMember = (Member) message.getUser();

      memAdded = MemberDAO.insertMember(clientMember);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (memAdded)
        replyMessage = new Reply(ReplyType.OK);
      else
        replyMessage = new Reply(ReplyType.ERROR);
    }

    return replyMessage;
  }
}
