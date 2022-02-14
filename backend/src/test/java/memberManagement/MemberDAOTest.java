package memberManagement;

import common.DBUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MemberDAOTest {
    Connection connection;
    @BeforeAll
    static void initAll() throws Exception {
        DBUtil.initDB("jdbc:mariadb://localhost:3307/sailing-club-test", "sailing-club-test");
    }

    @Test
    void insertMember() throws Exception {
        // Set up expected member
        Member expectedMember = new Member("Paolo", "Rossi", "via Prova 10", "XX1233425JFGKHNS", "user1", "pass123");

        // Execute query
        MemberDAO.insertMember(expectedMember);

        // Retrieve inserted member
        Member actualMember = MemberDAO.searchMember(expectedMember.getUsername());

        // Make sure everything is correct
        assertEquals(expectedMember.getUsername(), actualMember.getUsername());
        assertEquals(expectedMember.getPassword(), actualMember.getPassword());
        assertEquals(expectedMember.getAddress(), actualMember.getAddress());
        assertEquals(expectedMember.getFiscalCode(), actualMember.getFiscalCode());
        assertEquals(expectedMember.getName(), actualMember.getName());
    }

    @Test
    void updateMemberUsername() throws Exception {
        // Updates username
        MemberDAO.updateMemberUsername("Rossi", "user1.1");

        // Get updated member object
        Member actualMember = MemberDAO.searchMember("user1.1");

        assertNotNull(actualMember);
    }

    @Test
    void updateMemberPassword() throws Exception {
        // Updates username
        MemberDAO.updateMemberPassword("Rossi", "pass1234");

        // Get updated member object
        Member actualMember = MemberDAO.searchMember("user1.1");

        assertEquals(actualMember.getPassword(), "pass1234");
    }

    @Test
    void searchMember() throws Exception {
        // Set up expected member
        Member expectedMember = new Member("Paolo", "Rossi", "via Prova 10", "XX1233425JFGKHNS", "user1", "pass1234");

        // Execute search
        Member actualMember = MemberDAO.searchMember("user1.1");

        assertEquals(actualMember.getPassword(), expectedMember.getPassword());
        assertEquals(expectedMember.getAddress(), actualMember.getAddress());
        assertEquals(expectedMember.getFiscalCode(), actualMember.getFiscalCode());
        assertEquals(expectedMember.getName(), actualMember.getName());
    }
}
