package messageManagement;

public class ConnectionEstablishedCommand implements Command {
    @Override
    public Reply execute(Message m) {
        Reply replyMessage = new Reply(ReplyType.CONNECTION_ESTABLISHED);
        return replyMessage;
    }
}
