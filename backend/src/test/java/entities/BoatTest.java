package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoatTest {
    Boat boat = null;
    Member owner;

    @BeforeEach
    void setUp() {
        owner = new Member("John", "Doe", "street Test 45", "AVERGER892364528", "user1", "pass");
        owner.setID(1);

        boat = new Boat("Boat1", owner, 12.0);
        boat.setID(2);
    }

    @Test
    void getName() {
        assertEquals("Boat1", boat.getName());
    }

    @Test
    void getOwner() {
        assertEquals(1, boat.getOwner().getID());
        assertEquals("John", boat.getOwner().getName());
        assertEquals("Doe", boat.getOwner().getSurname());
        assertEquals("street Test 45", boat.getOwner().getAddress());
        assertEquals("user1", boat.getOwner().getUsername());
        assertEquals("pass", boat.getOwner().getPassword());
    }

    @Test
    void getLength() {
        assertEquals(12.0, boat.getLength());
    }

    @Test
    void getID() {
        assertEquals(2, boat.getID());
    }

    @Test
    void setID() {
        boat.setID(3);
        assertEquals(3, boat.getID());
    }
}