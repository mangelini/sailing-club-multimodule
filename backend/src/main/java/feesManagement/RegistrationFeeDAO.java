package feesManagement;

import boatManagement.Boat;
import boatManagement.BoatDAO;
import common.DBUtil;
import raceManagement.Race;
import raceManagement.RaceDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistrationFeeDAO {
  /**
   * Adds a new Registration Fee record to db
   * 
   * @param registrationFee Fee to be added
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static void insertRegistrationFee(RegistrationFee registrationFee)
      throws SQLException, ClassNotFoundException {
    String updateStmt = "INSERT INTO registration_fee (Boat, Race, Fee) "
        + "VALUES ('" + registrationFee.getBoat() + "', '" + registrationFee.getRace().getID()
        + "', '" + registrationFee.getFee() + "')";

    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while INSERT Operation: " + e);
      throw e;
    }
  }

  /**
   * Search for a specific Registration Fee within DB table
   * 
   * @param boatID ID of boat used for searching record
   * @return Registration Fee found with matching criteria
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ArrayList<RegistrationFee> searchRegistrationFeeByBoat(Integer boatID)
      throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM registration_fee WHERE Boat='" + boatID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFees = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getEmployeeFromResultSet method and get employee object
      ArrayList<RegistrationFee> registrationFees = getRegistrationFeesFromResultSet(rsFees);

      return registrationFees;
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
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ArrayList<RegistrationFee> searchRegistrationFeeByRace(Integer raceID)
      throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM registration_fee WHERE Race='" + raceID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFees = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getEmployeeFromResultSet method and get employee object
      ArrayList<RegistrationFee> registrationFees = getRegistrationFeesFromResultSet(rsFees);

      return registrationFees;
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
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ArrayList<RegistrationFee> searchRegistrationFeeByID(Integer ID)
      throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM registration_fee WHERE ID='" + ID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFees = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getEmployeeFromResultSet method and get employee object
      ArrayList<RegistrationFee> registrationFees = getRegistrationFeesFromResultSet(rsFees);

      return registrationFees;
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
      Race race = RaceDAO.searchRaceByID(rs.getInt("ID"));
      // TODO Registration Fee will be decided per race???
      RegistrationFee registrationFee = new RegistrationFee(boat, race, 100.0);
      registrationFee.setID(rs.getInt("ID"));

      feesList.add(registrationFee);
    }

    return feesList;
  }
}
