package com.sailingclub.frontend.memberPages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.member.RegisterToRacePageController;
import entities.Member;
import javafx.fxml.FXMLLoader;

public class RegisterToRacePage {
    Member currentMember;

    public RegisterToRacePage(Member currentMember){
        this.currentMember = currentMember;
    }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/member/register-to-race-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            RegisterToRacePageController registerToRacePageController = fxmlLoader.<RegisterToRacePageController>getController();
            registerToRacePageController.initialize(currentMember);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in RegisterToRacePage render()");
        }
    }
}
