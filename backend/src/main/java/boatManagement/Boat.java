package boatManagement;

import memberManagement.Member;

import java.io.Serializable;

public class Boat implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Member owner;
    private Double length;
    private Integer ID;

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
}