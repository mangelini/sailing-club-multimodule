package com.sailingclub.frontend.employeePages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.employee.RacesHomePageController;
import entities.Employee;
import javafx.fxml.FXMLLoader;

public class RacesHomePage {
    Employee currentEmployee;

    public RacesHomePage(Employee employee) { this.currentEmployee = employee; }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/employee/races-home-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            RacesHomePageController racesHomePageController = fxmlLoader.<RacesHomePageController>getController();
            racesHomePageController.initialize(currentEmployee);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in NotifyStorageFees render()");
        }
    }
}
