package messageManagement.employee;

import dao.BoatDAO;
import dao.NotifyStorageFeeDAO;
import dao.StorageFeeDAO;
import entities.Boat;
import entities.Member;
import entities.NotifyStorageFee;
import entities.StorageFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class GetStorageFeesToPay implements Command {
    Reply replyMessage = null;
    ArrayList<StorageFee> fees = new ArrayList<StorageFee>();

    @Override
    public Reply execute(Message message) {
        try {
            // search for all boats and find the ones with Storage Fees expired
            ArrayList<Boat> allBoats = BoatDAO.getAllBoats();

            for (Boat b : allBoats){
                StorageFee storageFee = StorageFeeDAO.searchExpiredStorageFeeOfBoat(b.getID());

                if (storageFee != null){
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
