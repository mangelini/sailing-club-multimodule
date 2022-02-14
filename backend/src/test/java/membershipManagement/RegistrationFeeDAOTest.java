package membershipManagement;

import boatManagement.Boat;
import boatManagement.BoatDAO;
import common.DBUtil;
import feesManagement.RegistrationFee;
import feesManagement.RegistrationFeeDAO;
import memberManagement.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import raceManagement.Race;
import raceManagement.RaceDAO;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegistrationFeeDAOTest {
    Connection connection;
    @BeforeAll
    static void initAll() throws Exception {
        DBUtil.initDB("jdbc:mariadb://localhost:3307/sailing-club-test", "sailing-club-test");
    }

    @Test
    void insertRegistrationFee() throws Exception {
        // Set up expected objects
        Member member = new Member("Paolo", "Rossi", "via Prova 10", "XX1233425JFGKHNS", "user1", "pass123");
        member.setID(1);
        Boat boat = BoatDAO.searchBoatByName("Boat1").get(0);

        Race race = RaceDAO.searchRaceByID(1);

        assertNotNull(boat);
        assertNotNull(race);

        RegistrationFee expectedRegistrationFee = new RegistrationFee(boat, race, 20.0);

        // Execute query
        RegistrationFeeDAO.insertRegistrationFee(expectedRegistrationFee);

        // Check if everything is fine
        ArrayList<RegistrationFee> fees = RegistrationFeeDAO.searchRegistrationFeesByBoat(boat.getID());

        for (RegistrationFee fee : fees) {
            assertEquals(fee.getBoat().getName(), expectedRegistrationFee.getBoat().getName());
            assertEquals(fee.getRace().getName(), fee.getRace().getName());
        }
    }
}
