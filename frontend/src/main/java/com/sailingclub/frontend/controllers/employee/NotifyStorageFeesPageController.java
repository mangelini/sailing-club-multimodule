package com.sailingclub.frontend.controllers.employee;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.employeePages.EmployeeHomePage;
import entities.Boat;
import entities.Employee;
import entities.Member;
import entities.StorageFee;
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
import java.util.Date;

public class NotifyStorageFeesPageController {
    Employee currentEmployee;
    ArrayList<Member> owners;
    ArrayList<Boat> boats;
    ArrayList<Date> dates;
    ArrayList<StorageFee> fees;

    @FXML
    private Button backButton;
    @FXML
    private TableView<StorageFee> tableView;
    @FXML
    private TableColumn<StorageFee, String> boat;
    @FXML
    private TableColumn<StorageFee, String> member;
    @FXML
    private TableColumn<StorageFee, String> date;

    TableView.TableViewSelectionModel<StorageFee> selectionModel;

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
                    /*dates = (ArrayList<Date>) serializableArrayList.get(0);
                    owners = (ArrayList<Member>) serializableArrayList.get(1);
                    boats = (ArrayList<Boat>) serializableArrayList.get(2);*/
                    fees = (ArrayList<StorageFee>) serializableArrayList.get(0);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initTableView() {
        // Fill table with data passed by backend
        member.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StorageFee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StorageFee, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().getBoat().getOwner().getUsername());
            }
        });

        boat.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StorageFee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StorageFee, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().getBoat().getName());
            }
        });

        date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StorageFee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StorageFee, String> data) {
                String date = new SimpleDateFormat("dd.MM.yyyy").format(data.getValue().getDate());
                return new ReadOnlyStringWrapper(date);
            }
        });

        if (fees != null) tableView.getItems().setAll(fees);
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new EmployeeHomePage(currentEmployee).render();
    }
}
