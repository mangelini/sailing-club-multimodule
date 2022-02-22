package messageManagement.member;

import dao.BoatDAO;
import dao.StorageFeeDAO;
import entities.Boat;
import entities.StorageFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

public class PayStorageFeesCommand implements Command {
    Reply replyMessage = null;

    @Override
    public Reply execute(Message message) {
        try {
            StorageFee storageFee = (StorageFee) message.getNewObject();
            Boat boat = BoatDAO.searchBoatByName(storageFee.getBoat().getName()).get(0);
            StorageFeeDAO.insertStorageFee(new StorageFee(boat));
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK);
        }
        return replyMessage;
    }
}
