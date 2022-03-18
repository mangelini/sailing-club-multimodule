package entities;

import java.io.Serializable;

/**
 * Model class for NotifyStorageFee entity in database
 */
public class NotifyStorageFee implements Serializable {
    private Integer ID;
    private Boat boat;
    private Integer sent;

    /**
     * Constructor without ID
     * @param boat Boat to be added
     * @param sent Parameter set if the employee already sent the notification
     */
    public NotifyStorageFee(Boat boat, boolean sent) {
        this.boat = boat;
        if (!sent) this.sent = 0;
        else this.sent = 1;
    }

    /**
     * Constructor with ID
     * @param boat Storage Fee to be added
     * @param sent Parameter set if the employee already sent the notification
     */
    public NotifyStorageFee(Integer ID, Boat boat, boolean sent) {
        this.ID = ID;
        this.boat = boat;
        if (!sent) this.sent = 0;
        else this.sent = 1;
    }

    /**
     * Getter for ID of NotifyStorageFee
     * @return ID
     */
    public Integer getID() {
        return ID;
    }

    /**
     * Setter for ID of NotifyStorageFee
     * @param ID ID to be put
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * Getter for Boat of NotifyStorageFee
     * @return Boat
     */
    public Boat getBoat() {
        return boat;
    }

    /**
     * Setter for Boat of NotifyStorageFee
     * @param boat Boat to be put
     */
    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    /**
     * Getter for Sent of NotifyStorageFee
     * @return Sent
     */
    public Integer isSent() {
        return sent;
    }

    /**
     * Setter for Sent of NotifyStorageFee
     * @param sent Boolean value to be put
     */
    public void setSent(boolean sent) {
        if (!sent) this.sent = 0;
        else this.sent = 1;
    }
}