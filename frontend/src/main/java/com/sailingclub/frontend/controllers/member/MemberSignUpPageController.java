package com.sailingclub.frontend.controllers.member;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.authPages.member.MemberAuthHomePage;
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

public class MemberSignUpPageController {
    @FXML private TextField username;
    @FXML private TextField name;
    @FXML private TextField surname;
    @FXML private TextField address;
    @FXML private TextField fiscalCode;
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
        if(password1.getText().equals(password2.getText())) {
            Member member = new Member(name.getText(), surname.getText(),
                    address.getText(), fiscalCode.getText(), username.getText(), password1.getText());
            Message<Member> message = new Message<Member>(member, MessageType.ADD_MEMBER, "");

            try {
                Helpers.getOutputStream().writeObject(message);

                Object o = Helpers.getInputStream().readObject();
                if (o instanceof  Reply){
                    Reply reply = (Reply) o;
                    if(reply.getResponseCode() == ReplyType.OK){
                        new MemberAuthHomePage().render();
                    } else {
                        Helpers.showStage("Some error occurred in the Sign Up process");
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new MemberAuthHomePage().render();
    }
}
