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
        Boat boat = null;

        try {
            Boat boatToAdd = (Boat) message.getNewObject();
            boatAdded = BoatDAO.insertBoat(boatToAdd);

            // TODO insertBoat should return the boat ID and to do the search with that
            boat = BoatDAO.searchBoatByName(boatToAdd.getName()).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (boatAdded)
                replyMessage = new Reply(ReplyType.OK, boat);
            else
                replyMessage = new Reply(ReplyType.ERROR);
        }

        return replyMessage;
    }
}
