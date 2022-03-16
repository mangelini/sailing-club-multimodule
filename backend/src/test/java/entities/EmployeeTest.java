package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    Employee emp = null;

    @BeforeEach
    void setUp() {
        emp = new Employee("emp1", "pass");
        emp.setID(1);
    }

    @Test
    void getID() {
        assertEquals(1, emp.getID());
    }

    @Test
    void setID() {
        emp.setID(3);
        assertEquals(3, emp.getID());
    }

    @Test
    void getUsername() {
        assertEquals("emp1", emp.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("pass", emp.getPassword());
    }

    @Test
    void setPassword() {
        emp.setPassword("pass123");
        assertEquals("pass123", emp.getPassword());
    }

    @Test
    void setUsername() {
        emp.setUsername("employee");
        assertEquals("employee", emp.getUsername());
    }

    @Test
    void isAdmin() {
        Employee emp2 = new Employee("emp2", "pass", true);

        assertEquals(1, emp2.isAdmin());
        assertEquals(0, emp.isAdmin());
    }
}