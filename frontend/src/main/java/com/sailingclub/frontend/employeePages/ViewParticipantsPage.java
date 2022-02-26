package com.sailingclub.frontend.employeePages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.employee.ViewParticipantsPageController;
import entities.Employee;
import entities.Race;
import javafx.fxml.FXMLLoader;

public class ViewParticipantsPage {
    Employee currentEmployee;
    Race selectedRace;

    public ViewParticipantsPage(Employee currentEmployee, Race selectedRace){
        this.currentEmployee = currentEmployee;
        this.selectedRace = selectedRace;
    }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/employee/view-participants-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            ViewParticipantsPageController viewParticipantsPageController = fxmlLoader.<ViewParticipantsPageController>getController();
            viewParticipantsPageController.initialize(currentEmployee, selectedRace);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in ViewParticipantsPage render()");
        }
    }
}
