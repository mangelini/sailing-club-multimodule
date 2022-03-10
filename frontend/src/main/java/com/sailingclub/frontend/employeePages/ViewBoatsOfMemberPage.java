package com.sailingclub.frontend.employeePages;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.controllers.employee.ViewBoatsOfMemberController;
import entities.Employee;
import entities.Member;
import javafx.fxml.FXMLLoader;

public class ViewBoatsOfMemberPage {
    Employee currentEmployee;
    Member selectedMember;

    public ViewBoatsOfMemberPage(Employee currentEmployee, Member selectedMember){
        this.currentEmployee = currentEmployee;
        this.selectedMember = selectedMember;
    }

    /**
     * Gets the path of the FXML for the current page
     * and attach the resource to the scene
     */
    public void render() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/employee/view-boats-of-member-page.fxml"));
            Helpers.staticScene.setRoot(fxmlLoader.load());

            ViewBoatsOfMemberController viewBoatsOfMemberController = fxmlLoader.<ViewBoatsOfMemberController>getController();
            viewBoatsOfMemberController.initialize(currentEmployee, selectedMember);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in ViewBoatsOfMemberPage render()");
        }
    }
}
