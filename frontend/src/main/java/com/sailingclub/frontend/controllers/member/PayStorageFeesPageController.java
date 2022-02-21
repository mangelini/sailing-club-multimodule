package com.sailingclub.frontend.controllers.member;

import com.sailingclub.frontend.memberPages.MemberHomePage;
import entities.Member;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class PayStorageFeesPageController {
    Member currentMember;

    @FXML
    private Button backButton;

    /**
     * Initialize FXML components,
     */
    @FXML
    public void initialize(Member member) {
        this.currentMember = member;
        this.backButton.setStyle("-fx-background-radius: 5em; ");
    }

    public void onPayFeeClick() {

    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new MemberHomePage(currentMember).render();
    }
}
