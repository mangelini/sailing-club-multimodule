package dao;

import common.DBUtil;
import entities.Boat;
import entities.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoatDAO {
  /**
   * Add boat to the member
   * 
   * @param boat Boat to be added to the table
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static boolean insertBoat(Boat boat) throws SQLException, ClassNotFoundException {
    boolean boatAdded = false;

    String updateStmt = "INSERT INTO boat (Name, Owner, Length) "
        + "VALUES ('" + boat.getName() + "', '" + boat.getOwner().getID() + "', '" + boat.getLength() + "')";

    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while INSERT Operation: " + e);
      throw e;
    } finally {
      boatAdded = true;
    }

    return boatAdded;
  }

  /**
   * Return all the boats with specified name
   * 
   * @param name Name of boats the method returns
   * @return List of boats found
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ArrayList<Boat> searchBoatByName(String name) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM boat WHERE Name='" + name + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsBoats = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getEmployeeFromResultSet method and get employee object
      ArrayList<Boat> boats = getBoatsFromResultSet(rsBoats);

      return boats;
    } catch (SQLException e) {
      System.out.println("While searching a boat with " + name + " name, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  /**
   * Return all the boats with specified ID
   * 
   * @param ID Name of boats the method returns
   * @return List of boats found
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static Boat searchBoatByID(Integer ID) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM boat WHERE ID='" + ID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsBoat = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getEmployeeFromResultSet method and get employee object
      Boat boat = getBoatFromResultSet(rsBoat);

      return boat;
    } catch (SQLException e) {
      System.out.println("While searching a boat with " + ID + " ID, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  private static Boat getBoatFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
    Boat boat = null;

    while (rs.next()) {
      Member member = MemberDAO.searchMemberByID(rs.getInt("Owner"));
      boat = new Boat(rs.getString("Name"), member, rs.getDouble("Length"));
    }
    return boat;
  }

  private static ArrayList<Boat> getBoatsFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
    // Declare an observable List which comprises of Boat objects
    ArrayList<Boat> boatsList = new ArrayList<Boat>();

    while (rs.next()) {
      Member owner = MemberDAO.searchMemberByID(rs.getInt("Owner"));
      Boat boat = new Boat(rs.getString("Name"), owner, rs.getDouble("Length"));
      boat.setID(rs.getInt("ID"));

      boatsList.add(boat);
    }

    return boatsList;
  }

  /**
   * Get all boats of a particular member
   * 
   * @param member Owner of boats
   * @return All boats owned by member
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ArrayList<Boat> searchBoatsByMember(Member member) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM boat WHERE Owner='" + member.getID() + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsBoats = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getBoatsFromResultSet method and get boat object
      ArrayList<Boat> boats = getBoatsFromResultSet(rsBoats);

      return boats;
    } catch (SQLException e) {
      System.out.println("While searching a boat with " + member.getID() + " owner ID, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  /**
   * Returns all boats
   * @return ArrayList of boats
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ArrayList<Boat> getAllBoats() throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM boat";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsBoats = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getBoatsFromResultSet method and get boats array
      ArrayList<Boat> boats = getBoatsFromResultSet(rsBoats);

      return boats;
    } catch (SQLException e) {
      e.printStackTrace();
      // Return exception
      throw e;
    }
  }

  /**
   * Deletes record of selected boat
   * 
   * @param boatID ID of boat entity
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static void deleteBoat(Integer boatID) throws SQLException, ClassNotFoundException {
    String updateStmt = "DELETE FROM boat WHERE ID='" + boatID + "'";

    // Execute DELETE operation
    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while DELETE Operation: " + e);
      throw e;
    }
  }
}
