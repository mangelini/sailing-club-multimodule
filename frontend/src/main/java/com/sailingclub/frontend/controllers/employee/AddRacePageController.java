package com.sailingclub.frontend.controllers.employee;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.employeePages.RacesHomePage;
import entities.Employee;
import entities.Race;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import messageManagement.Message;
import messageManagement.MessageType;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.io.IOException;

public class AddRacePageController {
    Employee currentEmployee;

    @FXML
    private Button backButton;
    @FXML
    private TextField name;
    @FXML
    private TextField loc;

    /**
     * Initialize FXML components,
     */
    @FXML
    public void initialize(Employee employee) {
        this.currentEmployee = employee;
        this.backButton.setStyle("-fx-background-radius: 5em; ");
    }

    public void onAddRaceClick(){
        if (!name.getText().isEmpty() && !loc.getText().isEmpty()){
            Race race = new Race(name.getText(), loc.getText(), false);
            Message<Employee> message = Message.newInstance(currentEmployee, MessageType.ADD_RACE, race);

            try {
                Helpers.getOutputStream().writeObject(message);

                Object o = Helpers.getInputStream().readObject();

                if (o instanceof Reply) {
                    Reply reply = (Reply) o;
                    if (reply.getResponseCode() == ReplyType.OK){
                        new RacesHomePage(currentEmployee).render();
                    }
                    if (reply.getResponseCode() == ReplyType.ERROR) {
                        Helpers.showStage("Some error occurred while adding a race");
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
        new RacesHomePage(currentEmployee).render();
    }
}
