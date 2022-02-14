package com.sailingclub.frontend.controllers.member;

import com.sailingclub.frontend.authPages.AuthHomePage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MemberAuthHomePageController {
    @FXML private Button backButton;

    /**
     * Initialize FXML components
     */
    @FXML
    public void initialize(){
        this.backButton.setStyle("-fx-background-radius: 5em; ");
    }

    /**
     * When the Sign In button is clicked
     * creates a new UserSignIn page
     */
    public void onMemberSignInClick(){
        //new UserSignIn().render();
    }

    /**
     * When the Sign Up button is clicked
     * creates a new UserSignUp page
     */
    public void onMemberSignUpClick(){
        //new UserSignUp().render();
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new AuthHomePage().render();
    }
}
