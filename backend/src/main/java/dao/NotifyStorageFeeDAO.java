package dao;

import common.DBUtil;
import entities.Boat;
import entities.NotifyStorageFee;
import entities.StorageFee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotifyStorageFeeDAO {
    /**
     * Adds a new record to NotifyStorageFee table
     * @param notifyStorageFee Notification Fee to be added
     * @throws SQLException
     * @throws ClassNotFoundException
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

    private static NotifyStorageFee getNotifyStorageFeeFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
        NotifyStorageFee notifyStorageFee = null;

        if (rs.next()){
            Boat boat = BoatDAO.searchBoatByID(rs.getInt("Boat"));
            notifyStorageFee = new NotifyStorageFee(rs.getInt("ID"), boat, rs.getBoolean("Sent"));
        }

        return notifyStorageFee;
    }

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
}