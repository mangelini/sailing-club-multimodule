package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotifyStorageFeeTest {
    Boat boat = null;
    Member member = null;
    NotifyStorageFee notifyStorageFee = null;

    @BeforeEach
    void setUp() {
        member = new Member("John", "Doe", "street Test 45", "AVERGER892364528",
                "user1", "pass");
        member.setID(1);

        boat = new Boat("Boat1", member, 12.0);
        boat.setID(1);

        notifyStorageFee = new NotifyStorageFee(2, boat, false);
    }

    @Test
    void getID() {
        assertEquals(2, notifyStorageFee.getID());
    }

    @Test
    void setID() {
        notifyStorageFee.setID(1);
        assertEquals(1, notifyStorageFee.getID());
    }

    @Test
    void getBoat() {
        assertEquals(1, notifyStorageFee.getBoat().getID());
        assertEquals(1, notifyStorageFee.getBoat().getOwner().getID());
    }

    @Test
    void setBoat() {
        Boat boat2 = new Boat("boat2", member, 3.0);
        boat2.setID(2);

        notifyStorageFee.setBoat(boat2);

        assertEquals(2, notifyStorageFee.getBoat().getID());
        assertEquals(1, notifyStorageFee.getBoat().getOwner().getID());
    }

    @Test
    void isSent() {
        assertEquals(0, notifyStorageFee.isSent());
    }

    @Test
    void setSent() {
        notifyStorageFee.setSent(true);
        assertEquals(1, notifyStorageFee.isSent());
    }
}