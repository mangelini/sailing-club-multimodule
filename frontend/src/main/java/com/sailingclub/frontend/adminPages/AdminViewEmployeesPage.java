package com.sailingclub.frontend.adminPages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.admin.AdminViewEmployeesController;
import entities.Employee;
import javafx.fxml.FXMLLoader;

public class AdminViewEmployeesPage {
    Employee currentEmployee;

    public AdminViewEmployeesPage(Employee employee) { this.currentEmployee = employee; }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/admin/admin-view-employees-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            AdminViewEmployeesController adminViewEmployeesController = fxmlLoader.<AdminViewEmployeesController>getController();
            adminViewEmployeesController.initialize(currentEmployee);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in AdminViewEmployees render()");
        }
    }
}
