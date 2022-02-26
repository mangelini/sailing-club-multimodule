package com.sailingclub.frontend.controllers.employee;

import com.sailingclub.frontend.employeePages.AddRacePage;
import com.sailingclub.frontend.employeePages.EmployeeHomePage;
import com.sailingclub.frontend.employeePages.ViewAllRacesPage;
import entities.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class RacesHomePageController {
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

    public void onAddRaceClick(){
        new AddRacePage(currentEmployee).render();
    }

    public void onViewAllRacesClick(){ new ViewAllRacesPage(currentEmployee).render(); }

    /**
     * Goes back to the previous page
     */
    public void onGoBackClick() throws IOException {
        new EmployeeHomePage(currentEmployee).render();
    }
}
