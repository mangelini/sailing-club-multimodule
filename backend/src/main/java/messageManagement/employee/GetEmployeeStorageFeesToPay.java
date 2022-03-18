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

/**
 * Command that returns a list of all storage fees that needs to be paid by every member
 */
public class GetEmployeeStorageFeesToPay implements Command {
    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;
        ArrayList<Boat> boatsWithExpiredFees = new ArrayList<>();

        try {
            // search for all boats and find the ones with Storage Fees expired
            ArrayList<Boat> allBoats = BoatDAO.getAllBoats();

            for (Boat b : allBoats) {
                StorageFee storageFee = StorageFeeDAO.searchNotExpiredStorageFeeOfBoat(b.getID());

                if (storageFee == null && !NotifyStorageFeeDAO.notificationAlreadySent(b.getID())){
                    boatsWithExpiredFees.add(b);
                }
            }
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK, boatsWithExpiredFees);
        }

        return replyMessage;
    }
}
