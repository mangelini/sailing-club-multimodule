package com.sailingclub.frontend.controllers.member;

import com.sailingclub.frontend.authPages.member.MemberAuthHomePage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MemberSignInPageController {
    @FXML
    private TextField username;
    @FXML private PasswordField password;
    @FXML private Button backButton;

    /**
     * Initialize FXML components,
     */
    @FXML
    public void initialize(){
        this.backButton.setStyle("-fx-background-radius: 5em; ");
    }

    /**
     * When the Sign In button is clicked tries to search in
     * the users file for an object that matches the given
     * parameters, if something goes wrong creates a new window
     * showing the error message
     */
    public void onSignInClick(){
        /*UserRepository userRepository = new UserRepository();

        if(userRepository.signIn(username.getText(), password.getText())){
            new UserHomePage().render();
        } else {
            new Helpers().showStage("Some error occurred in the Sign In process");
        }*/
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new MemberAuthHomePage().render();
    }
}
