package com.sailingclub.frontend.controllers.employee;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.employeePages.EmployeeHomePage;
import com.sailingclub.frontend.employeePages.ViewBoatsOfMemberPage;
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

public class ViewMembersController {
    Employee currentEmployee;
    ArrayList<Member> members = new ArrayList<>();

    @FXML
    private Button backButton;
    @FXML
    private TableView<Member> tableView;
    @FXML
    private TableColumn<Member, String> name;
    @FXML
    private TableColumn<Member, String> surname;
    @FXML
    private TableColumn<Member, String> address;
    @FXML
    private TableColumn<Member, String> fiscalCode;
    @FXML
    private TableColumn<Member, String> username;

    TableView.TableViewSelectionModel<Member> selectionModel;

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
        initTableView();
    }

    public void onViewBoatsOfMemberClick() {
        Member member = selectionModel.getSelectedItem();
        new ViewBoatsOfMemberPage(currentEmployee, member).render();
    }

    private void getAllData() {
        Message<Employee> message = Message.newInstance(currentEmployee, MessageType.GET_ALL_MEMBERS);

        try {
            Helpers.getOutputStream().writeObject(message);

            Object o = Helpers.getInputStream().readObject();

            if (o instanceof Reply) {
                Reply reply = (Reply) o;
                if (reply.getResponseCode() == ReplyType.OK) {
                    // make cast of serializable array of response
                    ArrayList<Serializable> serializableArrayList = reply.getResults();
                    members = (ArrayList<Member>) serializableArrayList.get(0);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initTableView(){
        name.setCellValueFactory(new PropertyValueFactory<Member, String>("Name"));
        surname.setCellValueFactory(new PropertyValueFactory<Member, String>("Surname"));
        address.setCellValueFactory(new PropertyValueFactory<Member, String>("Address"));
        fiscalCode.setCellValueFactory(new PropertyValueFactory<Member, String>("FiscalCode"));
        username.setCellValueFactory(new PropertyValueFactory<Member, String>("Username"));

        if (members != null)
            tableView.getItems().setAll(members);
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new EmployeeHomePage(currentEmployee).render();
    }
}
