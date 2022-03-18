package messageManagement.member;

import dao.NotifyStorageFeeDAO;
import dao.StorageFeeDAO;
import entities.Boat;
import entities.StorageFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.util.ArrayList;

/**
 * Command to pay a storage fee with preferred payment type by adding a new record
 * to StorageFee table and deleting notification
 */
public class PayStorageFeesCommand implements Command {
    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;

        try {
            ArrayList arr = (ArrayList) message.getNewObject();
            Boat boat = (Boat) arr.get(0);
            String paymentType = (String) arr.get(1);

            StorageFee storageFee = new StorageFee(boat, paymentType);

            StorageFeeDAO.insertStorageFee(storageFee);

            NotifyStorageFeeDAO.deleteNotification(boat.getID());
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK);
        }
        return replyMessage;
    }
}
