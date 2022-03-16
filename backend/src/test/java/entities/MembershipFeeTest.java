package entities;

import common.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class MembershipFeeTest {
    Member member = null;
    MembershipFee membershipFee = null;
    java.sql.Timestamp date = new Timestamp(System.currentTimeMillis());

    @BeforeEach
    void setUp(){
        member = new Member("John", "Doe", "street Test 45", "AVERGER892364528", "user1", "pass");
        member.setID(1);

        membershipFee = new MembershipFee(member, date, Constants.MEMBERSHIP_FEE, "Credit Card");
        membershipFee.setID(1);
    }

    @Test
    void getDate() {
        assertEquals(date, membershipFee.getDate());
    }

    @Test
    void getFee() {
        assertEquals(100.0, membershipFee.getFee());
    }

    @Test
    void getMember() {
        assertEquals(1, membershipFee.getMember().getID());
        assertEquals("John", membershipFee.getMember().getName());
        assertEquals("Doe", membershipFee.getMember().getSurname());
        assertEquals("street Test 45", membershipFee.getMember().getAddress());
        assertEquals("user1", membershipFee.getMember().getUsername());
        assertEquals("pass", membershipFee.getMember().getPassword());
    }

    @Test
    void setID() {
        membershipFee.setID(2);
        assertEquals(2, membershipFee.getID());
    }

    @Test
    void getID() {
        assertEquals(1, membershipFee.getID());
    }

    @Test
    void checkPaymentType() {
        assertEquals("Credit Card", membershipFee.getPaymentType());
    }
}