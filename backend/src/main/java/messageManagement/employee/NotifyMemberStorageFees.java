package messageManagement.employee;

import dao.NotifyStorageFeeDAO;
import entities.NotifyStorageFee;
import entities.StorageFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

public class NotifyMemberStorageFees implements Command {
    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;

        try {
            StorageFee feeToSend = (StorageFee) message.getNewObject();
            // do not send notification if it was previously sent
            if (!NotifyStorageFeeDAO.notificationAlreadySent(feeToSend.getID()))
                NotifyStorageFeeDAO.insertNotifyStorageFee(new NotifyStorageFee(feeToSend, true));
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK);
        }

        return replyMessage;
    }
}
