package com.sailingclub.frontend.controllers.admin;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.adminPages.AdminHomePage;
import com.sailingclub.frontend.authPages.AuthHomePage;
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

public class AdminAddEmployeeController {
    Employee currentEmployee;

    @FXML
    private Button backButton;
    @FXML
    private TextField username;
    @FXML private PasswordField password1;
    @FXML private PasswordField password2;

    /**
     * Initialize FXML components,
     */
    @FXML
    public void initialize(Employee employee) {
        this.currentEmployee = employee;
        this.backButton.setStyle("-fx-background-radius: 5em; ");
    }

    public void onAddEmployeeClick() {
        if(password1.getText().equals(password2.getText())) {
            Employee employee = new Employee(username.getText(), password1.getText(),
                    false);
            Message<Employee> message = Message.newInstance(employee, MessageType.ADD_EMPLOYEE);

            try {
                Helpers.getOutputStream().writeObject(message);

                Object o = Helpers.getInputStream().readObject();
                if (o instanceof Reply){
                    Reply reply = (Reply) o;
                    if(reply.getResponseCode() == ReplyType.OK){
                        new AdminHomePage(currentEmployee).render();
                    } else if(reply.getResponseCode() == ReplyType.ERROR) {
                        Helpers.showStage("Some error occurred in the Add Employee process");
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
        new AdminHomePage(currentEmployee).render();
    }
}