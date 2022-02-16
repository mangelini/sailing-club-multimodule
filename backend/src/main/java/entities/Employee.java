package entities;

import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer ID;
    private String username;
    private String password;

    public Employee(){}

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
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
     * @return ID
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
