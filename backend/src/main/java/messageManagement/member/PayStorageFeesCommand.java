package messageManagement.member;

import dao.NotifyStorageFeeDAO;
import dao.StorageFeeDAO;
import entities.Boat;
import entities.StorageFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

public class PayStorageFeesCommand implements Command {
    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;

        try {
            Boat boat = (Boat) message.getNewObject();

            StorageFee storageFee = new StorageFee(boat, "Credit Card");

            StorageFeeDAO.insertStorageFeeWithPaymentType(storageFee);

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
