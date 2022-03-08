package com.sailingclub.frontend.employeePages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.employee.CheckStorageFeePaymentsController;
import entities.Employee;
import javafx.fxml.FXMLLoader;

public class CheckStorageFeePaymentsPage {
    Employee currentEmployee;

    public CheckStorageFeePaymentsPage(Employee employee) { this.currentEmployee = employee; }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "/employee/check-storage-fee-payments-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            CheckStorageFeePaymentsController checkStorageFeePaymentsController
                    = fxmlLoader.<CheckStorageFeePaymentsController>getController();
            checkStorageFeePaymentsController.initialize(currentEmployee);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in CheckMembershipFeePaymentsPage render()");
        }
    }
}
