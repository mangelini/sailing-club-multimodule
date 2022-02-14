package com.sailingclub.frontend;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import messageManagement.Message;
import messageManagement.Reply;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

    public void sendObjectToServer(Message message){
        try {
            os.writeObject(message);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Reply readReplyFromServer() {
        Reply reply = null;
        Object o = null;

        try {
            o = is.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while reading object from input stream");
        } finally {
            if(o instanceof Reply){
                reply = (Reply) o;
            }
        }

        return reply;
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
