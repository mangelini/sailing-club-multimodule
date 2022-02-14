package com.sailingclub.frontend;

import com.sailingclub.frontend.authPages.AuthHomePage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // this makes available the scene object in every class
        // making it a sort of "global state"
        Helpers.staticScene = new Scene(new Pane());
        new AuthHomePage().render();

        stage.setScene(Helpers.staticScene);
        stage.show();
    }

    public static void startFrontend(){
        launch();
    }
}