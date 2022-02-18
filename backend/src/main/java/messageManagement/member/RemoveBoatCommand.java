package messageManagement.member;

import dao.BoatDAO;
import entities.Boat;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

public class RemoveBoatCommand implements Command {
    Reply replyMessage = null;

    @Override
    public Reply execute(Message message) {
        try {
            Boat boatToDelete = (Boat) message.getNewObject();
            BoatDAO.deleteBoat(boatToDelete.getID());
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
        } finally {
            replyMessage = new Reply(ReplyType.OK);
        }

        return replyMessage;
    }
}
