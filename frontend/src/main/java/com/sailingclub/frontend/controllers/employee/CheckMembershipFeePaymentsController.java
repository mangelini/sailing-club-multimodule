package com.sailingclub.frontend.controllers.employee;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.employeePages.EmployeeHomePage;
import entities.Employee;
import entities.Member;
import entities.MembershipFee;
import javafx.beans.property.ReadOnlyDoubleWrapper;
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

public class CheckMembershipFeePaymentsController {
    Employee currentEmployee;
    ArrayList<MembershipFee> fees;

    @FXML
    private Button backButton;
    @FXML
    private TableView<MembershipFee> tableView;
    @FXML
    private TableColumn<MembershipFee, String> name;
    @FXML
    private TableColumn<MembershipFee, String> username;
    @FXML
    private TableColumn<MembershipFee, String> date;
    @FXML
    private TableColumn<MembershipFee, String> paymentType;
    @FXML
    private TableColumn<MembershipFee, String> fee;

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
        Message<Employee> message = Message.newInstance(currentEmployee, MessageType.GET_ALL_MEMBERSHIP_FEES);

        try {
            Helpers.getOutputStream().writeObject(message);

            Object o = Helpers.getInputStream().readObject();

            if (o instanceof Reply) {
                Reply reply = (Reply) o;
                if (reply.getResponseCode() == ReplyType.OK){
                    ArrayList<Serializable> serializableArrayList = reply.getResults();
                    fees = (ArrayList<MembershipFee>) serializableArrayList.get(0);
                }
                if (reply.getResponseCode() == ReplyType.ERROR) {
                    Helpers.showStage("Some error occurred while retrieving data");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initTableView(){
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
                if(data.getValue().getMember().getUsername() != null)
                    return new ReadOnlyStringWrapper(data.getValue().getMember().getUsername());

                return new ReadOnlyStringWrapper("");
            }
        });

        date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MembershipFee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<MembershipFee, String> data) {
                if(data.getValue().getDate() != null){
                    String date = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(data.getValue().getDate());
                    return new ReadOnlyStringWrapper(date);
                }

                return new ReadOnlyStringWrapper("");
            }
        });

        paymentType.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MembershipFee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<MembershipFee, String> data) {
                if(data.getValue().getPaymentType() != null)
                    return new ReadOnlyStringWrapper(data.getValue().getPaymentType());

                return new ReadOnlyStringWrapper("");
            }
        });

        fee.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MembershipFee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<MembershipFee, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().getFee().toString());
            }
        });

        if (fees != null)
            tableView.getItems().setAll(fees);
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new EmployeeHomePage(currentEmployee).render();
    }
}
