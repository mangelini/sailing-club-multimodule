package com.sailingclub.frontend.controllers;

import com.sailingclub.frontend.authPages.admin.AdminSignInPage;
import com.sailingclub.frontend.authPages.employee.EmployeeSignInPage;
import com.sailingclub.frontend.authPages.member.MemberAuthHomePage;
import javafx.fxml.FXML;

public class AuthHomePageController {
    /**
     * Initialize FXML components,
     */
    @FXML
    public void initialize(){
    }
    /**
     * When the User button is clicked creates a new
     * MemberAuthHomePage page
     */
    public void onMemberClick(){
        new MemberAuthHomePage().render();
    }

    /**
     * When the Employee button is clicked creates a new
     * EmployeeSignIn page
     */
    public void onEmployeeClick(){
        new EmployeeSignInPage().render();
    }

    /**
     * When the User button is clicked creates a new
     * AdminSignIn page
     */
    public void onAdminClick() { new AdminSignInPage().render(); }
}
