package dao;

import common.DBUtil;
import entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotifyMembershipFeeDAO {
    /**
     * Adds a new record to NotifyStorageFee table
     * @param notifyMembershipFee Notification Fee to be added
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static synchronized void insertNotifyMembershipFee(NotifyMembershipFee notifyMembershipFee) throws SQLException, ClassNotFoundException {
        String updateStmt = "INSERT INTO notify_membership_fee (MembershipFee, Sent) "
                + "VALUES ('" + notifyMembershipFee.getMembershipFee().getID() + "', '" + notifyMembershipFee.getSent() + "')";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }

    public static synchronized boolean notificationAlreadySent(int membershipFeeID) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM notify_membership_fee WHERE Sent=1 AND MembershipFee='" + membershipFeeID + "'";

        try {
            ResultSet rsFee = DBUtil.dbExecuteQuery(selectStmt);

            NotifyMembershipFee notifyMembershipFee = getNotifyMembershipFeeFromResultSet(rsFee);

            if (notifyMembershipFee == null) return false;
            else return true;
        } catch (SQLException e) {
            System.out.println("While searching a notify membership fee, an error occurred: " + e);
            // Return exception
            throw e;
        }
    }

    public static synchronized boolean isMembershipFeePresent(Integer feeID) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM notify_membership_fee WHERE Membership='" + feeID + "'";

        try {
            ResultSet rsFee = DBUtil.dbExecuteQuery(selectStmt);

            NotifyMembershipFee notifyMembershipFee = getNotifyMembershipFeeFromResultSet(rsFee);

            if (notifyMembershipFee == null) return false;
            else return true;
        } catch (SQLException e) {
            System.out.println("While searching a notify storage fee, an error occurred: " + e);
            // Return exception
            throw e;
        }
    }

    public static synchronized void deleteNotification(Integer feeID) throws SQLException, ClassNotFoundException {
        String updateStmt = "DELETE FROM notify_membership_fee WHERE MembershipFee='" + feeID + "'";

        // Execute DELETE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    private static NotifyMembershipFee getNotifyMembershipFeeFromResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
        NotifyMembershipFee notifyMembershipFee = null;

        if (rs.next()){
            MembershipFee membershipFee = MembershipFeeDAO.searchMembershipFeeByID(rs.getInt("MembershipFee"));
            notifyMembershipFee = new NotifyMembershipFee(rs.getInt("ID"), membershipFee, rs.getBoolean("Sent"));
        }

        return notifyMembershipFee;
    }
}
