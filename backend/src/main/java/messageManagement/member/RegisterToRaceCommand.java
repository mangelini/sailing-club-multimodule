package messageManagement.member;

import common.Constants;
import dao.RegistrationFeeDAO;
import entities.Boat;
import entities.Race;
import entities.RegistrationFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Command to register a specified boat to selected race with preferred payment type
 * by adding a new record to RegistrationFee table
 */
public class RegisterToRaceCommand implements Command {
    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;

        try {
            ArrayList<Serializable> arr = (ArrayList<Serializable>) message.getNewObject();
            Race selectedRace = (Race) arr.get(0);
            Boat selectedBoat = (Boat) arr.get(1);
            String paymentType = (String) arr.get(2);

            RegistrationFeeDAO.insertRegistrationFee(new RegistrationFee(selectedBoat, selectedRace,
                    Constants.REGISTRATION_FEE, new Timestamp(System.currentTimeMillis()),
                    paymentType));
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK);
        }

        return replyMessage;
    }
}
