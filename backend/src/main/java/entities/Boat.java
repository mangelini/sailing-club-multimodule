package entities;

import java.io.Serial;
import java.io.Serializable;

public class Boat implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private final Member owner;
    private final Double length;
    private Integer ID;
    private final boolean enabled;

    /**
     * Constructor of Boat entity
     * @param name Name of the boat
     * @param owner Owner of the boat
     * @param length Length of the boat
     */
    public Boat(String name, Member owner, Double length){
        this.name = name;
        this.length = length;
        this.owner = owner;
        this.enabled = true;
    }

    /**
     * Constructor of Boat entity with Enabled property
     * @param name Name of the boat
     * @param owner Owner of the boat
     * @param length Length of the boat
     * @param enabled If the boat was erased in db
     */
    public Boat(String name, Member owner, Double length, boolean enabled){
        this.name = name;
        this.length = length;
        this.owner = owner;
        this.enabled = enabled;
    }

    /**
     * Getter for the name of the boat
     * @return name of the boat
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter the owner of the boat
     * @return owner of the boat
     */
    public Member getOwner(){
        return this.owner;
    }

    /**
     * Getter the length of the boat
     * @return length of the boat
     */
    public Double getLength(){
        return this.length;
    }

    /**
     * Getter the ID of the boat
     * @return ID of the boat
     */
    public Integer getID(){
        return this.ID;
    }

    /**
     * Setter for ID of boat
     * @param ID of boat
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * Getter for Enabled of the boat
     * @return Enabled of the boat
     */
    public Integer isEnabled() {
        if (enabled) return 1;
        else return 0;
    }
}