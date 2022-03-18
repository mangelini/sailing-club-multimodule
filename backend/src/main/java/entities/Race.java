package entities;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Model class for Race entity in database
 */
public class Race implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private final String location;
    private final java.sql.Timestamp date;
    private Integer ID;
    private final Integer expired;

    /**
     * Constructor for Race Entity without ID
     * @param name Name of the Race
     * @param location Where the race is being held
     * @param date Date of creation
     * @param expired True if the race is expired, False otherwise
     */
    public Race(String name, String location, java.sql.Timestamp date, boolean expired){
        this.name = name;
        this.location = location;
        this.date = date;
        if (!expired) this.expired = 0;
        else this.expired = 1;
    }

    /**
     * Constructor without timestamp and ID
     * @param name Name of race
     * @param location Where the race is taking place
     * @param expired True if the race is expired, False otherwise
     */
    public Race(String name, String location, boolean expired){
        this.name = name;
        this.location = location;
        this.date = new Timestamp(System.currentTimeMillis());
        if (!expired) this.expired = 0;
        else this.expired = 1;
    }

    /**
     * Base constructor for Race entity
     * @param ID ID of race
     * @param name Name of race
     * @param location Where the race is taking place
     * @param date Date of creation
     * @param expired True if the race is expired, False otherwise
     */
    public Race(Integer ID, String name, String location, java.sql.Timestamp date, boolean expired){
        this.ID = ID;
        this.name = name;
        this.location = location;
        this.date = date;
        if (!expired) this.expired = 0;
        else this.expired = 1;
    }

    /**
     * Getter for name of the race
     * @return Name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for date of the race
     * @return Date
     */
    public Timestamp getDate() {
        return this.date;
    }

    /**
     * Getter for location of the race
     * @return Location
     */
    public String getLocation() {
        return this.location;
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
     * Getter for expired property of Race entity
     * @return Expired
     */
    public Integer isExpired() {
        return this.expired;
    }
}
