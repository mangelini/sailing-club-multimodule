package com.sailingclub.frontend.controllers.member;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.authPages.member.MemberAuthHomePage;
import com.sailingclub.frontend.memberPages.MemberHomePage;
import entities.Member;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import messageManagement.Message;
import messageManagement.MessageType;
import messageManagement.Reply;
import messageManagement.ReplyType;

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
        Member insertedMember = new Member(username.getText(), password.getText());
        Message<Member> message = Message.newInstance(insertedMember, MessageType.LOGIN_MEMBER);

        try {
            Helpers.getOutputStream().writeObject(message);

            Object o = Helpers.getInputStream().readObject();
            if (o instanceof Reply){
                Reply reply = (Reply) o;
                if(reply.getResponseCode() == ReplyType.OK){
                    new MemberHomePage((Member) reply.getResults().get(0)).render();
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
        new MemberAuthHomePage().render();
    }
}
