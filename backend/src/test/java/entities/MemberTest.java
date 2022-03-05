package entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    Member member = null;

    @BeforeEach
    void init() {
        member = new Member("John", "Doe", "street Test 45", "AVERGER892364528", "user1", "pass");
        member.setID(1);
    }

    @Test
    void getName() {
        assertEquals("John", member.getName());
    }

    @Test
    void getSurname() {
        assertEquals("Doe", member.getSurname());
    }

    @Test
    void getAddress() {
        assertEquals("street Test 45", member.getAddress());
    }

    @Test
    void getFiscalCode() {
        assertEquals("AVERGER892364528", member.getFiscalCode());
    }

    @Test
    void getID() {
        assertEquals(1, member.getID());
    }

    @Test
    void setID() {
        member.setID(2);
        assertEquals(2, member.getID());
    }

    @Test
    void getUsername() {
        assertEquals("user1", member.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("pass", member.getPassword());
    }

    @Test
    void setPassword() {
        member.setPassword("pass123");
        assertEquals("pass123", member.getPassword());
    }

    @Test
    void setUsername() {
        member.setUsername("user0");
        assertEquals("user0", member.getUsername());
    }
}