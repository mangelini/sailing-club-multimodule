package entities;

import common.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class NotifyMembershipFeeTest {
    NotifyMembershipFee notifyMembershipFee = null;
    MembershipFee membershipFee = null;
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    Member member = null;

    @BeforeEach
    void setUp() {
        member = new Member("John", "Doe", "street Test 45", "AVERGER892364528", "user1", "pass");
        member.setID(1);

        membershipFee = new MembershipFee(member, timestamp, Constants.MEMBERSHIP_FEE);
        membershipFee.setID(1);

        notifyMembershipFee = new NotifyMembershipFee(1, membershipFee, false);
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
    void getMembershipFee() {
        assertEquals(1, notifyMembershipFee.getMembershipFee().getID());
        assertEquals(1, notifyMembershipFee.getMembershipFee().getMember().getID());
        assertEquals(timestamp, notifyMembershipFee.getMembershipFee().getDate());
        assertEquals(Constants.MEMBERSHIP_FEE, notifyMembershipFee.getMembershipFee().getFee());
    }

    @Test
    void setMembershipFee() {
        Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
        MembershipFee membershipFee2 = new MembershipFee(member, timestamp2, Constants.MEMBERSHIP_FEE);
        membershipFee2.setID(2);

        notifyMembershipFee.setMembershipFee(membershipFee2);

        assertEquals(2, notifyMembershipFee.getMembershipFee().getID());
        assertEquals(1, notifyMembershipFee.getMembershipFee().getMember().getID());
        assertEquals(timestamp2, notifyMembershipFee.getMembershipFee().getDate());
        assertEquals(Constants.MEMBERSHIP_FEE, notifyMembershipFee.getMembershipFee().getFee());
    }

    @Test
    void getSent() {
        assertEquals(0, notifyMembershipFee.getSent());
    }

    @Test
    void setSent() {
        notifyMembershipFee.setSent(true);
        assertEquals(1, notifyMembershipFee.getSent());
    }
}