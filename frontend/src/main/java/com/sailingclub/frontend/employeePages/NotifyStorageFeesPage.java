package com.sailingclub.frontend.employeePages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.employee.NotifyStorageFeesPageController;
import entities.Employee;
import javafx.fxml.FXMLLoader;

public class NotifyStorageFeesPage {
    Employee currentEmployee;

    public NotifyStorageFeesPage(Employee employee) { this.currentEmployee = employee; }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/employee/notify-storage-fees-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            NotifyStorageFeesPageController notifyStorageFeesPageController = fxmlLoader.<NotifyStorageFeesPageController>getController();
            notifyStorageFeesPageController.initialize(currentEmployee);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in NotifyStorageFees render()");
        }
    }
}
