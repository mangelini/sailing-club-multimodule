package employeeManagement;

import common.User;

import java.io.Serializable;

public class Employee extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer ID;

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
}
