package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class NotifyMembershipFeeTest {
    NotifyMembershipFee notifyMembershipFee = null;
    Member member = null;

    @BeforeEach
    void setUp() {
        member = new Member("John", "Doe", "street Test 45", "AVERGER892364528",
                "user1", "pass");
        member.setID(1);

        notifyMembershipFee = new NotifyMembershipFee(1, member, false);
    }

    @Test
    void getID() {
        assertEquals(1, notifyMembershipFee.getID());
    }

    @Test
    void setID() {
        notifyMembershipFee.setID(4);
        assertEquals(4, notifyMembershipFee.getID());
    }

    @Test
    void getMember() {
        assertEquals(1, notifyMembershipFee.getMember().getID());
        assertEquals("John", notifyMembershipFee.getMember().getName());
        assertEquals("Doe", notifyMembershipFee.getMember().getSurname());
    }

    @Test
    void setMembershipFee() {
        Member mem2 = new Member("Paolo", "Rossi", "via Roma 42", "BP87T5P4TY4TNFU8",
                "user2", "pass");
        mem2.setID(2);

        notifyMembershipFee.setMember(mem2);

        assertEquals(2, notifyMembershipFee.getMember().getID());
        assertEquals("Paolo", notifyMembershipFee.getMember().getName());
    }

    @Test
    void getSent() {
        assertEquals(0, notifyMembershipFee.getSent());
    }
}