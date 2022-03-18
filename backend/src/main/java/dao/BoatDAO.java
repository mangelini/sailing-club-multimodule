package dao;

import common.DBUtil;
import entities.Boat;
import entities.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class handles all the queries and interaction to Boat entity of database
 */
public class BoatDAO {
  /**
   * Add boat to the member
   * 
   * @param boat Boat to be added to the table
   */
  public static synchronized int insertBoat(Boat boat) throws SQLException, ClassNotFoundException {
    int ID = 0;

    String updateStmt = "INSERT INTO boat (Name, Owner, Length, Enabled) "
        + "VALUES ('" + boat.getName() + "', '" + boat.getOwner().getID() + "', '" + boat.getLength()
            + "', '" + boat.isEnabled() + "')";

    try {
      ID = DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while INSERT Operation: " + e);
      throw e;
    }

    return ID;
  }

  /**
   * Return all the boats with specified ID
   * 
   * @param ID Name of boats the method returns
   * @return List of boats found
   */
  public static synchronized Boat searchBoatByID(Integer ID) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM boat WHERE ID='" + ID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsBoat = DBUtil.dbExecuteQuery(selectStmt);

      return getBoatFromResultSet(rsBoat);
    } catch (SQLException e) {
      System.out.println("While searching a boat with " + ID + " ID, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  /**
   * Get all boats of a particular member
   * 
   * @param member Owner of boats
   * @return All boats owned by member
   */
  public static synchronized ArrayList<Boat> searchBoatsByMember(Member member) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM boat WHERE Owner='" + member.getID() + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsBoats = DBUtil.dbExecuteQuery(selectStmt);

      return getBoatsFromResultSet(rsBoats);
    } catch (SQLException e) {
      System.out.println("While searching a boat with " + member.getID() + " owner ID, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  /**
   * Get all enabled boats of a particular member
   *
   * @param member Owner of boats
   * @return All boats owned by member
   */
  public static synchronized ArrayList<Boat> searchEnabledBoatsByMember(Member member) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM boat WHERE Owner='" + member.getID() + "' AND Enabled=1";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsBoats = DBUtil.dbExecuteQuery(selectStmt);

      return getBoatsFromResultSet(rsBoats);
    } catch (SQLException e) {
      System.out.println("While searching a boat with " + member.getID() + " owner ID, an error occurred: " + e);
      throw e;
    }
  }

  /**
   * Returns all boats
   * @return ArrayList of boats
   */
  public static synchronized ArrayList<Boat> getAllBoats() throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM boat WHERE Enabled=1";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsBoats = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getBoatsFromResultSet method and get boats array

      return getBoatsFromResultSet(rsBoats);
    } catch (SQLException e) {
      e.printStackTrace();
      // Return exception
      throw e;
    }
  }

  /**
   * Deletes record of selected boat by disabling the bit Enabled
   * 
   * @param boatID ID of boat entity
   */
  public static synchronized void deleteBoat(Integer boatID) throws SQLException, ClassNotFoundException {
    String updateStmt = "UPDATE boat SET Enabled=0 WHERE ID='" + boatID + "'";

    // Execute DELETE operation
    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while DELETE Operation: " + e);
      throw e;
    }
  }

  private static Boat getBoatFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
    Boat boat = null;

    while (rs.next()) {
      Member member = MemberDAO.searchMemberByID(rs.getInt("Owner"));
      boat = new Boat(rs.getString("Name"), member, rs.getDouble("Length"));
      boat.setID(rs.getInt("ID"));
    }
    return boat;
  }

  private static ArrayList<Boat> getBoatsFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
    // Declare an observable List which comprises Boat objects
    ArrayList<Boat> boatsList = new ArrayList<>();

    while (rs.next()) {
      Member owner = MemberDAO.searchMemberByID(rs.getInt("Owner"));
      Boat boat = new Boat(rs.getString("Name"), owner, rs.getDouble("Length"));
      boat.setID(rs.getInt("ID"));

      boatsList.add(boat);
    }

    return boatsList;
  }
}
