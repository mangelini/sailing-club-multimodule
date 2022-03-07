package dao;

import entities.Boat;
import common.DBUtil;
import entities.StorageFee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StorageFeeDAO {
  /**
   * Adds a new Storage Fee record to db
   * 
   * @param storageFee Fee to be added
   */
  public static synchronized void insertStorageFee(StorageFee storageFee) throws SQLException, ClassNotFoundException {
    String updateStmt = "INSERT INTO storage_fee (Boat, Date, Fee) "
        + "VALUES ('" + storageFee.getBoat().getID() + "', '" + storageFee.getDate()
        + "', '" + storageFee.getFee() + "')";

    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while INSERT Operation: " + e);
      throw e;
    }
  }

  /**
   * Adds a new Storage Fee with PaymentType record to db
   *
   * @param storageFee Fee to be added
   */
  public static synchronized void insertStorageFeeWithPaymentType(StorageFee storageFee) throws SQLException, ClassNotFoundException {
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
   * Search for a specific Storage Fee within DB table
   * 
   * @param boatID ID of boat used for searching record
   */
  public static synchronized StorageFee searchStorageFeeByBoat(Integer boatID) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM storage_fee WHERE Boat='" + boatID + "' ORDER BY Date";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFee = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getEmployeeFromResultSet method and get employee object

      return getStorageFeeFromResultSet(rsFee);
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
   */
  public static synchronized ArrayList<StorageFee> searchStorageFeeByDate(java.sql.Timestamp date)
      throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM storage_fee WHERE Date='" + date + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFees = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getEmployeeFromResultSet method and get employee object

      return getStorageFeesFromResultSet(rsFees);
    } catch (SQLException e) {
      System.out.println("While searching a storage fee, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  /**
   * Search for a Storage Fees of a specific date within DB table
   *
   * @param storageFeeID ID used for searching record
   */
  public static synchronized StorageFee searchStorageFeeByID(Integer storageFeeID)
          throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM storage_fee WHERE ID='" + storageFeeID + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsFees = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getEmployeeFromResultSet method and get employee object

      return getStorageFeeFromResultSet(rsFees);
    } catch (SQLException e) {
      System.out.println("While searching a storage fee, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  public static synchronized void deleteStorageFeeByBoat(Integer boatID)
          throws SQLException, ClassNotFoundException {
    String updateStmt = "DELETE FROM storage_fee WHERE Boat='" + boatID + "'";

    // Execute DELETE operation
    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while DELETE Operation: " + e);
      throw e;
    }
  }

  /**
   * Search for a Storage Fees of a specific date within DB table
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

  /**
   * Updates the payment type when the fee is paid
   * @param feeID Paid Fee
   * @param paymentType New PaymentType
   */
  public static synchronized void updatePaymentType(int feeID, String paymentType)
          throws SQLException, ClassNotFoundException {
    String updateStmt = "UPDATE storage_fee SET PaymentType='" + paymentType
            + "' WHERE ID='" + feeID + "'";

    // Execute UPDATE operation
    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while UPDATE Operation: " + e);
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
    ArrayList<StorageFee> feesList = new ArrayList<StorageFee>();

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
