package com.sailingclub.frontend;

import com.sailingclub.frontend.authPages.AuthHomePage;
import com.sailingclub.frontend.authPages.admin.AdminSignUpPage;
import entities.Employee;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import messageManagement.Message;
import messageManagement.MessageType;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.io.IOException;

/**
 * JavaFX application entry point
 */
public class App extends Application {
    @Override
    public void start(Stage stage) {
        // this makes available the scene object in every class
        // making it a sort of "global state"
        Helpers.staticScene = new Scene(new Pane());
        if (!adminExist()) new AdminSignUpPage().render();
        else new AuthHomePage().render();

        stage.setScene(Helpers.staticScene);
        stage.show();
    }

    private boolean adminExist() {
        Message<Employee> message = Message.newInstance(new Employee(), MessageType.ADMIN_EXIST);
        boolean adminExist = false;

        try {
            Helpers.getOutputStream().writeObject(message);

            Object o = Helpers.getInputStream().readObject();

            if (o instanceof Reply) {
                Reply reply = (Reply) o;
                if (reply.getResponseCode() == ReplyType.OK) {
                    adminExist = (Boolean) reply.getResults().get(0);
                    System.out.println(adminExist);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return adminExist;
    }

    public static void startFrontend(){
        launch();
    }
}