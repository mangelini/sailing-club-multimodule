package membershipManagement;

import boatManagement.Boat;
import common.DBUtil;
import feesManagement.MembershipFee;
import feesManagement.MembershipFeeDAO;
import memberManagement.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MembershipFeeDAOTest {
    Connection connection;
    @BeforeAll
    static void initAll() throws Exception {
        DBUtil.initDB("jdbc:mariadb://localhost:3307/sailing-club-test", "sailing-club-test");
    }

    @Test
    void insertMembershipFee() throws Exception{
        // Set up expected data
        Member member = new Member("Pippo", "Prova", "via Prova 20", "XX1233425JFGKHNS", "user1.1", "pass1234");
        member.setID(1);

        java.util.Date date=new java.util.Date();
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());

        MembershipFee expectedFee = new MembershipFee(member, sqlDate, 219.0);

        // execute query
        MembershipFeeDAO.insertMembershipFee(new MembershipFee(member, sqlDate, 219.0));

        // check if fee was added correctly
        ArrayList<MembershipFee> actualFees = MembershipFeeDAO.searchMembershipFeesByMember(member.getID());

        for (MembershipFee fee : actualFees){
            assertEquals(fee.getMember().getID(), expectedFee.getMember().getID());
            assertEquals(fee.getFee(), expectedFee.getFee());
        }
    }

    @Test
    void searchMembershipFeesByMember() throws Exception {
        // Set up expected objects
        Member member = new Member("Pippo", "Prova", "via Prova 20", "XX1233425JFGKHNS", "user1.1", "pass1234");
        member.setID(1);

        java.util.Date date=new java.util.Date();
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());

        MembershipFee expectedFee = new MembershipFee(member, sqlDate, 219.0);
        expectedFee.setID(1);

        // Execute query
        ArrayList<MembershipFee> actualFees = MembershipFeeDAO.searchMembershipFeesByMember(member.getID());

        // Check if returned items are right
        for (MembershipFee fee : actualFees){
            assertEquals(fee.getMember().getID(), expectedFee.getMember().getID());
            assertEquals(fee.getFee(), expectedFee.getFee());
        }
    }
}
