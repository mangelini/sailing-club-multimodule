package feesManagement;

import boatManagement.Boat;

import java.io.Serializable;
import java.util.Date;

public class StorageFee implements Serializable {
    private static final long serialVersionUID = 1L;
    private Boat boat;
    private java.sql.Date date;
    private Double fee;
    private Integer ID;

    /**
     * Constructor for the Storage Fee Entity
     * @param boat Boat for which the fee needs to be paid
     * @param date Date of the transaction
     * @param fee Fee for the storage of a boat
     */
    public StorageFee(Boat boat, java.sql.Date date, Double fee){
        this.boat = boat;
        this.date = date;
        this.fee = calculateFee(boat.getLength());
    }

    private Double calculateFee(Double boatLength){
        if (boatLength < 2.0) return 20.0;
        else if (boatLength >= 2.0 && boatLength < 5.0) return 50.0;
        else return 100.0;
    }

    /**
     * Getter for boat of selected Storage Fee record
     * @return Boat
     */
    public Boat getBoat() {
        return this.boat;
    }

    /**
     * Getter for fee of selected Storage Fee record
     * @return Fee
     */
    public Double getFee() {
        return this.fee;
    }

    /**
     * Getter for date of selected Storage Fee record
     * @return Date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Setter for ID property of Race entity
     * @param ID Integer of ID
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * Getter for ID property of Race entity
     * @return ID
     */
    public Integer getID() {
        return ID;
    }
}
