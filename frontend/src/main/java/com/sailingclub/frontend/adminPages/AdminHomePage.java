package com.sailingclub.frontend.adminPages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.admin.AdminHomePageController;
import entities.Employee;
import javafx.fxml.FXMLLoader;

public class AdminHomePage {
    Employee currentEmployee;

    public AdminHomePage(Employee employee) { this.currentEmployee = employee; }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/admin/admin-home-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            AdminHomePageController adminHomePageController = fxmlLoader.<AdminHomePageController>getController();
            adminHomePageController.initialize(currentEmployee);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in AdminHomePage render()");
        }
    }
}
