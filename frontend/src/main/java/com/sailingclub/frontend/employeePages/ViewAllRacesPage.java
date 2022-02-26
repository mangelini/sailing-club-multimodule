package com.sailingclub.frontend.employeePages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.employee.RacesHomePageController;
import com.sailingclub.frontend.controllers.employee.ViewAllRacesPageController;
import entities.Employee;
import javafx.fxml.FXMLLoader;

public class ViewAllRacesPage {
    Employee currentEmployee;

    public ViewAllRacesPage(Employee employee) { this.currentEmployee = employee; }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/employee/view-all-races-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            ViewAllRacesPageController viewAllRacesPageController = fxmlLoader.<ViewAllRacesPageController>getController();
            viewAllRacesPageController.initialize(currentEmployee);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in NotifyStorageFees render()");
        }
    }
}
