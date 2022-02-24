package messageManagement.employee;

import dao.BoatDAO;
import dao.NotifyStorageFeeDAO;
import dao.StorageFeeDAO;
import entities.Boat;
import entities.StorageFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.util.ArrayList;

public class GetEmployeeStorageFeesToPay implements Command {
    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;
        ArrayList<StorageFee> fees = new ArrayList<>();

        try {
            // search for all boats and find the ones with Storage Fees expired
            ArrayList<Boat> allBoats = BoatDAO.getAllBoats();

            for (Boat b : allBoats) {
                StorageFee storageFee = StorageFeeDAO.searchExpiredStorageFeeOfBoat(b.getID());

                if (storageFee != null && !NotifyStorageFeeDAO.notificationAlreadySent(storageFee.getID())){
                    fees.add(storageFee);
                }
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
