package dao;

import common.DBUtil;
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
        String updateStmt = "INSERT INTO notify_storage_fee (StorageFee, Sent) "
                + "VALUES ('" + notifyStorageFee.getStorageFee().getID() + "', '" + notifyStorageFee.isSent() + "')";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }

    public static synchronized boolean notificationAlreadySent(int storageFeeID) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM notify_storage_fee WHERE Sent=1 AND StorageFee='" + storageFeeID + "'";

        try {
            ResultSet rsFee = DBUtil.dbExecuteQuery(selectStmt);

            NotifyStorageFee notifyStorageFee = getNotifyStorageFeeFromResultSet(rsFee);

            if (notifyStorageFee == null) return false;
            else return true;
        } catch (SQLException e) {
            System.out.println("While searching a notify storage fee, an error occurred: " + e);
            // Return exception
            throw e;
        }
    }

    public static synchronized boolean isStorageFeePresent(Integer feeID) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM notify_storage_fee WHERE StorageFee='" + feeID + "'";

        try {
            ResultSet rsFee = DBUtil.dbExecuteQuery(selectStmt);

            NotifyStorageFee notifyStorageFee = getNotifyStorageFeeFromResultSet(rsFee);

            if (notifyStorageFee == null) return false;
            else return true;
        } catch (SQLException e) {
            System.out.println("While searching a notify storage fee, an error occurred: " + e);
            // Return exception
            throw e;
        }
    }

    private static NotifyStorageFee getNotifyStorageFeeFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
        NotifyStorageFee notifyStorageFee = null;

        if (rs.next()){
            StorageFee storageFee = StorageFeeDAO.searchStorageFeeByID(rs.getInt("StorageFee"));
            notifyStorageFee = new NotifyStorageFee(rs.getInt("ID"), storageFee, rs.getBoolean("Sent"));
        }

        return notifyStorageFee;
    }

    public static synchronized void deleteNotification(Integer feeID) throws SQLException, ClassNotFoundException {
        String updateStmt = "DELETE FROM notify_storage_fee WHERE StorageFee='" + feeID + "'";

        // Execute DELETE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}
