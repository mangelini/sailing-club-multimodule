package messageManagement.member;

import dao.RaceDAO;
import entities.Race;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.util.ArrayList;

public class GetAvailableRacesCommand implements Command {
    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;
        ArrayList<Race> races = new ArrayList<>();

        try {
            RaceDAO.updateExpirationField();
            races = RaceDAO.getRacesNotExpired();
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK, races);
        }

        return replyMessage;
    }
}
