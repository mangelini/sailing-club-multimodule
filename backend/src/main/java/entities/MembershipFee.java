package entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class MembershipFee implements Serializable {
    private static final long serialVersionUID = 1L;
    private Member member;
    private Timestamp date;
    private Double fee;
    private Integer ID;

    /**
     * Constructor for Membership Fee Entity
     * @param member Member of the club
     * @param date Date of the payment
     * @param fee Fee that was paid
     */
    public MembershipFee(Member member, Timestamp date, Double fee){
        this.member = member;
        this.date = date;
        this.fee = fee;
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
}
