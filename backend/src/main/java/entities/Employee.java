package entities;

import java.io.Serial;
import java.io.Serializable;

/**
 * Model class for Employee entity in database
 */
public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer ID;
    private String username;
    private String password;
    private Boolean isAdmin;

    /**
     * Base constructor of Employee entity
     */
    public Employee(){}

    /**
     * Constructor of Employee entity
     * @param username Username of employee
     * @param password Password of employee
     */
    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
        this.isAdmin = false;
    }

    /**
     * Special admin constructor of Employee entity
     * @param username Username of admin
     * @param password Password of admin
     * @param isAdmin True if employee is an admin, False otherwise
     */
    public Employee(String username, String password, boolean isAdmin){
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    /**
     * Getter for ID field of Employee
     * @return ID
     */
    public Integer getID(){
        return ID;
    }

    /**
     * Setter for ID field of Employee
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
     * @param username Username that will be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for isAdmin property
     * @return 1 if employee is an admin, 0 otherwise
     */
    public Integer isAdmin() {
        if (isAdmin) return 1;
        else return 0;
    }
}
