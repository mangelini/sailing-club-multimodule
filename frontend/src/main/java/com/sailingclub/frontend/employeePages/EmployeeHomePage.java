package com.sailingclub.frontend.employeePages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.employee.EmployeeHomePageController;
import entities.Employee;
import javafx.fxml.FXMLLoader;

public class EmployeeHomePage {
    Employee currentEmployee;

    public EmployeeHomePage(Employee employee) { this.currentEmployee = employee; }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/employee/employee-home-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            EmployeeHomePageController employeeHomePageController = fxmlLoader.<EmployeeHomePageController>getController();
            employeeHomePageController.initialize(currentEmployee);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in EmployeeHomePage render()");
        }

    }
}
