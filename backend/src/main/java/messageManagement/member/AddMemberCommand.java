package messageManagement.member;

import entities.Member;
import dao.MemberDAO;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

/**
 * Command that adds a member if another record in member table does not have same
 * credentials
 */
public class AddMemberCommand implements Command {
  @Override
  public synchronized Reply execute(Message message) {
    Reply replyMessage = null;
    Member actualMember = null;

    try {
      Member clientMember = (Member) message.getUser();

      // assert that username is not already used
      if (MemberDAO.searchMemberByUsername(clientMember.getUsername()) == null) {
        // Insert member to DB
        int ID = MemberDAO.insertMember(clientMember);
        // get the newly added Member by searching DB with its ID
        actualMember = MemberDAO.searchMemberByID(ID);
      } else throw new Exception();
    } catch (Exception e) {
      replyMessage = new Reply(ReplyType.ERROR);
      return replyMessage;
    } finally {
        replyMessage = new Reply(ReplyType.OK, actualMember);
    }

    return replyMessage;
  }
}
