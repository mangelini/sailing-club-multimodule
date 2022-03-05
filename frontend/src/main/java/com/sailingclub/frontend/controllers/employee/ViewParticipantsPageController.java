package com.sailingclub.frontend.controllers.employee;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.employeePages.ViewAllRacesPage;
import entities.Boat;
import entities.Employee;
import entities.Race;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import messageManagement.Message;
import messageManagement.MessageType;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class ViewParticipantsPageController {
    Employee currentEmployee;
    Race selectedRace;
    ArrayList<Boat> boats;

    @FXML
    private Button backButton;
    @FXML
    private TableView<Boat> tableView;
    @FXML
    private TableColumn<Boat, String> username;
    @FXML
    private TableColumn<Boat, String> name;
    @FXML
    private TableColumn<Boat, String> boat;


    /**
     * Initialize FXML components,
     */
    @FXML
    public void initialize(Employee employee, Race selectedRace) {
        this.currentEmployee = employee;
        this.selectedRace = selectedRace;
        this.backButton.setStyle("-fx-background-radius: 5em; ");

        getData();
        initTableView();
    }

    private void initTableView() {
        boat.setCellValueFactory(new PropertyValueFactory<Boat, String>("Name"));

        username.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Boat, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Boat, String> data) {
                if (data.getValue().getOwner().getUsername() != null) {
                    return new ReadOnlyStringWrapper(data.getValue().getOwner().getUsername());
                }

                return new ReadOnlyStringWrapper("");
            }
        });

        name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Boat, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Boat, String> data) {
                if (data.getValue().getOwner().getName() != null) {
                    return new ReadOnlyStringWrapper(data.getValue().getOwner().getName());
                }

                return new ReadOnlyStringWrapper("");
            }
        });

        if (boats != null)
            tableView.getItems().setAll(boats);
    }

    private void getData() {
        Message<Employee> message = new Message<>(currentEmployee, MessageType.VIEW_PARTICIPANTS, "", selectedRace);

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

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new ViewAllRacesPage(currentEmployee).render();
    }
}
