package com.sailingclub.frontend.controllers.admin;

import com.sailingclub.frontend.adminPages.AdminAddEmployeePage;
import com.sailingclub.frontend.adminPages.AdminHomePage;
import com.sailingclub.frontend.adminPages.AdminViewEmployeesPage;
import com.sailingclub.frontend.authPages.AuthHomePage;
import entities.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminHomePageController {
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

    public void onAddEmployeeClick(){
        new AdminAddEmployeePage(currentEmployee).render();
    }

    public void onViewEmployeesClick(){
        new AdminViewEmployeesPage(currentEmployee).render();
    }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new AuthHomePage().render();
    }
}
