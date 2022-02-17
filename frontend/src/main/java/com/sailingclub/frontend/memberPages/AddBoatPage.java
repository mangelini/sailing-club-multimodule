package com.sailingclub.frontend.memberPages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.member.AddBoatPageController;
import entities.Member;
import javafx.fxml.FXMLLoader;

public class AddBoatPage {
    Member currentMember;

    public AddBoatPage(Member currentMember){
        this.currentMember = currentMember;
    }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/member/add-boat-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            // pass data from the previous page
            AddBoatPageController addBoatPageController = fxmlLoader.<AddBoatPageController>getController();
            addBoatPageController.initialize(currentMember);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in AddBoatPage render()");
        }

    }
}
