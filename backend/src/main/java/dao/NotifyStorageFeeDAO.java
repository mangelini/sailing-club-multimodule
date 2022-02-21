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
    public static void insertNotifyStorageFee(NotifyStorageFee notifyStorageFee) throws SQLException, ClassNotFoundException {
        String updateStmt = "INSERT INTO notify_storage_fee (StorageFee, Sent) "
                + "VALUES ('" + notifyStorageFee.getStorageFee().getID() + "', '" + notifyStorageFee.isSent() + "')";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }

    public static boolean notificationAlreadySent(int storageFeeID) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM notify_storage_fee WHERE Sent=0 AND StorageFee='" + storageFeeID + "'";

        try {
            ResultSet rsFee = DBUtil.dbExecuteQuery(selectStmt);

            NotifyStorageFee notifyStorageFee = getNotifyStorageFeeFromResultSet(rsFee);

            if (notifyStorageFee == null) return true;
            else return false;
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
}
