package raceManagement;

import common.DBUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RaceDAOTest {
    Connection connection;
    @BeforeAll
    static void initAll() throws Exception {
        DBUtil.initDB("jdbc:mariadb://localhost:3307/sailing-club-test", "sailing-club-test");
    }

    @Test
    void insertRace() throws Exception {
        // Set up expected Race
        java.util.Date date=new java.util.Date();
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());

        Race expectedRace = new Race("Race1", "Genova", sqlDate);

        // execute query
        RaceDAO.insertRace(new Race("Race1", "Genova", sqlDate));

        // check that db record is correct
        Race actualRace = RaceDAO.searchRaceByName("Race1");
        assertEquals(actualRace.getName(), expectedRace.getName());
        assertEquals(actualRace.getLocation(), actualRace.getLocation());
    }

    @Test
    void searchRaceByName() throws Exception {
        // Set up expected race
        java.util.Date date=new java.util.Date();
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());

        Race expectedRace = new Race("Race1", "Genova", sqlDate);

        // execute query
        Race actualRace = RaceDAO.searchRaceByName("Race1");

        // check if the query result is correct
        assertEquals(actualRace.getName(), expectedRace.getName());
        assertEquals(actualRace.getLocation(), expectedRace.getLocation());
    }

    @Test
    void searchRaceByID() throws Exception {
        // Set up expected race
        java.util.Date date=new java.util.Date();
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());

        Race expectedRace = new Race("Race1", "Genova", sqlDate);
        expectedRace.setID(1);

        // execute query
        Race actualRace = RaceDAO.searchRaceByID(1);

        // check if the query result is correct
        assertEquals(actualRace.getName(), expectedRace.getName());
        assertEquals(actualRace.getLocation(), expectedRace.getLocation());
    }
}
