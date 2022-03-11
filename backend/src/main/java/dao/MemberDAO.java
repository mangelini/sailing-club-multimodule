package dao;

import common.DBUtil;
import entities.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
  /**
   * Creates a new member record in the Member DB Table
   * 
   * @param member Member objects that will be transformed in a DB record
   */
  public static synchronized int insertMember(Member member) throws SQLException, ClassNotFoundException {
    int ID = 0;

    String updateStmt = "INSERT INTO member (Name, Surname, Address, FiscalCode, Username, Password) "
        + "VALUES ('" + member.getName() + "', '" + member.getSurname()
        + "', '" + member.getAddress() + "', '" + member.getFiscalCode() + "', '"
        + member.getUsername() + "', '" + member.getPassword() + "')";

    try {
      ID = DBUtil.dbExecuteUpdate(updateStmt);
    } catch (Exception e) {
      System.out.print("Error occurred while INSERT Operation: " + e);
      throw e;
    }

    return ID;
  }

  /**
   * Gets all members of the club
   * 
   * @return A List of members
   */
  public static synchronized ArrayList<Member> getAllMembers() throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM member";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsMembers = DBUtil.dbExecuteQuery(selectStmt);

      return getMemberList(rsMembers);
    } catch (SQLException e) {
      System.out.println("SQL select operation has been failed: " + e);
      // Return exception
      throw e;
    }
  }

  /**
   * Search for specified member in db
   * @param clientMember Member that we want to authenticate
   * @return Member with all fields initialized
   */
  public static synchronized Member logIn(Member clientMember) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM member WHERE Username='" + clientMember.getUsername() +
            "' AND Password='" + clientMember.getPassword() + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsMem = DBUtil.dbExecuteQuery(selectStmt);

      return getMemberFromResultSet(rsMem);
    } catch (SQLException e) {
      System.out.println("While searching a member with " + clientMember.getUsername() + " username, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  /**
   * Search for a specific member inside Member's DB table
   * 
   * @param Username Surname of target member
   * @return Member object
   */
  public static synchronized Member searchMember(String Username) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM member WHERE Username='" + Username + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsMem = DBUtil.dbExecuteQuery(selectStmt);

      return getMemberFromResultSet(rsMem);
    } catch (SQLException e) {
      System.out.println("While searching a member with " + Username + " username, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  /**
   * Search for a specific member inside Member's DB table
   * 
   * @param ID ID's of target member
   * @return Member object
   */
  public static synchronized Member searchMemberByID(Integer ID) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM member WHERE ID='" + ID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsMem = DBUtil.dbExecuteQuery(selectStmt);

      return getMemberFromResultSet(rsMem);
    } catch (SQLException e) {
      System.out.println("While searching a member with " + ID + " ID, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  /**
   * Updates the username of specified Member
   * 
   * @param Surname     Surname of Member
   * @param newUsername Updated username
   */
  public static synchronized void updateMemberUsername(String Surname, String newUsername)
      throws SQLException, ClassNotFoundException {
    // Declare a UPDATE statement
    String updateStmt = "UPDATE member" +
        " SET Username ='" + newUsername + "'" +
        " WHERE Surname ='" + Surname + "'";

    // Execute UPDATE operation
    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while UPDATE Operation: " + e);
      throw e;
    }
  }

  /**
   * Updates the password of specified Member
   * 
   * @param Surname     Surname of Member
   * @param newPassword Updated password
   */
  public static synchronized void updateMemberPassword(String Surname, String newPassword)
      throws SQLException, ClassNotFoundException {
    // Declare a UPDATE statement
    String updateStmt = "UPDATE member" +
        " SET Password ='" + newPassword + "'" +
        " WHERE Surname ='" + Surname + "'";

    // Execute UPDATE operation
    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while UPDATE Operation: " + e);
      throw e;
    }
  }

  private static ArrayList<Member> getMemberList(ResultSet rs) throws SQLException {
    // Declare an observable List which comprises Member objects
    ArrayList<Member> memList = new ArrayList<>();

    while (rs.next()) {
      Member member = new Member(
              rs.getString("Name"), rs.getString("Surname"), rs.getString("Address"),
              rs.getString("FiscalCode"), rs.getString("Username"), rs.getString("Password"));
      member.setID(rs.getInt("ID"));

      memList.add(member);
    }

    return memList;
  }

  private static Member getMemberFromResultSet(ResultSet rsMem) throws SQLException {
    Member member = null;

    if (rsMem.next()) {
      member = new Member(rsMem.getString("Name"), rsMem.getString("Surname"),
              rsMem.getString("Address"), rsMem.getString("FiscalCode"),
              rsMem.getString("Username"), rsMem.getString("Password"));

      member.setID(rsMem.getInt("ID"));
    }

    return member;
  }
}
