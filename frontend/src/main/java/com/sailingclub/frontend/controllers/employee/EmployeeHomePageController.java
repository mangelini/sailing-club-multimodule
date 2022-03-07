package com.sailingclub.frontend.controllers.employee;

import com.sailingclub.frontend.authPages.AuthHomePage;
import com.sailingclub.frontend.employeePages.CheckPaymentsPage;
import com.sailingclub.frontend.employeePages.NotifyMembershipFeesPage;
import com.sailingclub.frontend.employeePages.NotifyStorageFeesPage;
import com.sailingclub.frontend.employeePages.RacesHomePage;
import entities.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class EmployeeHomePageController {
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

    public void onNotifyMembershipFeesClick() { new NotifyMembershipFeesPage(currentEmployee).render(); }

    public void onNotifyStorageFeesClick() {
        new NotifyStorageFeesPage(currentEmployee).render();
    }

    public void onRacesClick() { new RacesHomePage(currentEmployee).render(); }

    public void onCheckPaymentsClick() {
        new CheckPaymentsPage(currentEmployee).render();
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new AuthHomePage().render();
    }
}
