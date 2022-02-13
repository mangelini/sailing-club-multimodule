package memberManagement;


import boatManagement.Boat;
import common.User;

import java.io.Serializable;
import java.util.ArrayList;

public class Member extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String surname;
    private String address;
    private String fiscalCode;
    private ArrayList<Boat> boats;
    private Integer ID;

    public Member(String name, String surname, String address, String fiscalCode, String Username, String Password){
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.fiscalCode = fiscalCode;
        super.setPassword(Password);
        super.setUsername(Username);
    }

    /**
     * Getter for name field of Member
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for surname field of Member
     * @return surname
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Getter for address field of Member
     * @return address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Getter for fiscal code field of Member
     * @return fiscal code
     */
    public String getFiscalCode() {
        return this.fiscalCode;
    }

    /**
     * Adds a boat to ArrayList
     * @param boat The given boat
     */
    public void addBoat(Boat boat){
        this.boats.add(boat);
    }

    public void removeBoat(Boat boat){
        this.boats.remove(boat);
    }

    /**
     * Getter for member ID's
     * @return ID
     */
    public Integer getID() {
        return this.ID;
    }

    /**
     * Setter for member ID's
     * @param ID
     */
    public void setID(Integer ID){
        this.ID = ID;
    }
}
