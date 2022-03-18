package messageManagement.employee;

import dao.NotifyStorageFeeDAO;
import entities.Boat;
import entities.NotifyStorageFee;
import messageManagement.Command;
import messageManagement.Message;
import messageManagement.Reply;
import messageManagement.ReplyType;

/**
 * Command that adds a record to NotifyStorageFee table of the boat which has
 * its storage fee expired
 */
public class NotifyMemberStorageFees implements Command {
    @Override
    public synchronized Reply execute(Message message) {
        Reply replyMessage = null;

        try {
            Boat boatToNotify = (Boat) message.getNewObject();

            NotifyStorageFeeDAO.insertNotifyStorageFee(new NotifyStorageFee(boatToNotify, true));
        } catch (Exception e) {
            replyMessage = new Reply(ReplyType.ERROR);
            return replyMessage;
        } finally {
            replyMessage = new Reply(ReplyType.OK);
        }

        return replyMessage;
    }
}
