package com.sailingclub.frontend.controllers.member;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.memberPages.MemberHomePage;
import com.sailingclub.frontend.memberPages.SelectBoatToRacePage;
import entities.Member;
import entities.Race;
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

public class RegisterToRacePageController {
    Member currentMember;
    ArrayList<Race> races;

    @FXML
    private Button backButton;
    @FXML
    private TableView<Race> tableView;
    @FXML
    private TableColumn<Race, String> name;
    @FXML
    private TableColumn<Race, String> date;
    @FXML
    private TableColumn<Race, String> loc;

    TableView.TableViewSelectionModel<Race> selectionModel;

    @FXML
    public void initialize(Member currentMember){
        this.currentMember = currentMember;
        this.backButton.setStyle("-fx-background-radius: 5em; ");

        this.selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        getAllData();
        initTableView();
    }

    public void onChooseBoatClick(){
        Race selectedRace = selectionModel.getSelectedItem();

        new SelectBoatToRacePage(currentMember, selectedRace).render();
    }

    private void getAllData(){
        Message<Member> message = new Message<>(currentMember, MessageType.GET_AVAILABLE_RACES, "");

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

        loc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Race, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Race, String> data) {
                if (data.getValue().getLocation() != null) {
                    return new ReadOnlyStringWrapper(data.getValue().getLocation());
                }

                return new ReadOnlyStringWrapper("");
            }
        });

        name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Race, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Race, String> data) {
                if (data.getValue().getName() != null) {
                    return new ReadOnlyStringWrapper(data.getValue().getName());
                }

                return new ReadOnlyStringWrapper("");
            }
        });

        for (Race race : races) {
            tableView.getItems().add(race);
        }
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new MemberHomePage(currentMember).render();
    }
}
