package com.sailingclub.frontend.controllers.employee;

import com.sailingclub.frontend.employeePages.CheckMembershipFeePaymentsPage;
import com.sailingclub.frontend.employeePages.EmployeeHomePage;
import entities.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class CheckPaymentsPageController {
    Employee currentEmployee;

    @FXML
    private Button backButton;

    /**
     * Initialize FXML components,
     */
    @FXML
    public void initialize(Employee employee) {
        this.currentEmployee = employee;
        this.backButton.setStyle("-fx-background-radius: 5em; ");
    }

    public void onCheckStorageFeePaymentsClick() {

    }

    public void onCheckMembershipFeePaymentsClick() {
        new CheckMembershipFeePaymentsPage(currentEmployee).render();
    }

    public void onCheckRegistrationFeePaymentsClick() {

    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new EmployeeHomePage(currentEmployee).render();
    }
}
