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
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static boolean insertMember(Member member) throws SQLException, ClassNotFoundException {
    boolean memAdded = false;

    String updateStmt = "INSERT INTO member (Name, Surname, Address, FiscalCode, Username, Password) "
        + "VALUES ('" + member.getName() + "', '" + member.getSurname()
        + "', '" + member.getAddress() + "', '" + member.getFiscalCode() + "', '"
        + member.getUsername() + "', '" + member.getPassword() + "')";

    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while INSERT Operation: " + e);
      throw e;
    } finally {
      memAdded = true;
    }

    return memAdded;
  }

  /**
   * Gets all members of the club
   * 
   * @return A List of members
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ArrayList<Member> getAllMembers() throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM member";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsMembers = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getMemberList method and get member object
      ArrayList<Member> memList = getMemberList(rsMembers);

      // Return employee object
      return memList;
    } catch (SQLException e) {
      System.out.println("SQL select operation has been failed: " + e);
      // Return exception
      throw e;
    }
  }

  private static ArrayList<Member> getMemberList(ResultSet rs) throws SQLException, ClassNotFoundException {
    // Declare a observable List which comprises of Member objects
    ArrayList<Member> memList = new ArrayList<Member>();

    while (rs.next()) {
      Member member = new Member(
          rs.getString("Name"), rs.getString("Surname"), rs.getString("Address"),
          rs.getString("FiscalCode"), rs.getString("Username"), rs.getString("Password"));
      member.setID(rs.getInt("ID"));

      memList.add(member);
    }

    return memList;
  }

  /**
   * Search for specified member in db
   * @param clientMember Member that we want to authenticate
   * @return Member with all fields initialized
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static Member logIn(Member clientMember) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM member WHERE Username='" + clientMember.getUsername() +
            "' AND Password='" + clientMember.getPassword() + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsMem = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getMemberFromResultSet method and get member object
      Member actualMember = getMemberFromResultSet(rsMem);

      return actualMember;
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
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static Member searchMember(String Username) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM member WHERE Username='" + Username + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsMem = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getMemberFromResultSet method and get member object
      Member member = getMemberFromResultSet(rsMem);

      return member;
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
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static Member searchMemberByID(Integer ID) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM member WHERE ID='" + ID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsMem = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getMemberFromResultSet method and get member object
      Member member = getMemberFromResultSet(rsMem);

      return member;
    } catch (SQLException e) {
      System.out.println("While searching a member with " + ID + " ID, an error occurred: " + e);
      // Return exception
      throw e;
    }
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

  /**
   * Updates the username of specified Member
   * 
   * @param Surname     Surname of Member
   * @param newUsername Updated username
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static void updateMemberUsername(String Surname, String newUsername)
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
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static void updateMemberPassword(String Surname, String newPassword)
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
}
