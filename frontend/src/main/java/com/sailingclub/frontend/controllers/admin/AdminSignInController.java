package com.sailingclub.frontend.controllers.admin;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.authPages.AuthHomePage;
import com.sailingclub.frontend.adminPages.AdminHomePage;
import entities.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import messageManagement.Message;
import messageManagement.MessageType;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.io.IOException;

public class AdminSignInController {
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

    public void onSignInClick(){
        Employee admin = new Employee(username.getText(), password.getText(), true);
        Message<Employee> message = Message.newInstance(admin, MessageType.LOGIN_EMPLOYEE);

        try {
            Helpers.getOutputStream().writeObject(message);

            Object o = Helpers.getInputStream().readObject();
            if (o instanceof Reply){
                Reply reply = (Reply) o;
                if(reply.getResponseCode() == ReplyType.OK){
                    new AdminHomePage((Employee) ((Reply) o).getResults().get(0)).render();
                } else if (reply.getResponseCode() == ReplyType.NOT_FOUND) {
                    Helpers.showStage("Credentials are not right");
                } else {
                    Helpers.showStage("Some error occurred in the Sign Up process");
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
        new AuthHomePage().render();
    }
}
