package com.sailingclub.frontend.controllers.employee;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.authPages.AuthHomePage;
import entities.Boat;
import entities.Employee;
import entities.Member;
import entities.StorageFee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import messageManagement.Message;
import messageManagement.MessageType;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class NotifyStorageFeesPageController {
    Employee currentEmployee;
    ArrayList<Member> owners;
    ArrayList<Boat> boats;
    ArrayList<Date> dates;

    @FXML
    private Button backButton;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Boat, String> boat;
    @FXML
    private TableColumn<Member, String> member;
    @FXML
    private TableColumn<StorageFee, String> date;

    TableView.TableViewSelectionModel selectionModel;

    /**
     * Initialize FXML components,
     */
    @FXML
    public void initialize(Employee employee) {
        this.currentEmployee = employee;
        this.backButton.setStyle("-fx-background-radius: 5em; ");

        this.selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        getAllData();
    }

    public void onNotifyMemberClick(){

    }

    private void getAllData() {
        Message<Employee> message = new Message<>(currentEmployee, MessageType.GET_STORAGE_FEES_TO_PAY, "");

        try {
            Helpers.getOutputStream().writeObject(message);

            Object o = Helpers.getInputStream().readObject();

            if (o instanceof Reply) {
                Reply reply = (Reply) o;
                if (reply.getResponseCode() == ReplyType.OK) {
                    // make cast of serializable array of response
                    ArrayList<Serializable> serializableArrayList = reply.getResults();
                    dates = (ArrayList<Date>) serializableArrayList.get(0);
                    owners = (ArrayList<Member>) serializableArrayList.get(1);
                    boats = (ArrayList<Boat>) serializableArrayList.get(2);
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
