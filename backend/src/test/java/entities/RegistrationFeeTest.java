package entities;

import common.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationFeeTest {
    Boat boat = null;
    Race race = null;
    RegistrationFee registrationFee = null;
    Member owner = null;
    Timestamp date = new Timestamp(System.currentTimeMillis());

    @BeforeEach
    void setUp() {
        owner = new Member("John", "Doe", "street Test 45", "AVERGER892364528",
                "user1", "pass");
        owner.setID(1);

        boat = new Boat("Boat1", owner, 12.0);
        boat.setID(2);

        race = new Race(1, "race1", "Milan", date, false);

        registrationFee = new RegistrationFee(boat, race, Constants.REGISTRATION_FEE,
                new Timestamp(System.currentTimeMillis()),"Bank Transfer");
        registrationFee.setID(1);
    }

    @Test
    void getFee() {
        assertEquals(Constants.REGISTRATION_FEE, registrationFee.getFee());
    }

    @Test
    void getBoat() {
        assertEquals(2, registrationFee.getBoat().getID());
        assertEquals(1, registrationFee.getBoat().getOwner().getID());
        assertEquals("Boat1", registrationFee.getBoat().getName());
        assertEquals(12.0, registrationFee.getBoat().getLength());
    }

    @Test
    void getRace() {
        assertEquals(1, race.getID());
        assertEquals("race1", race.getName());
        assertEquals("Milan", race.getLocation());
    }

    @Test
    void setID() {
        registrationFee.setID(2);
        assertEquals(2, registrationFee.getID());
    }

    @Test
    void getID() {
        assertEquals(1, registrationFee.getID());
    }

    @Test
    void checkPaymentType(){
        assertEquals("Bank Transfer", registrationFee.getPaymentType());
    }
}