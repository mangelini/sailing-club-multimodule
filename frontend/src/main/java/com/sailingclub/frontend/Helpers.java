package com.sailingclub.frontend;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Helper class with code reused throughout the entire frontend application
 */
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
