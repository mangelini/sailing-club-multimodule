package com.sailingclub.frontend.employeePages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.employee.ViewMembersController;
import entities.Employee;
import javafx.fxml.FXMLLoader;

public class ViewMembersPage {
    Employee currentEmployee;

    public ViewMembersPage(Employee employee) { this.currentEmployee = employee; }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/employee/view-members-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            ViewMembersController viewMembersController = fxmlLoader.<ViewMembersController>getController();
            viewMembersController.initialize(currentEmployee);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in ViewMembersPage render()");
        }
    }
}
