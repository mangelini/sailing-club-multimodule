package com.sailingclub.frontend.controllers.member;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.memberPages.MemberHomePage;
import entities.Member;
import entities.MembershipFee;
import entities.StorageFee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import messageManagement.Message;
import messageManagement.MessageType;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class PayMembershipFeesPageController {
    Member currentMember;
    MembershipFee fee;

    @FXML
    private Button backButton;
    @FXML
    private Button payButton;
    @FXML
    private Label alreadyPayedLabel;

    @FXML
    public void initialize(Member member) {
        this.alreadyPayedLabel.setVisible(false);
        this.payButton.setVisible(false);
        this.currentMember = member;
        this.backButton.setStyle("-fx-background-radius: 5em; ");

        getMembershipFee();

        if(fee == null) this.alreadyPayedLabel.setVisible(true);
        else this.payButton.setVisible(true);
    }

    private void getMembershipFee(){
        Message<Member> message = Message.newInstance(currentMember, MessageType.GET_MEMBER_MEM_FEES_TO_PAY);

        try {
            Helpers.getOutputStream().writeObject(message);

            Object o = Helpers.getInputStream().readObject();

            if (o instanceof Reply) {
                Reply reply = (Reply) o;
                if (reply.getResponseCode() == ReplyType.OK) {
                    // make cast of serializable array of response
                    ArrayList<Serializable> serializableArrayList = reply.getResults();
                    fee = (MembershipFee) serializableArrayList.get(0);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onPayFeeClick(){
        Message<Member> message = Message.newInstance(currentMember, MessageType.PAY_MEMBERSHIP_FEE, fee);

        try {
            Helpers.getOutputStream().writeObject(message);

            Object o = Helpers.getInputStream().readObject();

            if (o instanceof Reply) {
                Reply reply = (Reply) o;
                if (reply.getResponseCode() == ReplyType.OK) {
                    new MemberHomePage(currentMember).render();
                } else if (reply.getResponseCode() == ReplyType.ERROR) {
                    Helpers.showStage("Some error occurred while paying fee");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new MemberHomePage(currentMember).render();
    }
}
