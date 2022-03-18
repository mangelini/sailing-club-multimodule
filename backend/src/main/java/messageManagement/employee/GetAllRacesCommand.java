package messageManagement.employee;

import dao.RaceDAO;
import entities.Race;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.util.ArrayList;

/**
 * Command that returns a list of all races available and expired present in database
 */
public class GetAllRacesCommand implements Command {
    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;
        ArrayList<Race> races = new ArrayList<>();

        try {
            RaceDAO.updateExpirationField();
            races = RaceDAO.getAllRaces();
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK, races);
        }

        return replyMessage;
    }
}
