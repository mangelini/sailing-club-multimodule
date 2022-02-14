package com.sailingclub.frontend.controllers.employee;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.authPages.AuthHomePage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EmployeeSignInPageController {
    @FXML
    private TextField username;
    @FXML private PasswordField password;
    @FXML private Button backButton;

    /**
     * Initialize FXML components
     */
    @FXML
    public void initialize(){
        this.backButton.setStyle("-fx-background-radius: 5em;");
    }

    /**
     * Tries to Sign In with the given credentials,
     * if something goes wrong creates a new window showing the error message
     */
    public void onSignInClick(){
        /*EmployeeRepository employeeRepository = new EmployeeRepository();

        if(employeeRepository.signIn(username.getText(), password.getText())){
            new EmployeeHomePage().render();
        } else {
            new Helpers().showStage("Credentials not right");
        }*/
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new AuthHomePage().render();
    }
}
