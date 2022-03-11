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
        Boat boat = null;

        try {
            Boat boatToAdd = (Boat) message.getNewObject();
            int ID = BoatDAO.insertBoat(boatToAdd);

            boat = BoatDAO.searchBoatByID(ID);
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK, boat);
        }

        return replyMessage;
    }
}
