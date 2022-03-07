package entities;


import java.sql.Timestamp;

public class RegistrationFee extends Fee {
    private final Boat boat;
    private final Race race;

    /**
     * Constructor for the Registration Fee Entity
     * @param boat Boat registered to the race
     * @param race Race of the Registration Fee record
     */
    public RegistrationFee(Boat boat, Race race, Double fee, Timestamp date, String paymentType){
        this.boat = boat;
        this.race = race;
        this.fee = fee;
        this.date = date;
        this.paymentType = paymentType;
    }

    /**
     * Getter for fee of selected race
     * @return Fee
     */
    public Double getFee() {
        return this.fee;
    }

    /**
     * Getter for boat of current Registration Fee record
     * @return Boat
     */
    public Boat getBoat() {
        return this.boat;
    }

    /**
     * Getter for selected race
     * @return Race
     */
    public Race getRace() {
        return this.race;
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

    /**
     * Getter for date of selected Storage Fee record
     * @return Date
     */
    public java.sql.Timestamp getDate() {
        return this.date;
    }

    /**
     * Getter for PaymentType
     * @return paymentType
     */
    public String getPaymentType(){ return this.paymentType; }
}
