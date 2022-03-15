package com.sailingclub.frontend.paymentType;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class PaymentTypePage {
    AtomicReference<String> paymentType;
    public PaymentTypePage(AtomicReference<String> paymentType){
        this.paymentType = paymentType;
    }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            Stage newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setOnCloseRequest(Event::consume);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/payment-type-page.fxml"));
            Scene scene = new Scene(new Pane());
            scene.setRoot(fxmlLoader.load());

            // pass data from the previous page
            PaymentTypeController paymentTypeController = fxmlLoader.<PaymentTypeController>getController();
            paymentTypeController.initialize(paymentType);

            newStage.setScene(scene);
            newStage.showAndWait();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in PaymentTypePage render()");
        }

    }
}
