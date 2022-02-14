module com.sailingclub.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires backend;


    opens com.sailingclub.frontend to javafx.fxml;
    exports com.sailingclub.frontend;

    opens com.sailingclub.frontend.controllers to javafx.fxml;
    exports com.sailingclub.frontend.controllers;

    opens com.sailingclub.frontend.controllers.member to javafx.fxml;
    exports com.sailingclub.frontend.controllers.member;

    opens com.sailingclub.frontend.controllers.employee to javafx.fxml;
    exports com.sailingclub.frontend.controllers.employee;
}