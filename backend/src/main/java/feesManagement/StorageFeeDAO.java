package feesManagement;

import boatManagement.Boat;
import boatManagement.BoatDAO;
import common.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StorageFeeDAO {
  /**
   * Adds a new Storage Fee record to db
   * 
   * @param storageFee Fee to be added
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static void insertStorageFee(StorageFee storageFee) throws SQLException, ClassNotFoundException {
    String updateStmt = "INSERT INTO storage_fee (Boat, Date, Fee) "
        + "VALUES ('" + storageFee.getBoat() + "', '" + storageFee.getDate()
        + "', '" + storageFee.getFee() + "')";

    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while INSERT Operation: " + e);
      throw e;
    }
  }

  /**
   * Search for a specific Storage Fee within DB table
   * 
   * @param boatID ID of boat used for searching record
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static StorageFee searchStorageFeeByBoat(Integer boatID) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM storage_fee WHERE Boat='" + boatID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFee = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getEmployeeFromResultSet method and get employee object
      StorageFee storageFee = getStorageFeeFromResultSet(rsFee);

      return storageFee;
    } catch (SQLException e) {
      System.out.println("While searching a membership fee, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  /**
   * Search for a Storage Fees of a specific date within DB table
   * 
   * @param date Date used for searching record
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static ArrayList<StorageFee> searchStorageFeeByDate(java.sql.Date date)
      throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM storage_fee WHERE Date='" + date + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFees = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getEmployeeFromResultSet method and get employee object
      ArrayList<StorageFee> storageFees = getStorageFeesFromResultSet(rsFees);

      return storageFees;
    } catch (SQLException e) {
      System.out.println("While searching a membership fee, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  private static StorageFee getStorageFeeFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
    StorageFee storageFee = null;

    if (rs.next()) {
      Boat boat = BoatDAO.searchBoatByID(rs.getInt("Boat"));
      java.sql.Date sqlDate = new java.sql.Date(rs.getDate("Date").getTime());

      storageFee = new StorageFee(boat, sqlDate, rs.getDouble("Fee"));
      storageFee.setID(rs.getInt("ID"));
    }

    return storageFee;
  }

  private static ArrayList<StorageFee> getStorageFeesFromResultSet(ResultSet rs)
      throws SQLException, ClassNotFoundException {
    ArrayList<StorageFee> feesList = new ArrayList<StorageFee>();

    while (rs.next()) {
      Boat boat = BoatDAO.searchBoatByID(rs.getInt("Boat"));
      java.sql.Date sqlDate = new java.sql.Date(rs.getDate("Date").getTime());

      StorageFee storageFee = new StorageFee(boat, sqlDate, rs.getDouble("Fee"));
      storageFee.setID(rs.getInt("ID"));

      feesList.add(storageFee);
    }

    return feesList;
  }
}
