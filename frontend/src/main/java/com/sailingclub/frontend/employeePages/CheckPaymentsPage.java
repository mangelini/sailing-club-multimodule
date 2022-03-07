package com.sailingclub.frontend.employeePages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.employee.CheckPaymentsPageController;
import com.sailingclub.frontend.controllers.employee.EmployeeHomePageController;
import entities.Employee;
import javafx.fxml.FXMLLoader;

public class CheckPaymentsPage {
    Employee currentEmployee;

    public CheckPaymentsPage(Employee employee) { this.currentEmployee = employee; }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/employee/check-payments-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            CheckPaymentsPageController checkPaymentsPageController = fxmlLoader.<CheckPaymentsPageController>getController();
            checkPaymentsPageController.initialize(currentEmployee);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in EmployeeHomePage render()");
        }
    }
}
