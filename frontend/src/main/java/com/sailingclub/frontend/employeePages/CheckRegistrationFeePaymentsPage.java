package com.sailingclub.frontend.employeePages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.employee.CheckRegistrationFeePaymentsController;
import entities.Employee;
import javafx.fxml.FXMLLoader;

public class CheckRegistrationFeePaymentsPage {
    Employee currentEmployee;

    public CheckRegistrationFeePaymentsPage(Employee employee) { this.currentEmployee = employee; }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "/employee/check-registration-fee-payments-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            CheckRegistrationFeePaymentsController checkRegistrationFeePaymentsController
                    = fxmlLoader.<CheckRegistrationFeePaymentsController>getController();
            checkRegistrationFeePaymentsController.initialize(currentEmployee);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in CheckRegistrationFeePaymentsPage render()");
        }
    }
}
