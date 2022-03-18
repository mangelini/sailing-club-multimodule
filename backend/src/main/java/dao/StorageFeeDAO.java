package dao;

import entities.Boat;
import common.DBUtil;
import entities.StorageFee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class handles all the queries and interaction to StorageFee entity of database
 */
public class StorageFeeDAO {
  /**
   * Adds a new Storage Fee with PaymentType record to db
   *
   * @param storageFee Fee to be added
   */
  public static synchronized void insertStorageFee(StorageFee storageFee) throws SQLException, ClassNotFoundException {
    String updateStmt = "INSERT INTO storage_fee (Boat, Date, Fee, PaymentType) "
            + "VALUES ('" + storageFee.getBoat().getID() + "', '" + storageFee.getDate()
            + "', '" + storageFee.getFee() + "', '" + storageFee.getPaymentType() + "')";

    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while INSERT Operation: " + e);
      throw e;
    }
  }

  /**
   * Get all Storage fees in database
   * @return ArrayList of results
   */
  public static synchronized ArrayList<StorageFee> getAllFees()
          throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM storage_fee";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFee = DBUtil.dbExecuteQuery(selectStmt);

      return getStorageFeesFromResultSet(rsFee);
    } catch (SQLException e) {
      System.out.println("While searching a membership fee, an error occurred: " + e);
      throw e;
    }
  }

  /**
   * Search for the only Storage Fee not expired of given boat in database
   * @param boatID Selected Boat that should have a valid StorageFee
   * @return StorageFee object of result, null otherwise
   */
  public static synchronized StorageFee searchNotExpiredStorageFeeOfBoat(int boatID)
          throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM storage_fee WHERE timestampdiff(second, Date, NOW()) < 30 AND Boat='"
            + boatID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFees = DBUtil.dbExecuteQuery(selectStmt);

      return getStorageFeeFromResultSet(rsFees);
    } catch (SQLException e) {
      System.out.println("While searching a membership fee, an error occurred: " + e);
      throw e;
    }
  }

  private static StorageFee getStorageFeeFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
    StorageFee storageFee = null;

    if (rs.next()) {
      Boat boat = BoatDAO.searchBoatByID(rs.getInt("Boat"));

      storageFee = new StorageFee(boat, rs.getTimestamp("Date"), rs.getDouble("Fee"),
              rs.getString("PaymentType"));
      storageFee.setID(rs.getInt("ID"));
    }

    return storageFee;
  }

  private static ArrayList<StorageFee> getStorageFeesFromResultSet(ResultSet rs)
      throws SQLException, ClassNotFoundException {
    ArrayList<StorageFee> feesList = new ArrayList<>();

    while (rs.next()) {
      Boat boat = BoatDAO.searchBoatByID(rs.getInt("Boat"));

      StorageFee storageFee = new StorageFee(boat, rs.getTimestamp("Date"), rs.getDouble("Fee"),
              rs.getString("PaymentType"));
      storageFee.setID(rs.getInt("ID"));

      feesList.add(storageFee);
    }

    return feesList;
  }
}
