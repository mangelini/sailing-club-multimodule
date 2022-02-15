package employeeManagement;

import common.DBUtil;
import dao.EmployeeDAO;
import entities.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDAOTest {
    Connection connection;
    @BeforeAll
    static void initAll() throws Exception {
        DBUtil.initDB("jdbc:mariadb://localhost:3307/sailing-club-test", "sailing-club-test");
    }

    @Test
    void insertEmployee() throws Exception {
        // Set up the expected employee
        Employee expectedEmployee = new Employee();
        expectedEmployee.setUsername("emp1");
        expectedEmployee.setPassword("pass123");

        // Execute query
        EmployeeDAO.insertEmployee(expectedEmployee);

        // Check if the transaction went smoothly
        Employee actualEmployee = EmployeeDAO.searchEmployee(expectedEmployee.getUsername());

        assertEquals(expectedEmployee.getUsername(), actualEmployee.getUsername());
        assertEquals(expectedEmployee.getPassword(), actualEmployee.getPassword());
    }

    @Test
    void updateEmployeeUsername() throws Exception {
        // creates updated employee
        Employee expectedEmployee = new Employee();
        expectedEmployee.setUsername("emp1.1");

        // updates db record
        EmployeeDAO.updateEmployeeUsername(1, "emp1.1");

        // retrieve updated element
        Employee actualEmployee = EmployeeDAO.searchEmployee("emp1.1");

        // check validity of update
        assertEquals(expectedEmployee.getUsername(), actualEmployee.getUsername());
    }

    @Test
    void updateEmployeePassword() throws Exception {
        // creates updated employee
        Employee expectedEmployee = new Employee();
        expectedEmployee.setPassword("pass1.1");

        // updates db record
        EmployeeDAO.updateEmployeePassword(1, "pass1.1");

        // retrieve updated element
        Employee actualEmployee = EmployeeDAO.searchEmployee("emp1.1");

        // check validity of update
        assertEquals(expectedEmployee.getPassword(), actualEmployee.getPassword());
    }

    @Test
    void searchEmployee() throws Exception {
        // Create the expected Employee
        Employee expectedEmployee = new Employee();
        expectedEmployee.setUsername("emp1");
        expectedEmployee.setPassword("pass123");

        // Execute query
        Employee actualEmployee = EmployeeDAO.searchEmployee(expectedEmployee.getUsername());

        assertEquals(expectedEmployee.getUsername(), actualEmployee.getUsername());
    }
}