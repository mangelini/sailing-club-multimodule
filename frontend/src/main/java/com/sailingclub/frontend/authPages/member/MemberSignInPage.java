package com.sailingclub.frontend.authPages.member;

import com.sailingclub.frontend.Helpers;
import javafx.fxml.FXMLLoader;

public class MemberSignInPage {
    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/auth/member-sign-in-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());
        } catch (Exception e){
            System.out.println("Error in MemberSignIn render()");
        }
    }
}
