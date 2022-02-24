package messageManagement.member;

import dao.BoatDAO;
import entities.Boat;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

public class AddBoatCommand implements Command {
    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;
        boolean boatAdded = false;

        try {
            boatAdded = BoatDAO.insertBoat((Boat) message.getNewObject());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (boatAdded)
                replyMessage = new Reply(ReplyType.OK);
            else
                replyMessage = new Reply(ReplyType.ERROR);
        }

        return replyMessage;
    }
}
