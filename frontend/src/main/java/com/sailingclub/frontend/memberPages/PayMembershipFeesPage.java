package com.sailingclub.frontend.memberPages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.member.PayMembershipFeesPageController;
import entities.Member;
import javafx.fxml.FXMLLoader;

public class PayMembershipFeesPage {
    Member currentMember;

    public PayMembershipFeesPage(Member currentMember){
        this.currentMember = currentMember;
    }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/member/pay-membership-fees-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            // pass data from the previous page
            PayMembershipFeesPageController payMembershipFeesPageController = fxmlLoader.<PayMembershipFeesPageController>getController();
            payMembershipFeesPageController.initialize(currentMember);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in MemberHomePage render()");
        }
    }
}
