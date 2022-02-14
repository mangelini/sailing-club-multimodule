package com.sailingclub.frontend;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Helpers {
    public static Scene staticScene;

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
}
