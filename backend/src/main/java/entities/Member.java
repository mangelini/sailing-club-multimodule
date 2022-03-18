package entities;

import java.io.Serial;
import java.io.Serializable;

/**
 * Model class for Member entity in database
 */
public class Member implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private String surname;
    private String address;
    private String fiscalCode;
    private Integer ID;
    private String username;
    private String password;

    /**
     * Base constructor for Member entity
     * @param name Name of member
     * @param surname Surname of member
     * @param address Address of member
     * @param fiscalCode Fiscal code of member
     * @param Username Username of member
     * @param Password Password of member
     */
    public Member(String name, String surname, String address, String fiscalCode, String Username, String Password){
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.fiscalCode = fiscalCode;
        this.username = Username;
        this.password = Password;
    }

    /**
     * Constructor for Member with only username and password
     * @param username Username of member
     * @param password Password of member
     */
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
     * Getter for member ID's
     * @return ID
     */
    public Integer getID() {
        return this.ID;
    }

    /**
     * Setter for member ID's
     * @param ID ID of member
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
     * @param username Username of member
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
