package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class StorageFeeTest {
    StorageFee storageFee = null;
    Boat boat = null;
    Member member = null;

    @BeforeEach
    void setUp() {
        member = new Member("John", "Doe", "street Test 45", "AVERGER892364528",
                "user1", "pass");
        member.setID(1);

        boat = new Boat("Boat1", member, 12.0);
        boat.setID(1);

        storageFee = new StorageFee(boat, new Timestamp(System.currentTimeMillis()), "Credit Card");
        storageFee.setID(1);
    }

    @Test
    void getBoat() {
        assertEquals(1, storageFee.getBoat().getID());
        assertEquals(1, storageFee.getBoat().getOwner().getID());
        assertEquals("Boat1", storageFee.getBoat().getName());
        assertEquals(12.0, storageFee.getBoat().getLength());
    }

    @Test
    void getFee() {
        assertEquals(100.0, storageFee.getFee());
    }

    @Test
    void setID() {
        storageFee.setID(12);
        assertEquals(12, storageFee.getID());
    }

    @Test
    void getID() {
        assertEquals(1, storageFee.getID());
    }

    @Test
    void checkPaymentType(){
        assertEquals("Credit Card", storageFee.getPaymentType());
    }
}