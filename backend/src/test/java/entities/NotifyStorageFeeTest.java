package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotifyStorageFeeTest {
    StorageFee storageFee = null;
    Boat boat = null;
    Member member = null;
    NotifyStorageFee notifyStorageFee = null;

    @BeforeEach
    void setUp() {
        member = new Member("John", "Doe", "street Test 45", "AVERGER892364528", "user1", "pass");
        member.setID(1);

        boat = new Boat("Boat1", member, 12.0);
        boat.setID(1);

        storageFee = new StorageFee(boat);
        storageFee.setID(1);

        notifyStorageFee = new NotifyStorageFee(2, storageFee, false);
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
    void getStorageFee() {
        assertEquals(1, notifyStorageFee.getStorageFee().getID());
        assertEquals(1, notifyStorageFee.getStorageFee().getBoat().getID());
        assertEquals(1, notifyStorageFee.getStorageFee().getBoat().getOwner().getID());
        assertEquals(100.0, notifyStorageFee.getStorageFee().getFee());
    }

    @Test
    void setStorageFee() {
        Boat boat2 = new Boat("boat2", member, 3.0);
        boat2.setID(2);
        StorageFee storageFee2 = new StorageFee(boat2);
        storageFee2.setID(2);

        notifyStorageFee.setStorageFee(storageFee2);

        assertEquals(2, notifyStorageFee.getStorageFee().getID());
        assertEquals(2, notifyStorageFee.getStorageFee().getBoat().getID());
        assertEquals(1, notifyStorageFee.getStorageFee().getBoat().getOwner().getID());
        assertEquals(50.0, notifyStorageFee.getStorageFee().getFee());
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