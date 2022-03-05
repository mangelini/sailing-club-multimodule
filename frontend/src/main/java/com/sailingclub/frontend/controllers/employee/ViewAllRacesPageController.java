package com.sailingclub.frontend.controllers.employee;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.employeePages.RacesHomePage;
import com.sailingclub.frontend.employeePages.ViewParticipantsPage;
import entities.Employee;
import entities.Race;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ViewAllRacesPageController {
    Employee currentEmployee;
    ArrayList<Race> races = new ArrayList<>();

    @FXML
    private Button backButton;
    @FXML
    private TableView<Race> tableView;
    @FXML
    private TableColumn<Race, String> name;
    @FXML
    private TableColumn<Race, String> loc;
    @FXML
    private TableColumn<Race, String> date;
    @FXML
    private TableColumn<Race, String> expired;

    TableView.TableViewSelectionModel<Race> selectionModel;

    /**
     * Initialize FXML components,
     */
    @FXML
    public void initialize(Employee employee) {
        this.currentEmployee = employee;
        this.backButton.setStyle("-fx-background-radius: 5em; ");

        this.selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        getData();
        initTableView();
    }

    private void getData(){
        Message<Employee> message = new Message<>(currentEmployee, MessageType.GET_ALL_RACES, "");

        try {
            Helpers.getOutputStream().writeObject(message);

            Object o = Helpers.getInputStream().readObject();

            if (o instanceof Reply) {
                Reply reply = (Reply) o;
                if (reply.getResponseCode() == ReplyType.OK) {
                    // make cast of serializable array of response
                    ArrayList<Serializable> serializableArrayList = reply.getResults();
                    races = (ArrayList<Race>) serializableArrayList.get(0);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initTableView() {
        name.setCellValueFactory(new PropertyValueFactory<Race, String>("Name"));
        loc.setCellValueFactory(new PropertyValueFactory<Race, String>("Location"));

        date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Race, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Race, String> data) {
                if (data.getValue().getDate() != null) {
                    String date = new SimpleDateFormat("dd.MM.yyyy").format(data.getValue().getDate());
                    return new ReadOnlyStringWrapper(date);
                }

                return new ReadOnlyStringWrapper("");
            }
        });

        expired.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Race, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Race, String> data) {
                if (data.getValue().isExpired() == 1) {
                    return new ReadOnlyStringWrapper("yes");
                } else {
                    return new ReadOnlyStringWrapper("no");
                }
            }
        });

        if (races != null)
            tableView.getItems().setAll(races);
    }

    public void onViewParticipantsClick() {
        Race race = selectionModel.getSelectedItem();
        new ViewParticipantsPage(currentEmployee, race).render();
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new RacesHomePage(currentEmployee).render();
    }
}
