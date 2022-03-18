package dao;

import common.DBUtil;
import entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class handles all the queries and interaction to NotifyMembershipFee entity of database
 */
public class NotifyMembershipFeeDAO {
    /**
     * Adds a new record to NotifyStorageFee table
     * @param notifyMembershipFee Member to be added
     */
    public static synchronized void insertNotifyMember(NotifyMembershipFee notifyMembershipFee) throws SQLException, ClassNotFoundException {
        String updateStmt = "INSERT INTO notify_membership_fee (Member, Sent) "
                + "VALUES ('" + notifyMembershipFee.getMember().getID() + "', '" + notifyMembershipFee.getSent() + "')";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }

    /**
     * Checks weather a notification about the Membership Fee was already sent previously
     * by an Employee
     * @param memberID The Member to search
     * @return True if the notification was already sent, False otherwise
     */
    public static synchronized boolean notificationAlreadySent(int memberID) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM notify_membership_fee WHERE Sent=1 AND Member='" + memberID + "'";

        try {
            ResultSet rsFee = DBUtil.dbExecuteQuery(selectStmt);

            NotifyMembershipFee notifyMembershipFee = getNotifyMembershipFeeFromResultSet(rsFee);

            return notifyMembershipFee != null;
        } catch (SQLException e) {
            System.out.println("While searching a notify membership fee, an error occurred: " + e);
            // Return exception
            throw e;
        }
    }

    /**
     * Check if the given Member is present in table, which means his MembershipFee is expired
     * @param memberID Member to search
     * @return True if it is present in table, False otherwise
     */
    public static synchronized boolean isMemberPresent(Integer memberID) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM notify_membership_fee WHERE Member='" + memberID + "'";

        try {
            ResultSet rsFee = DBUtil.dbExecuteQuery(selectStmt);

            NotifyMembershipFee notifyMembershipFee = getNotifyMembershipFeeFromResultSet(rsFee);

            return notifyMembershipFee != null;
        } catch (SQLException e) {
            System.out.println("While searching a notify storage fee, an error occurred: " + e);
            // Return exception
            throw e;
        }
    }

    /**
     * Delete record of notification from table
     * @param memberID Member to do the search for deleting the notification
     */
    public static synchronized void deleteNotification(Integer memberID) throws SQLException, ClassNotFoundException {
        String updateStmt = "DELETE FROM notify_membership_fee WHERE Member='" + memberID + "'";

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
            Member member = MemberDAO.searchMemberByID(rs.getInt("Member"));
            notifyMembershipFee = new NotifyMembershipFee(rs.getInt("ID"), member, rs.getBoolean("Sent"));
        }

        return notifyMembershipFee;
    }
}