package com.sailingclub.frontend.controllers.employee;

import com.sailingclub.frontend.authPages.AuthHomePage;
import com.sailingclub.frontend.employeePages.NotifyStorageFeesPage;
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

    public void onNotifyMembershipFeesClick() {

    }

    public void onNotifyStorageFeesClick() {
        new NotifyStorageFeesPage(currentEmployee).render();
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new AuthHomePage().render();
    }
}
