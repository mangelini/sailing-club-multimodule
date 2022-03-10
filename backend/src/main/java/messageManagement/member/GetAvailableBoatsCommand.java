package messageManagement.member;

import dao.BoatDAO;
import dao.StorageFeeDAO;
import entities.Boat;
import entities.Member;
import entities.StorageFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.util.ArrayList;

public class GetAvailableBoatsCommand implements Command {

    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;
        ArrayList<Boat> availableBoats = new ArrayList<>();

        try {
            Member member = (Member) message.getUser();
            ArrayList<Boat> boats = (ArrayList<Boat>) BoatDAO.searchEnabledBoatsByMember(member);

            for (Boat boat : boats){
                StorageFee storageFee = StorageFeeDAO.searchNotExpiredStorageFeeOfBoat(boat.getID());
                if (storageFee != null)
                    availableBoats.add(boat);
            }
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK, availableBoats);
        }

        return replyMessage;
    }
}
