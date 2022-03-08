package com.sailingclub.frontend.controllers.employee;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.employeePages.CheckPaymentsPage;
import entities.Employee;
import entities.RegistrationFee;
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

public class CheckRegistrationFeePaymentsController {
    Employee currentEmployee;
    ArrayList<RegistrationFee> fees;

    @FXML
    private Button backButton;
    @FXML
    private TableView<RegistrationFee> tableView;
    @FXML
    private TableColumn<RegistrationFee, String> boatName;
    @FXML
    private TableColumn<RegistrationFee, String> ownerName;
    @FXML
    private TableColumn<RegistrationFee, String> raceName;
    @FXML
    private TableColumn<RegistrationFee, String> paymentType;
    @FXML
    private TableColumn<RegistrationFee, String> fee;
    @FXML
    private TableColumn<RegistrationFee, String> date;

    TableView.TableViewSelectionModel<RegistrationFee> selectionModel;

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
        Message<Employee> message = Message.newInstance(currentEmployee, MessageType.GET_ALL_REGISTRATION_FEES);

        try {
            Helpers.getOutputStream().writeObject(message);

            Object o = Helpers.getInputStream().readObject();

            if (o instanceof Reply) {
                Reply reply = (Reply) o;
                if (reply.getResponseCode() == ReplyType.OK){
                    ArrayList<Serializable> serializableArrayList = reply.getResults();
                    fees = (ArrayList<RegistrationFee>) serializableArrayList.get(0);
                }
                if (reply.getResponseCode() == ReplyType.ERROR) {
                    Helpers.showStage("Some error occurred while retrieving data");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initTableView() {
        boatName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegistrationFee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RegistrationFee, String> data) {
                if(data.getValue().getBoat().getName() != null)
                    return new ReadOnlyStringWrapper(data.getValue().getBoat().getName());

                return new ReadOnlyStringWrapper("");
            }
        });

        ownerName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegistrationFee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RegistrationFee, String> data) {
                if(data.getValue().getBoat().getOwner().getName() != null)
                    return new ReadOnlyStringWrapper(data.getValue().getBoat().getOwner().getName());

                return new ReadOnlyStringWrapper("");
            }
        });

        date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegistrationFee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RegistrationFee, String> data) {
                if(data.getValue().getDate() != null){
                    String date = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(data.getValue().getDate());
                    return new ReadOnlyStringWrapper(date);
                }

                return new ReadOnlyStringWrapper("");
            }
        });

        paymentType.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegistrationFee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RegistrationFee, String> data) {
                if(data.getValue().getPaymentType() != null)
                    return new ReadOnlyStringWrapper(data.getValue().getPaymentType());

                return new ReadOnlyStringWrapper("");
            }
        });

        fee.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegistrationFee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RegistrationFee, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().getFee().toString());
            }
        });

        raceName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegistrationFee, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RegistrationFee, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().getRace().getName());
            }
        });

        if (fees != null)
            tableView.getItems().setAll(fees);
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new CheckPaymentsPage(currentEmployee).render();
    }
}
