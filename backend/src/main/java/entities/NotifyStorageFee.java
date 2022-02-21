package entities;

import java.io.Serializable;

public class NotifyStorageFee implements Serializable {
    private Integer ID;
    private StorageFee storageFee;
    private boolean sent;

    /**
     * Constructor without ID
     * @param storageFee Storage Fee to be added
     * @param sent Parameter set if the employee already sent the notification
     */
    public NotifyStorageFee(StorageFee storageFee, boolean sent) {
        this.storageFee = storageFee;
        this.sent = sent;
    }

    /**
     * Constructor with ID
     * @param storageFee Storage Fee to be added
     * @param sent Parameter set if the employee already sent the notification
     */
    public NotifyStorageFee(Integer ID, StorageFee storageFee, boolean sent) {
        this.ID = ID;
        this.storageFee = storageFee;
        this.sent = sent;
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
     * Getter for StorageFee of NotifyStorageFee
     * @return
     */
    public StorageFee getStorageFee() {
        return storageFee;
    }

    /**
     * Setter for StorageFee of NotifyStorageFee
     * @param storageFee Fee to be put
     */
    public void setStorageFee(StorageFee storageFee) {
        this.storageFee = storageFee;
    }

    /**
     * Getter for Sent of NotifyStorageFee
     * @return
     */
    public boolean isSent() {
        return sent;
    }

    /**
     * Setter for Sent of NotifyStorageFee
     * @param sent Boolean value to be put
     */
    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
