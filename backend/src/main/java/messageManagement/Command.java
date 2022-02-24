package messageManagement;

/**
 * Command
 */
public interface Command {
   Reply execute(Message message);
}
