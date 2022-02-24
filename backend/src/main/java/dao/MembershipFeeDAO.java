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

  public static synchronized MembershipFee searchMembershipFeeByMember(Integer memberID)
      throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM membership_fee WHERE Member='" + memberID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFees = DBUtil.dbExecuteQuery(selectStmt);

      MembershipFee membershipFee = getMembershipFeeFromResultSet(rsFees);

      return membershipFee;
    } catch (SQLException e) {
      System.out.println("While searching a membership fee, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  public static synchronized MembershipFee searchMembershipFeeByID(Integer feeID)
          throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM membership_fee WHERE ID='" + feeID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFee = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getEmployeeFromResultSet method and get employee object
      MembershipFee membershipFee = getMembershipFeeFromResultSet(rsFee);

      return membershipFee;
    } catch (SQLException e) {
      System.out.println("While searching a membership fee, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  public static synchronized MembershipFee searchExpiredMembershipFeeByMember(Integer memberID)
          throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM membership_fee WHERE timestampdiff(second, Date, NOW()) > 30 AND Member='"
            + memberID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFees = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getEmployeeFromResultSet method and get employee object
      MembershipFee membershipFee = getMembershipFeeFromResultSet(rsFees);

      return membershipFee;
    } catch (SQLException e) {
      System.out.println("While searching a membership fee, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  public static synchronized void updateMembershipFee(Integer feeID)
          throws SQLException, ClassNotFoundException {
    String updateStmt = "UPDATE membership_fee SET Date=NOW() WHERE ID='" + feeID + "'";

    // Execute UPDATE operation
    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while UPDATE Operation: " + e);
      throw e;
    }
  }

  private static ArrayList<MembershipFee> getMembershipFeesFromResultSet(ResultSet rs)
      throws SQLException, ClassNotFoundException {
    // Declare an observable List which comprises of Membership Fee objects
    ArrayList<MembershipFee> feesList = new ArrayList<MembershipFee>();

    while (rs.next()) {
      Member member = MemberDAO.searchMemberByID(rs.getInt("Member"));

      MembershipFee membershipFee = new MembershipFee(member, rs.getTimestamp("Date"), rs.getDouble("Fee"));
      membershipFee.setID(rs.getInt("ID"));

      feesList.add(membershipFee);
    }

    return feesList;
  }

  private static MembershipFee getMembershipFeeFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
    MembershipFee membershipFee = null;

    if (rs.next()){
      Member member = MemberDAO.searchMemberByID(rs.getInt("Member"));

      membershipFee = new MembershipFee(member, rs.getTimestamp("Date"), rs.getDouble("Fee"));
      membershipFee.setID(rs.getInt("ID"));
    }

    return membershipFee;
  }
}
