package entities;

public class NotifyMembershipFee {
    private Integer ID;
    private MembershipFee membershipFee;
    private Integer sent;

    /**
     * Constructor without ID
     * @param membershipFee Membership Fee to be added
     * @param sent Parameter set if the employee already sent the notification
     */
    public NotifyMembershipFee(MembershipFee membershipFee, boolean sent){
        this.membershipFee = membershipFee;
        if (!sent) this.sent = 0;
        else this.sent = 1;
    }

    /**
     * Constructor with ID
     * @param membershipFee Membership Fee to be added
     * @param sent Parameter set if the employee already sent the notification
     */
    public NotifyMembershipFee(Integer ID, MembershipFee membershipFee, boolean sent){
        this.ID = ID;
        this.membershipFee = membershipFee;
        if (!sent) this.sent = 0;
        else this.sent = 1;
    }

    /**
     * Getter for ID of NotifyMembershipFee
     * @return ID
     */
    public Integer getID() {
        return ID;
    }

    /**
     * Setter for ID of NotifyMembershipFee
     * @param ID ID to be put
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * Getter for membershipFee entity of NotifyMembershipFee
     * @return MembershipFee
     */
    public MembershipFee getMembershipFee() {
        return membershipFee;
    }

    /**
     * Setter for MembershipFee of NotifyMembershipFee
     * @param membershipFee MembershipFee to be put
     */
    public void setMembershipFee(MembershipFee membershipFee) {
        this.membershipFee = membershipFee;
    }

    /**
     * Getter for Sent of NotifyMembershipFee
     * @return Sent
     */
    public Integer getSent() {
        return sent;
    }

    /**
     * Setter for Sent of NotifyMembershipFee
     * @param sent Boolean value to be put
     */
    public void setSent(boolean sent) {
        if (!sent) this.sent = 0;
        else this.sent = 1;
    }
}
