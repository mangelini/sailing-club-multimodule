package dao;

import entities.Boat;
import common.DBUtil;
import entities.RegistrationFee;
import entities.Race;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistrationFeeDAO {
  /**
   * Adds a new Registration Fee record to db
   * 
   * @param registrationFee Fee to be added
   */
  public static synchronized void insertRegistrationFee(RegistrationFee registrationFee)
      throws SQLException, ClassNotFoundException {
    String updateStmt = "INSERT INTO registration_fee (Boat, Race, Fee, PaymentType, Date) "
        + "VALUES ('" + registrationFee.getBoat().getID() + "', '" + registrationFee.getRace().getID()
        + "', '" + registrationFee.getFee() + "', '" + registrationFee.getPaymentType()
            + "', '" + registrationFee.getDate() + "')";

    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while INSERT Operation: " + e);
      throw e;
    }
  }

  /**
   * Get all registration fees from database
   * @return ArrayList of results
   */
  public static synchronized ArrayList<RegistrationFee> getAllFees()
          throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM registration_fee";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFee = DBUtil.dbExecuteQuery(selectStmt);

      return getRegistrationFeesFromResultSet(rsFee);
    } catch (SQLException e) {
      System.out.println("While searching a registration fee, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  /**
   * Search for a specific Registration Fee within DB table
   * 
   * @param boatID ID of boat used for searching record
   * @return Registration Fee found with matching criteria
   */
  public static synchronized ArrayList<RegistrationFee> searchRegistrationFeesByBoat(Integer boatID)
      throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM registration_fee WHERE Boat='" + boatID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFees = DBUtil.dbExecuteQuery(selectStmt);

      return getRegistrationFeesFromResultSet(rsFees);
    } catch (SQLException e) {
      System.out.println("While searching a membership fee, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  /**
   * Check whether the given boat was already registered to race
   * @param boatID Selected boat to do the query
   * @param raceID Selected race to do the query
   * @return True if boat was already registered, False otherwise
   */
  public static synchronized boolean boatAlreadyRegistered(Integer boatID, Integer raceID) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM registration_fee WHERE Boat='" + boatID + "' AND Race='" + raceID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFee = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getEmployeeFromResultSet method and get employee object
      RegistrationFee registrationFee = getRegistrationFeeFromResultSet(rsFee);

      return registrationFee != null;
    } catch (SQLException e) {
      System.out.println("While searching a membership fee, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  /**
   * Search for a specific Registration Fee within DB table
   * 
   * @param raceID ID of race used for searching record
   * @return Registration Fee found with matching criteria
   */
  public static synchronized ArrayList<RegistrationFee> searchRegistrationFeesByRace(Integer raceID)
      throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM registration_fee WHERE Race='" + raceID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFees = DBUtil.dbExecuteQuery(selectStmt);

      return getRegistrationFeesFromResultSet(rsFees);
    } catch (SQLException e) {
      System.out.println("While searching a membership fee, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  /**
   * Search for a specific Registration Fee within DB table
   * 
   * @param ID ID of Registration Fee used for searching record
   * @return Registration Fee found with matching criteria
   */
  public static synchronized ArrayList<RegistrationFee> searchRegistrationFeesByID(Integer ID)
      throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM registration_fee WHERE ID='" + ID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFees = DBUtil.dbExecuteQuery(selectStmt);

      return getRegistrationFeesFromResultSet(rsFees);
    } catch (SQLException e) {
      System.out.println("While searching a membership fee, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  private static ArrayList<RegistrationFee> getRegistrationFeesFromResultSet(ResultSet rs)
      throws SQLException, ClassNotFoundException {
    ArrayList<RegistrationFee> feesList = new ArrayList<RegistrationFee>();

    while (rs.next()) {
      Boat boat = BoatDAO.searchBoatByID(rs.getInt("Boat"));
      Race race = RaceDAO.searchRaceByID(rs.getInt("Race"));

      // TODO Registration Fee will be decided per race
      RegistrationFee registrationFee = new RegistrationFee(boat, race, 100.0,
              rs.getTimestamp("Date"), rs.getString("PaymentType"));
      registrationFee.setID(rs.getInt("ID"));

      feesList.add(registrationFee);
    }

    return feesList;
  }

  private static RegistrationFee getRegistrationFeeFromResultSet(ResultSet rs)
          throws SQLException, ClassNotFoundException {
    RegistrationFee registrationFee = null;

    if (rs.next()){
      Boat boat = BoatDAO.searchBoatByID(rs.getInt("Boat"));
      Race race = RaceDAO.searchRaceByID(rs.getInt("Race"));
      // TODO Registration Fee will be decided per race???
      registrationFee = new RegistrationFee(boat, race, 100.0,
              rs.getTimestamp("Date"), rs.getString("PaymentType"));
      registrationFee.setID(rs.getInt("ID"));
    }

    return registrationFee;
  }
}
