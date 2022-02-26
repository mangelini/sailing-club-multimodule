package messageManagement.member;

import dao.RegistrationFeeDAO;
import entities.Boat;
import entities.Race;
import entities.RegistrationFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.io.Serializable;
import java.util.ArrayList;

public class RegisterToRaceCommand implements Command {
    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;

        try {
            ArrayList<Serializable> arr = (ArrayList<Serializable>) message.getNewObject();
            Race selectedRace = (Race) arr.get(0);
            Boat selectedBoat = (Boat) arr.get(1);

            RegistrationFeeDAO.insertRegistrationFee(new RegistrationFee(selectedBoat, selectedRace, 100.0));
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK);
        }

        return replyMessage;
    }
}
