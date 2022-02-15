package entities;

import java.io.Serializable;
import java.util.Date;

public class Race implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String location;
    private java.sql.Date date;
    private Integer ID;

    /**
     * Constructor for Race Entity
     * @param name Name of the Race
     * @param location Where the race is being held
     * @param date When the race starts
     */
    public Race(String name, String location, java.sql.Date date){
        this.name = name;
        this.location = location;
        this.date = date;
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
    public Date getDate() {
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
}
