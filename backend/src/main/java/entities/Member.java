package entities;


import java.io.Serializable;
import java.util.ArrayList;

public class Member implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String surname;
    private String address;
    private String fiscalCode;
    private ArrayList<Boat> boats;
    private Integer ID;
    private String username;
    private String password;

    public Member(String name, String surname, String address, String fiscalCode, String Username, String Password){
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.fiscalCode = fiscalCode;
        this.username = Username;
        this.password = Password;
    }

    public Member(String username, String password){
        this.username = username;
        this.password = password;
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

    /**
     * Removes the specified boat
     * @param boat Given boat
     */
    public void removeBoat(Boat boat){
        this.boats.remove(boat);
    }

    /**
     * Returns the boat to add to db
     * @return Boat
     */
    public Boat getBoatToAdd() {
        return this.boats.get(0);
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

    /**
     * Getter for username
     * @return Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for password
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter for username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
