package messageManagement.member;

import dao.BoatDAO;
import dao.NotifyStorageFeeDAO;
import dao.StorageFeeDAO;
import entities.Boat;
import entities.Member;
import entities.StorageFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.util.ArrayList;

public class GetMemberStorageFeesToPay implements Command {
    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;
        ArrayList<StorageFee> fees = new ArrayList<StorageFee>();

        try {
            Member member = (Member) message.getUser();
            ArrayList<Boat> boatsOfMember = BoatDAO.searchBoatsByMember(member);

            for (Boat boat : boatsOfMember) {
                StorageFee storageFee = StorageFeeDAO.searchStorageFeeByBoat(boat.getID());
                if (NotifyStorageFeeDAO.isStorageFeePresent(storageFee.getID()))
                    fees.add(storageFee);
            }
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK, fees);
        }

        return replyMessage;
    }
}
