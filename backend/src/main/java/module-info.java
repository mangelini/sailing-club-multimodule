module backend {
    requires java.sql;
    requires mariaDB4j.core;
    requires java.sql.rowset;
    exports messageManagement to com.sailingclub.frontend;
}