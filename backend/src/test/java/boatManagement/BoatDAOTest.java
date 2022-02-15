package boatManagement;

import common.DBUtil;
import dao.BoatDAO;
import entities.Boat;
import entities.Member;
import dao.MemberDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BoatDAOTest {
    Connection connection;
    @BeforeAll
    static void initAll() throws Exception {
        DBUtil.initDB("jdbc:mariadb://localhost:3307/sailing-club-test", "sailing-club-test");
    }

    @Test
    void insertBoat() throws Exception {
        // Set up the expected Boat
        Member member = MemberDAO.searchMember("user1.1");
        Boat expectedBoat = new Boat("Boat1", member, 4.0);

        // Execute query
        BoatDAO.insertBoat(expectedBoat);

        // Check if the transaction went smoothly
        ArrayList<Boat> actualBoats = BoatDAO.searchBoatByName("Boat1");

        for (Boat b: actualBoats) {
            assertEquals(b.getName(), expectedBoat.getName());
        }
    }

    @Test
    void searchBoatByName() throws Exception {
        // Set up the expected Boat
        Member member = MemberDAO.searchMember("user1.1");
        Boat expectedBoat = new Boat("Boat1", member, 4.0);

        // Execute query
        ArrayList<Boat> actualBoats = BoatDAO.searchBoatByName("Boat1");

        for (Boat b: actualBoats) {
            assertEquals(b.getName(), expectedBoat.getName());
            assertEquals(b.getOwner().getID(), expectedBoat.getOwner().getID());
            assertEquals(b.getLength(), expectedBoat.getLength());
        }
    }

    @Test
    void searchBoatsByMember() throws Exception {
        // Set up entities needed for the test
        Member member = new Member("Paolo", "Rossi", "via Prova 10", "XX1233425JFGKHNS", "user1", "pass123");
        member.setID(1);
        Boat boat1 = new Boat("Boat1", member, 4.0);
        boat1.setID(1);

        // Set up expected boatList
        ArrayList<Boat> expectedBoats = new ArrayList<Boat>();
        expectedBoats.add(boat1);

        // Execute query
        ArrayList<Boat> actualBoats = BoatDAO.searchBoatsByMember(member);

        for (int i = 0; i < actualBoats.size(); i++){
            assertEquals(actualBoats.get(i).getName(), expectedBoats.get(i).getName());
            assertEquals(actualBoats.get(i).getOwner().getID(), expectedBoats.get(i).getOwner().getID());
            assertEquals(actualBoats.get(i).getID(), expectedBoats.get(i).getID());
            assertEquals(actualBoats.get(i).getLength(), expectedBoats.get(i).getLength());
        }
    }

    @Test
    void deleteBoat() throws Exception {
        Member member = new Member("Paolo", "Rossi", "via Prova 10", "XX1233425JFGKHNS", "user1", "pass123");
        Boat eliminatedBoat = new Boat("Boat2", member, 4.0);
        eliminatedBoat.setID(1);

        // execute query
        BoatDAO.deleteBoat(1);

        // get updated list of boats
        ArrayList<Boat> actualBoats = BoatDAO.searchBoatsByMember(member);

        // check if boat was deleted correctly
        for (Boat boat : actualBoats){
            assertNotEquals(boat.getID(), eliminatedBoat.getID());
        }
    }
}
