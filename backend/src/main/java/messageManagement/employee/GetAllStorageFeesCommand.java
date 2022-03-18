package messageManagement.employee;

import dao.StorageFeeDAO;
import entities.StorageFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.util.ArrayList;

/**
 * Command that returns all storage fees expired and not expired present in database
 */
public class GetAllStorageFeesCommand implements Command {
    @Override
    public Reply execute(Message message) {
        Reply replyMessage = null;
        ArrayList<StorageFee> fees = new ArrayList<>();

        try {
            fees = StorageFeeDAO.getAllFees();
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage  = new Reply(ReplyType.OK, fees);
        }

        return replyMessage;
    }
}
