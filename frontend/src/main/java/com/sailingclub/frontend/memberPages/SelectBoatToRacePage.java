package com.sailingclub.frontend.memberPages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.member.RegisterToRacePageController;
import com.sailingclub.frontend.controllers.member.SelectBoatToRaceController;
import entities.Member;
import entities.Race;
import javafx.fxml.FXMLLoader;

public class SelectBoatToRacePage {
    Member currentMember;
    Race raceToSubscribe;

    public SelectBoatToRacePage(Member currentMember, Race raceToSubscribe){
        this.currentMember = currentMember;
        this.raceToSubscribe = raceToSubscribe;
    }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/member/select-boat-to-race-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            SelectBoatToRaceController selectBoatToRaceController = fxmlLoader.<SelectBoatToRaceController>getController();
            selectBoatToRaceController.initialize(currentMember, raceToSubscribe);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in SelectBoatToRace render()");
        }
    }
}
