package com.sailingclub.frontend.controllers.admin;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.adminPages.AdminHomePage;
import entities.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

public class AdminViewEmployeesController {
    Employee currentEmployee;
    ArrayList<Employee> employees = new ArrayList<>();

    @FXML
    private Button backButton;
    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, String> username;

    /**
     * Initialize FXML components,
     */
    @FXML
    public void initialize(Employee employee) {
        this.currentEmployee = employee;
        this.backButton.setStyle("-fx-background-radius: 5em; ");

        getAllData();
        initTableView();
    }

    private void getAllData() {
        Message<Employee> message = Message.newInstance(currentEmployee, MessageType.GET_ALL_EMPLOYEES);

        try {
            Helpers.getOutputStream().writeObject(message);

            Object o = Helpers.getInputStream().readObject();

            if (o instanceof Reply) {
                Reply reply = (Reply) o;
                if (reply.getResponseCode() == ReplyType.OK) {
                    ArrayList<Serializable> serializableArrayList = reply.getResults();
                    employees = (ArrayList<Employee>) serializableArrayList.get(0);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initTableView() {
        username.setCellValueFactory(new PropertyValueFactory<Employee, String>("Username"));
        if (employees != null)
            tableView.getItems().setAll(employees);
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new AdminHomePage(currentEmployee).render();
    }
}
