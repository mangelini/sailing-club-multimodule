package com.sailingclub.frontend.paymentType;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class PaymentTypeController {
    AtomicReference<String> paymentType;

    @FXML
    RadioButton creditCard;
    @FXML
    RadioButton bankTransfer;
    @FXML
    Button select;
    @FXML
    ToggleGroup group;

    /**
     * Initialize FXML components,
     */
    @FXML
    public void initialize(AtomicReference<String> paymentType){
        this.paymentType = paymentType;
        creditCard.setToggleGroup(group);
        bankTransfer.setToggleGroup(group);
    }

    public void onSelectClick(){
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        this.paymentType.set(selectedRadioButton.getText());
        Stage stage = (Stage) select.getScene().getWindow();
        stage.close();
    }
}
