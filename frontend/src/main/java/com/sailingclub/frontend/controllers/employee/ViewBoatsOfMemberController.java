package com.sailingclub.frontend.controllers.employee;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.employeePages.ViewMembersPage;
import entities.Boat;
import entities.Employee;
import entities.Member;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import messageManagement.Message;
import messageManagement.MessageType;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class ViewBoatsOfMemberController {
    Employee currentEmployee;
    Member selectedMember;
    ArrayList<Boat> boats = new ArrayList<>();

    @FXML
    private Button backButton;
    @FXML
    private TableView<Boat> tableView;
    @FXML
    private TableColumn<Boat, String> name;
    @FXML
    private TableColumn<Boat, Double> length;
    @FXML
    private TableColumn<Boat, Integer> ID;

    TableView.TableViewSelectionModel<Boat> selectionModel;

    /**
     * Initialize FXML components,
     */
    @FXML
    public void initialize(Employee employee, Member selectedMember) {
        this.currentEmployee = employee;
        this.selectedMember = selectedMember;
        this.backButton.setStyle("-fx-background-radius: 5em; ");

        this.selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        getAllData();
        initTableView();
    }

    private void getAllData() {
        Message<Employee> message = Message.newInstance(currentEmployee, MessageType.GET_ALL_BOATS, selectedMember);

        try {
            Helpers.getOutputStream().writeObject(message);

            Object o = Helpers.getInputStream().readObject();

            if (o instanceof Reply) {
                Reply reply = (Reply) o;
                if (reply.getResponseCode() == ReplyType.OK) {
                    // make cast of serializable array of response
                    ArrayList<Serializable> serializableArrayList = reply.getResults();
                    boats = (ArrayList<Boat>) serializableArrayList.get(0);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initTableView() {
        name.setCellValueFactory(new PropertyValueFactory<Boat, String>("Name"));
        length.setCellValueFactory(new PropertyValueFactory<Boat, Double>("Length"));
        ID.setCellValueFactory(new PropertyValueFactory<Boat, Integer>("ID"));

        if (boats != null)
            tableView.getItems().setAll(boats);
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new ViewMembersPage(currentEmployee).render();
    }
}
