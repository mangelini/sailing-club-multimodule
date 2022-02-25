package com.sailingclub.frontend.employeePages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.employee.NotifyMembershipFeesPageController;
import entities.Employee;
import javafx.fxml.FXMLLoader;

public class NotifyMembershipFeesPage {
    Employee currentEmployee;

    public NotifyMembershipFeesPage(Employee employee) { this.currentEmployee = employee; }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/employee/notify-membership-fees-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            NotifyMembershipFeesPageController notifyMembershipFeesPageController = fxmlLoader.<NotifyMembershipFeesPageController>getController();
            notifyMembershipFeesPageController.initialize(currentEmployee);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in NotifyMembershipFees render()");
        }
    }
}
