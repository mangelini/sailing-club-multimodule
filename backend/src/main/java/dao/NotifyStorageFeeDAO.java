package dao;

import common.DBUtil;
import entities.Boat;
import entities.NotifyStorageFee;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class handles all the queries and interaction to NotifyStorageFee entity of database
 */
public class NotifyStorageFeeDAO {
    /**
     * Adds a new record to NotifyStorageFee table
     * @param notifyStorageFee Notification Fee to be added
     */
    public static synchronized void insertNotifyStorageFee(NotifyStorageFee notifyStorageFee) throws SQLException, ClassNotFoundException {
        String updateStmt = "INSERT INTO notify_storage_fee (Boat, Sent) "
                + "VALUES ('" + notifyStorageFee.getBoat().getID() + "', '" + notifyStorageFee.isSent() + "')";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }

    /**
     * Check weather the notification was already sent to Member
     * @param boatID Boat for searching the correct record
     * @return True if notification was sent, False otherwise
     */
    public static synchronized boolean notificationAlreadySent(int boatID) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM notify_storage_fee WHERE Sent=1 AND Boat='" + boatID + "'";

        try {
            ResultSet rsFee = DBUtil.dbExecuteQuery(selectStmt);

            NotifyStorageFee notifyStorageFee = getNotifyStorageFeeFromResultSet(rsFee);

            return notifyStorageFee != null;
        } catch (SQLException e) {
            System.out.println("While searching a notify storage fee, an error occurred: " + e);
            // Return exception
            throw e;
        }
    }

    /**
     * Check whether given Boat is present in DB
     * @param boatID Boat to do the query
     * @return True if it is present, False otherwise
     */
    public static synchronized boolean isBoatPresent(Integer boatID) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM notify_storage_fee WHERE Boat='" + boatID + "'";

        try {
            ResultSet rsFee = DBUtil.dbExecuteQuery(selectStmt);

            NotifyStorageFee notifyStorageFee = getNotifyStorageFeeFromResultSet(rsFee);

            return notifyStorageFee != null;
        } catch (SQLException e) {
            System.out.println("While searching a notify storage fee, an error occurred: " + e);
            // Return exception
            throw e;
        }
    }

    /**
     * Delete notification from database
     * @param boatID Boat ID of record to delete
     */
    public static synchronized void deleteNotification(Integer boatID) throws SQLException, ClassNotFoundException {
        String updateStmt = "DELETE FROM notify_storage_fee WHERE Boat='" + boatID + "'";

        // Execute DELETE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    private static NotifyStorageFee getNotifyStorageFeeFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
        NotifyStorageFee notifyStorageFee = null;

        if (rs.next()){
            Boat boat = BoatDAO.searchBoatByID(rs.getInt("Boat"));
            notifyStorageFee = new NotifyStorageFee(rs.getInt("ID"), boat, rs.getBoolean("Sent"));
        }

        return notifyStorageFee;
    }
}