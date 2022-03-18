package entities;

import java.sql.Timestamp;

/**
 * Model class for MembershipFee entity in database
 */
public class MembershipFee extends Fee {
    private final Member member;

    /**
     * Constructor for Membership Fee Entity
     * @param member Member of the club
     * @param date Date of the payment
     * @param fee Fee that was paid
     * @param paymentType Whether the fee was paid with credit card or bank transfer
     */
    public MembershipFee(Member member, Timestamp date, Double fee, String paymentType){
        this.member = member;
        this.date = date;
        this.fee = fee;
        this.paymentType = paymentType;
    }

    /**
     * Getter for date of Membership Fee
     * @return date of the payment
     */
    public Timestamp getDate() {
        return this.date;
    }

    /**
     * Getter for fee of Membership Fee
     * @return fee that was paid
     */
    public Double getFee() {
        return this.fee;
    }

    /**
     * Getter for member of Membership Fee
     * @return member of the club
     */
    public Member getMember() {
        return this.member;
    }

    /**
     * Setter for ID
     * @param ID ID of Membership Fee
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * Getter for ID of entity
     * @return ID
     */
    public Integer getID() {
        return this.ID;
    }

    /**
     * Getter for PaymentType
     * @return paymentType
     */
    public String getPaymentType(){ return paymentType; }
}
