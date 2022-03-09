package com.sailingclub.frontend.adminPages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.admin.AdminAddEmployeeController;
import entities.Employee;
import javafx.fxml.FXMLLoader;

public class AdminAddEmployeePage {
    Employee currentEmployee;

    public AdminAddEmployeePage(Employee employee) { this.currentEmployee = employee; }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/admin/admin-add-employee-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            AdminAddEmployeeController adminAddEmployeeController = fxmlLoader.<AdminAddEmployeeController>getController();
            adminAddEmployeeController.initialize(currentEmployee);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in AdminAddEmployee render()");
        }
    }
}
