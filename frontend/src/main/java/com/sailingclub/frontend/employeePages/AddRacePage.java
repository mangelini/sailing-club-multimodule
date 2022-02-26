package com.sailingclub.frontend.employeePages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.employee.AddRacePageController;
import entities.Employee;
import javafx.fxml.FXMLLoader;

public class AddRacePage {
    Employee currentEmployee;

    public AddRacePage(Employee employee) { this.currentEmployee = employee; }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/employee/add-race-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            AddRacePageController addRacePageController = fxmlLoader.<AddRacePageController>getController();
            addRacePageController.initialize(currentEmployee);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in AddRacePage render()");
        }
    }
}
