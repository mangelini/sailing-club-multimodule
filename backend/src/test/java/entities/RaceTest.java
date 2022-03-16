package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class RaceTest {
    Race race = null;
    Timestamp date = new Timestamp(System.currentTimeMillis());

    @BeforeEach
    void setUp() {
        race = new Race(1, "race1", "Milan", date, false);
    }

    @Test
    void getName() {
        assertEquals("race1", race.getName());
    }

    @Test
    void getDate() {
        assertEquals(date, race.getDate());
    }

    @Test
    void getLocation() {
        assertEquals("Milan", race.getLocation());
    }

    @Test
    void setID() {
        race.setID(3);
        assertEquals(3, race.getID());
    }

    @Test
    void getID() {
        assertEquals(1, race.getID());
    }

    @Test
    void isExpired() {
        assertEquals(0, race.isExpired());
    }
}