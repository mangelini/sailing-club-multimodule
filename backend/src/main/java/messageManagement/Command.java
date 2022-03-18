package messageManagement;

/**
 * Command Interface
 */
public interface Command {
    /**
     * Process request sent by client by combining the use of multiple DAOs and return the correct
     * reply to client
     * @param message Message sent by client containing the user who sent it, and all required parameters
     *                to successfully elaborate the request
     * @return Reply with status code and optionally the request result
     */
    Reply execute(Message message);
}
