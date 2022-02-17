package com.sailingclub.frontend.controllers.member;

import com.sailingclub.frontend.authPages.member.MemberAuthHomePage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MemberHomePageController {
    @FXML
    private Button backButton;

    /**
     * Initialize FXML components,
     */
    @FXML
    public void initialize() {
        this.backButton.setStyle("-fx-background-radius: 5em; ");
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new MemberAuthHomePage().render();
    }
}
