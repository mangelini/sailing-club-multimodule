package messageManagement.member;

import dao.BoatDAO;
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
            StorageFee storageFee = (StorageFee) message.getNewObject();
            //Boat boat = BoatDAO.searchBoatByName(storageFee.getBoat().getName()).get(0);

            // creates new StorageFee if it isn't already present in db
            // otherwise updates that record
            if (StorageFeeDAO.searchStorageFeeByBoat(storageFee.getID()) == null)
                StorageFeeDAO.insertStorageFee(storageFee);
            else {
                StorageFeeDAO.updateStorageFee(storageFee.getID());

                // delete notification from NotifyStorageFee
                NotifyStorageFeeDAO.deleteNotification(storageFee.getID());
            }

        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK);
        }
        return replyMessage;
    }
}
