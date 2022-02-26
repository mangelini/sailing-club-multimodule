package messageManagement.employee;

import dao.RegistrationFeeDAO;
import entities.Boat;
import entities.Race;
import entities.RegistrationFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.util.ArrayList;

public class ViewParticipantsCommand implements Command {
    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;
        ArrayList<Boat> boats = new ArrayList<>();

        try {
            Race race =(Race) message.getNewObject();
            ArrayList<RegistrationFee> registrationFees = RegistrationFeeDAO.searchRegistrationFeesByRace(race.getID());

            for (RegistrationFee fee : registrationFees){
                boats.add(fee.getBoat());
            }
        } catch (Exception e){
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK, boats);
        }

        return replyMessage;
    }
}
