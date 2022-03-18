package messageManagement;

/**
 * Command used at the initial handshake between client and server
 */
public class ConnectionEstablishedCommand implements Command {
    @Override
    public Reply execute(Message m) {
        return new Reply(ReplyType.CONNECTION_ESTABLISHED);
    }
}
