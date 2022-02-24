package dao;

import common.DBUtil;
import entities.MembershipFee;
import entities.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MembershipFeeDAO {
  /**
   * Adds new Membership Fee record
   * 
   * @param membershipFee Entity to be added to database
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static synchronized void insertMembershipFee(MembershipFee membershipFee) throws SQLException, ClassNotFoundException {
    String updateStmt = "INSERT INTO membership_fee (Member, Date, Fee) "
        + "VALUES ('" + membershipFee.getMember().getID() + "', '" + membershipFee.getDate()
        + "', '" + membershipFee.getFee() + "')";

    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while INSERT Operation: " + e);
      throw e;
    }
  }

  public static synchronized ArrayList<MembershipFee> searchMembershipFeesByMember(Integer memberID)
      throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM membership_fee WHERE Member='" + memberID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFees = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getEmployeeFromResultSet method and get employee object
      ArrayList<MembershipFee> membershipFees = getMembershipFeesFromResultSet(rsFees);

      return membershipFees;
    } catch (SQLException e) {
      System.out.println("While searching a membership fee, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  private static ArrayList<MembershipFee> getMembershipFeesFromResultSet(ResultSet rs)
      throws SQLException, ClassNotFoundException {
    // Declare an observable List which comprises of Membership Fee objects
    ArrayList<MembershipFee> feesList = new ArrayList<MembershipFee>();

    while (rs.next()) {
      Member member = MemberDAO.searchMemberByID(rs.getInt("Member"));
      java.sql.Date sqlDate = new java.sql.Date(rs.getDate("Date").getTime());

      MembershipFee membershipFee = new MembershipFee(member, sqlDate, rs.getDouble("Fee"));
      membershipFee.setID(rs.getInt("ID"));

      feesList.add(membershipFee);
    }

    return feesList;
  }
}
