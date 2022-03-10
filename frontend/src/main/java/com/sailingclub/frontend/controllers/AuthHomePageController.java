package com.sailingclub.frontend.controllers;

import com.sailingclub.frontend.Helpers;
import com.sailingclub.frontend.authPages.admin.AdminSignInPage;
import com.sailingclub.frontend.authPages.admin.AdminSignUpPage;
import com.sailingclub.frontend.authPages.employee.EmployeeSignInPage;
import com.sailingclub.frontend.authPages.member.MemberAuthHomePage;
import entities.Employee;
import javafx.fxml.FXML;
import messageManagement.Message;
import messageManagement.MessageType;
import messageManagement.Reply;
import messageManagement.ReplyType;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

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
