package entities;

import java.io.Serializable;

public class NotifyStorageFee implements Serializable {
    private Integer ID;
    private StorageFee storageFee;
    private Integer sent;

    /**
     * Constructor without ID
     * @param storageFee Storage Fee to be added
     * @param sent Parameter set if the employee already sent the notification
     */
    public NotifyStorageFee(StorageFee storageFee, boolean sent) {
        this.storageFee = storageFee;
        if (!sent) this.sent = 0;
        else this.sent = 1;
    }

    /**
     * Constructor with ID
     * @param storageFee Storage Fee to be added
     * @param sent Parameter set if the employee already sent the notification
     */
    public NotifyStorageFee(Integer ID, StorageFee storageFee, boolean sent) {
        this.ID = ID;
        this.storageFee = storageFee;
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
     * Getter for StorageFee of NotifyStorageFee
     * @return StorageFee
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
