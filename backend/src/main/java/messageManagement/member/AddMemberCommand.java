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
  public synchronized Reply execute(Message message) {
    Reply replyMessage = null;
    boolean memAdded = false;
    Member actualMember = null;

    try {
      Member clientMember = (Member) message.getUser();

      memAdded = MemberDAO.insertMember(clientMember);
      actualMember = MemberDAO.searchMember(clientMember.getUsername());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (memAdded)
        replyMessage = new Reply(ReplyType.OK, actualMember);
      else
        replyMessage = new Reply(ReplyType.ERROR);
    }

    return replyMessage;
  }
}
