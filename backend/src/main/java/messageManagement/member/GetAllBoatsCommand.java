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
    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;
        ArrayList<Boat> results = new ArrayList<>();

        try {
            Object o = message.getUser();

            if (o instanceof Member)
                results = BoatDAO.searchEnabledBoatsByMember((Member) message.getUser());
            else {
                Member selectedMember = (Member) message.getNewObject();
                results = BoatDAO.searchEnabledBoatsByMember(selectedMember);
            }
        } catch (Exception e){
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK, results);
        }

        return replyMessage;
    }
}
