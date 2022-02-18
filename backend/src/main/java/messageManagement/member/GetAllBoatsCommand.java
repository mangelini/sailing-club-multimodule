package messageManagement.member;

import dao.BoatDAO;
import entities.Boat;
import entities.Member;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.util.ArrayList;

public class GetAllBoatsCommand implements Command {
    Reply replyMessage = null;
    ArrayList<Boat> results;

    @Override
    public Reply execute(Message message) {
        try {
            results = BoatDAO.searchBoatsByMember((Member) message.getUser());
        } catch (Exception e){
            replyMessage = new Reply(ReplyType.ERROR);
        } finally {
            replyMessage = new Reply(ReplyType.OK, results);
        }

        return replyMessage;
    }
}
