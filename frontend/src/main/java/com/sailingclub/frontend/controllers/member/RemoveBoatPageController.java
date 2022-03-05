package com.sailingclub.frontend.controllers.member;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.memberPages.MemberHomePage;
import entities.Boat;
import entities.Member;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import messageManagement.Message;
import messageManagement.MessageType;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class RemoveBoatPageController {
    private Member currentMember;
    private ArrayList<Boat> boats;

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
    public void initialize(Member currentMember) {
        this.backButton.setStyle("-fx-background-radius: 5em; ");
        this.currentMember = currentMember;

        this.selectionModel = tableView.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        getBoatsOfMember();

        initTableView();
    }

    private void getBoatsOfMember() {
        Message<Member> message = Message.newInstance(currentMember, MessageType.GET_ALL_BOATS);

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

    private void initTableView() {
        name.setCellValueFactory(new PropertyValueFactory<Boat, String>("Name"));
        length.setCellValueFactory(new PropertyValueFactory<Boat, Double>("Length"));
        ID.setCellValueFactory(new PropertyValueFactory<Boat, Integer>("ID"));

        if (boats != null)
            tableView.getItems().setAll(boats);
    }

    public void onRemoveBoatClick(){
        Boat boat = selectionModel.getSelectedItem();
        Message<Member> message = Message.newInstance(currentMember, MessageType.REMOVE_BOAT, boat);

        try {
            Helpers.getOutputStream().writeObject(message);

            Object o = Helpers.getInputStream().readObject();

            if (o instanceof Reply) {
                Reply reply = (Reply) o;
                if (reply.getResponseCode() == ReplyType.OK){
                    tableView.getItems().remove(boat);
                }
                if (reply.getResponseCode() == ReplyType.ERROR) {
                    Helpers.showStage("Some error occurred while deleting the boat");
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
        new MemberHomePage(currentMember).render();
    }
}
