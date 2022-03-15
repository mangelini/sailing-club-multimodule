package com.sailingclub.frontend.controllers.member;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.memberPages.MemberHomePage;
import com.sailingclub.frontend.memberPages.RegisterToRacePage;
import com.sailingclub.frontend.paymentType.PaymentTypePage;
import entities.Boat;
import entities.Member;
import entities.Race;
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
import java.util.concurrent.atomic.AtomicReference;

public class SelectBoatToRaceController {
    Member currentMember;
    Race raceToSubscribe;
    ArrayList<Boat> availableBoats = new ArrayList<>();
    AtomicReference<String> ref = new AtomicReference<>("");

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

    @FXML
    public void initialize(Member currentMember, Race raceToSubscribe){
        this.currentMember = currentMember;
        this.raceToSubscribe = raceToSubscribe;
        this.backButton.setStyle("-fx-background-radius: 5em; ");

        this.selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        getAllData();
        initTableView();
    }

    private void initTableView() {
        name.setCellValueFactory(new PropertyValueFactory<Boat, String>("Name"));
        length.setCellValueFactory(new PropertyValueFactory<Boat, Double>("Length"));
        ID.setCellValueFactory(new PropertyValueFactory<Boat, Integer>("ID"));

        if (availableBoats != null)
            tableView.getItems().setAll(availableBoats);
    }

    private void getAllData() {
        Message<Member> message = Message.newInstance(currentMember, MessageType.GET_AVAILABLE_BOATS);

        try {
            Helpers.getOutputStream().writeObject(message);

            Object o = Helpers.getInputStream().readObject();

            if (o instanceof Reply) {
                Reply reply = (Reply) o;
                if (reply.getResponseCode() == ReplyType.OK) {
                    // make cast of serializable array of response
                    ArrayList<Serializable> serializableArrayList = reply.getResults();
                    availableBoats = (ArrayList<Boat>) serializableArrayList.get(0);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onRegisterClick() {
        // let the user choose payment type
        new PaymentTypePage(ref).render();

        Boat selectedBoat = selectionModel.getSelectedItem();
        ArrayList<Serializable> arrayList = new ArrayList<>();

        // send selected race and selected boat to backend
        arrayList.add(raceToSubscribe);
        arrayList.add(selectedBoat);
        arrayList.add(ref.get());

        Message<Member> message = Message.newInstance(currentMember, MessageType.REGISTER_TO_RACE, arrayList);

        try {
            Helpers.getOutputStream().writeObject(message);

            Object o = Helpers.getInputStream().readObject();

            if (o instanceof Reply reply) {
                if (reply.getResponseCode() == ReplyType.OK) {
                    new MemberHomePage(currentMember).render();
                } else if (reply.getResponseCode() == ReplyType.ERROR) {
                    Helpers.showStage("An error occurred while registering boat to race");
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
        new RegisterToRacePage(currentMember).render();
    }
}
