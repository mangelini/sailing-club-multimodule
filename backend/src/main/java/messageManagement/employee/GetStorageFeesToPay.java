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

public class GetStorageFeesToPay implements Command {
    Reply replyMessage = null;
    ArrayList<Date> dates = null;
    ArrayList<Member> owners = null;
    ArrayList<Boat> boats = null;

    @Override
    public Reply execute(Message message) {
        try {
            // search for all boats and find the ones with Storage Fees expired
            ArrayList<Boat> allBoats = BoatDAO.getAllBoats();

            for (Boat b : allBoats){
                StorageFee storageFee = StorageFeeDAO.searchExpiredStorageFeeOfBoat(b.getID());

                if (storageFee != null){
                    // convert timestamp to Date
                    dates.add(new Date(storageFee.getDate().getTime()));
                    owners.add(b.getOwner());
                    boats.add(b);
                    // do not send notification if it was previously sent
                    /*if (!NotifyStorageFeeDAO.notificationAlreadySent(storageFee.getID()))
                        NotifyStorageFeeDAO.insertNotifyStorageFee(new NotifyStorageFee(storageFee, false));*/
                }
            }
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK);
            replyMessage.addResult(dates);
            replyMessage.addResult(owners);
            replyMessage.addResult(boats);
        }

        return replyMessage;
    }
}
