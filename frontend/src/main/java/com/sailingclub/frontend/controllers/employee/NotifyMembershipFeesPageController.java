package com.sailingclub.frontend.controllers.employee;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.employeePages.EmployeeHomePage;
import entities.Employee;
import entities.MembershipFee;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import messageManagement.Message;
import messageManagement.MessageType;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NotifyMembershipFeesPageController {
    Employee currentEmployee;
    ArrayList<MembershipFee> fees;

    @FXML
    private Button backButton;
    @FXML
    private TableView<MembershipFee> tableView;
    @FXML
    private TableColumn<MembershipFee, String> username;
    @FXML
    private TableColumn<MembershipFee, String> name;
    @FXML
    private TableColumn<MembershipFee, String> date;

    TableView.TableViewSelectionModel<MembershipFee> selectionModel;

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

    private void getAllData() {
        Message<Employee> message = new Message<>(currentEmployee, MessageType.GET_EMPLOYEE_MEM_FEES_TO_PAY, "");

        try {
            Helpers.getOutputStream().writeObject(message);

            Object o = Helpers.getInputStream().readObject();

            if (o instanceof Reply) {
                Reply reply = (Reply) o;
                if (reply.getResponseCode() == ReplyType.OK) {
                    // make cast of serializable array of response
                    ArrayList<Serializable> serializableArrayList = reply.getResults();
                    fees = (ArrayList<MembershipFee>) serializableArrayList.get(0);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initTableView() {
        // Fill table with data passed by backend
        name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MembershipFee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<MembershipFee, String> data) {
                if(data.getValue().getMember().getName() != null)
                    return new ReadOnlyStringWrapper(data.getValue().getMember().getName());

                return new ReadOnlyStringWrapper("");
            }
        });

        username.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MembershipFee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<MembershipFee, String> data) {
                if (data.getValue().getMember().getUsername() != null)
                    return new ReadOnlyStringWrapper(data.getValue().getMember().getUsername());

                return new ReadOnlyStringWrapper("");
            }
        });

        date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MembershipFee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<MembershipFee, String> data) {
                if (data.getValue().getDate() != null) {
                    String date = new SimpleDateFormat("dd.MM.yyyy").format(data.getValue().getDate());
                    return new ReadOnlyStringWrapper(date);
                }

                return new ReadOnlyStringWrapper("");
            }
        });

        for (MembershipFee membershipFee : fees) {
            tableView.getItems().add(membershipFee);
        }
    }

    public void onNotifyMemberClick(){
        MembershipFee selectedFee = selectionModel.getSelectedItem();
        Message<Employee> message = new Message<>(currentEmployee, MessageType.NOTIFY_MEMBER_MEM_FEES, "", selectedFee);

        try {
            Helpers.getOutputStream().writeObject(message);

            Object o = Helpers.getInputStream().readObject();

            if (o instanceof Reply) {
                Reply reply = (Reply) o;
                if (reply.getResponseCode() == ReplyType.OK){
                    tableView.getItems().remove(selectedFee);
                }
                if (reply.getResponseCode() == ReplyType.ERROR) {
                    Helpers.showStage("Some error occurred while notifying member");
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
        new EmployeeHomePage(currentEmployee).render();
    }
}
