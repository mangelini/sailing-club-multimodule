package com.sailingclub.frontend;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import messageManagement.Message;
import messageManagement.Reply;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.atomic.AtomicReference;

public class Helpers {
    public static Scene staticScene;
    private static ObjectInputStream is;
    private static ObjectOutputStream os;

    /**
     * Creates a new popup window to display generic
     * messages to the user
     * @param message that needs to be showed to the user
     */
    public static void showStage(String message){
        Stage newStage = new Stage();
        VBox comp = new VBox();
        Label label = new Label();
        label.setText(message);
        comp.getChildren().add(label);
        comp.setAlignment(Pos.CENTER);

        Scene stageScene = new Scene(comp, 300, 100);
        newStage.setScene(stageScene);
        newStage.showAndWait();
    }

    /**
     * Popup window to let the user decide whether to pay with credit card or bank transfer
     * @param paymentType Reference used to decide which choice made the user
     */
    public static void showPaymentType(AtomicReference<String> paymentType) {
        Stage newStage = new Stage();
        VBox comp = new VBox();
        Label label = new Label();
        label.setText("Choose Payment Type");

        RadioButton creditCard = new RadioButton("Credit Card");
        RadioButton bankTransfer = new RadioButton("Bank Transfer");
        ToggleGroup radioGroup = new ToggleGroup();
        creditCard.setToggleGroup(radioGroup);
        bankTransfer.setToggleGroup(radioGroup);

        Button button = new Button("SELECT");

        comp.getChildren().add(label);
        comp.getChildren().add(creditCard);
        comp.getChildren().add(bankTransfer);
        comp.getChildren().add(button);
        comp.setAlignment(Pos.CENTER);

        Scene stageScene = new Scene(comp, 400, 300);
        newStage.setScene(stageScene);
        newStage.showAndWait();

        button.setOnAction(value ->  {
            RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
            paymentType.set(selectedRadioButton.getText());
            newStage.close();
        });
    }

    /**
     * Setter for input stream
     * @param is Input Stream object
     */
    public static void setInputStream(ObjectInputStream is) {
        Helpers.is = is;
    }

    /**
     * Setter for output stream
     * @param os Output Stream object
     */
    public static void setOutputStream(ObjectOutputStream os) {
        Helpers.os = os;
    }

    /**
     * Getter for input stream
     * @return is
     */
    public static ObjectInputStream getInputStream() {
        return is;
    }

    /**
     * Getter for output stream
     * @return os
     */
    public static ObjectOutputStream getOutputStream() {
        return os;
    }
}
