package entities;

import java.io.Serializable;

public class RegistrationFee implements Serializable {
    private static final long serialVersionUID = 1L;
    private Boat boat;
    private Race race;
    private Double fee;
    private Integer ID;

    /**
     * Constructor for the Registration Fee Entity
     * @param boat Boat registered to the race
     * @param race Race of the Registration Fee record
     */
    public RegistrationFee(Boat boat, Race race, Double fee){
        this.boat = boat;
        this.race = race;
        this.fee = fee;
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
}
