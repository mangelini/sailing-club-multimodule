package com.sailingclub.frontend.controllers.member;

import com.sailingclub.frontend.authPages.member.MemberAuthHomePage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MemberSignUpPageController {
    @FXML
    private TextField username;
    @FXML private PasswordField password1;
    @FXML private PasswordField password2;
    @FXML private Button backButton;
    String errorString[] = {""};


    /**
     * Initialize FXML components,
     */
    @FXML
    public void initialize(){
        this.backButton.setStyle("-fx-background-radius: 5em; ");
    }

    /**
     * When the Sign Up button is clicked tries to update
     * the users file with an object containing the new
     * credentials
     * if something goes wrong creates a new window
     * showing the error message
     */
    public void onSignUpClick(){
        /*UserRepository userRepository = new UserRepository();

        if(password1.getText().equals(password2.getText())) {
            if (userRepository.signUp(username.getText(), password1.getText(), errorString)) {
                new UserAuthHomePage().render();
            } else {
                new Helpers().showStage(errorString[0]);
            }
        }*/
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new MemberAuthHomePage().render();
    }
}
