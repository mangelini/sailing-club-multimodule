package entities;

import java.io.Serial;
import java.io.Serializable;

public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer ID;
    private String username;
    private String password;
    private Boolean isAdmin;

    public Employee(){}

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
        this.isAdmin = false;
    }

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

    public Integer isAdmin() {
        if (isAdmin) return 1;
        else return 0;
    }
}
