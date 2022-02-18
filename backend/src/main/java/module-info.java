module backend {
    requires java.sql;
    requires java.sql.rowset;
    exports messageManagement to com.sailingclub.frontend;
    exports entities to com.sailingclub.frontend;
    opens entities to javafx.base;
}