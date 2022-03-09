package com.sailingclub.frontend.authPages.admin;

import com.sailingclub.frontend.Helpers;
import javafx.fxml.FXMLLoader;

public class AdminSignInPage {
    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/auth/admin-sign-in-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in AdminSignIn render()");
        }
    }
}
